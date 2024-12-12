package com.example.ticketing.dto;

public class CustomerInput {
    private int CustomerTotal;

    public CustomerInput(int customerTotal) {
        CustomerTotal = customerTotal;
    }

    public void setCustomerTotal(int customerTotal) {
        CustomerTotal = customerTotal;
    }

    public int getCustomerTotal() {
        return CustomerTotal;
    }
}
