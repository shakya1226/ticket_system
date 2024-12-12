package com.example.ticketing.dto;

public class VendorInput {
    private int VendorTotal;

    public VendorInput(int vendorTotal) {
        this.VendorTotal = vendorTotal;
    }

    public int getVendorTotal() {
        return VendorTotal;
    }

    public void setVendorTotal(int vendorTotal) {
        this.VendorTotal = vendorTotal;
    }
}
