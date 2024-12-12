package com.example.ticketing.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerManagementService {
    private int CustomerTotal;
    private int retrieveTickets;
    private List<Thread> customerThreads = new ArrayList<>();
    private TicketPoolManager ticketPoolManager;

    public CustomerManagementService(TicketPoolManager ticketPoolManager) {
        this.ticketPoolManager = ticketPoolManager;
    }

    public void customerStart() {
        for (int i = 1; i <= CustomerTotal; i++) {
            Thread thread = new Thread(new Customer(ticketPoolManager, i + 1, retrieveTickets));
            customerThreads.add(thread);
            thread.start();
        }
    }

    public void customerStop() {
        for (Thread thread : customerThreads) {
            thread.interrupt();
        }
    }

    public int getCustomerTotal() {
        return CustomerTotal;
    }

    public void setCustomerTotal(int customerTotal) {
        this.CustomerTotal = customerTotal;
    }

    public void setRetrieveTickets(int retrieveTickets) {
        this.retrieveTickets = retrieveTickets;
    }
}
