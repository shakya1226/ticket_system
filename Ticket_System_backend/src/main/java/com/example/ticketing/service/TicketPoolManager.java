package com.example.ticketing.service;

import org.springframework.stereotype.Service;

@Service
public class TicketPoolManager {
    private int ticketCollection = 0;
    private int totalTickets;
    private int releaseRate;
    private int retrievalRate;
    private int maximumCapacity;
    private int availableTickets;
    private int VendorTotal;

    private VendorManagementService vendorManagementService;
    private CustomerManagementService customerManagementService;

    public TicketPoolManager() {
    }

    public void setVendorManagementService(VendorManagementService vendorManagementService) {
        this.vendorManagementService = vendorManagementService;
    }

    public void setCustomerManagementService(CustomerManagementService customerManagementService) {
        this.customerManagementService = customerManagementService;
    }

    public void config(int totalTickets, int releaseRate, int retrievalRate, int maximumCapacity) {
        this.totalTickets = totalTickets;
        this.releaseRate = releaseRate;
        this.retrievalRate = retrievalRate;
        this.maximumCapacity = maximumCapacity;
    }

    public synchronized void addTickets(int vendorId, int releaseRate) {
        while (ticketCollection >= maximumCapacity) {
            try {
                System.out.println("Maximum capacity is full!");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        int spaceLeft = maximumCapacity - ticketCollection;
        if (spaceLeft > 0) {
            int ticketsToAdd = Math.min(releaseRate, spaceLeft);
            ticketCollection += ticketsToAdd;
            System.out.println("Vendor " + vendorId + " added " + ticketsToAdd + " tickets. Total tickets: " + ticketCollection);
            notifyAll();
        }
    }

    public synchronized void removeTickets(int customerId, int ticketsToRemove) {
        while (ticketCollection < ticketsToRemove) {
            try {
                System.out.println("Not enough tickets!");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        ticketCollection -= ticketsToRemove;
        System.out.println("Customer " + customerId + " buy " + ticketsToRemove + " tickets. Total tickets: " + ticketCollection);
        notifyAll();
    }

    public void startSimulation() {
        vendorManagementService.vendorStart(vendorManagementService.getVendorTotal());
        customerManagementService.customerStart();
    }

    public void stopSimulation() {
        vendorManagementService.vendorStop();
        customerManagementService.customerStop();
    }
}
