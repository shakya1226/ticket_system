package com.example.ticketing.service;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {
    private static final AtomicInteger customerIdGenerator = new AtomicInteger(1);
    private final int customerId;
    private TicketPoolManager ticketPoolManager;
    private final int retrievalRate;

    public Customer(TicketPoolManager ticketPoolManager, int customerId, int retrievalRate) {
        this.ticketPoolManager = ticketPoolManager;
        this.customerId = customerIdGenerator.getAndIncrement();
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                int ticketsToPurchase = retrievalRate;
                ticketPoolManager.removeTickets(customerId, ticketsToPurchase);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer " + customerId + " interrupted.");
            }
        }

        System.out.println("Customer " + customerId + " stopped.");
    }
}