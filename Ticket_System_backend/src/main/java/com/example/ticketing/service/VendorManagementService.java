package com.example.ticketing.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendorManagementService {
    private int VendorTotal;
    private int releaseRate;
    private List<Thread> vendorThreads = new ArrayList<>();
    private TicketPoolManager ticketPoolManager;

    public VendorManagementService() {
    }

    public void setTicketPoolManager(TicketPoolManager ticketPoolManager) {
        this.ticketPoolManager = ticketPoolManager;
    }

    public void vendorStart(int vendorTotal) {
        for (int i = 1; i <= vendorTotal; i++) {
            Thread thread = new Thread(new Vendor(ticketPoolManager, i + 1, releaseRate));
            vendorThreads.add(thread);
            thread.start();
        }
    }

    public void vendorStop() {
        for (Thread thread : vendorThreads) {
            thread.interrupt();
        }
    }

    public int getVendorTotal() {
        return VendorTotal;
    }

    public void setVendorTotal(int vendorTotal) {
        this.VendorTotal = vendorTotal;
    }

    public void setReleaseRate(int releaseRate) {
        this.releaseRate = releaseRate;
    }
}
