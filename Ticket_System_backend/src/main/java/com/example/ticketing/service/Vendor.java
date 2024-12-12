package com.example.ticketing.service;

import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private static final AtomicInteger vendorIdGenerator = new AtomicInteger(1);
    private int vendorId;
    private TicketPoolManager ticketPoolManager;
    private int releaseRate;

    public Vendor(TicketPoolManager ticketPoolManager, int vendorId, int releaseRate) {
        this.ticketPoolManager = ticketPoolManager;
        this.vendorId = vendorIdGenerator.getAndIncrement();
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // Simulates ticket release interval
                ticketPoolManager.addTickets(vendorId, releaseRate);
            } catch (InterruptedException e) {
                System.out.println("Vendor " + vendorId + " interrupted.");
                Thread.currentThread().interrupt();
            }
        }

    }
}