package com.example.ticketing.controller;

import com.example.ticketing.dto.ConfigSettings;
import com.example.ticketing.dto.CustomerInput;
import com.example.ticketing.dto.VendorInput;
import com.example.ticketing.service.CustomerManagementService;
import com.example.ticketing.service.VendorManagementService;
import org.springframework.web.bind.annotation.*;
import com.example.ticketing.service.TicketPoolManager;

@RestController
@RequestMapping("/ticketing")
public class TicketController {

    private final TicketPoolManager ticketPoolManager;
    private final VendorManagementService vendorManagementService;
    private final CustomerManagementService customerManagementService;

    public TicketController(TicketPoolManager ticketPoolManager, VendorManagementService vendorManagementService, CustomerManagementService customerManagementService) {
        this.ticketPoolManager = ticketPoolManager;
        this.vendorManagementService = vendorManagementService;
        this.customerManagementService = customerManagementService;
        this.ticketPoolManager.setVendorManagementService(vendorManagementService);
        this.ticketPoolManager.setCustomerManagementService(customerManagementService);
        this.vendorManagementService.setTicketPoolManager(ticketPoolManager);
    }

    @PostMapping("/config")
    public String configure(@RequestBody ConfigSettings configSettings) {
        ticketPoolManager.config(configSettings.getTotalTickets(), configSettings.getTicketReleaseRate(), configSettings.getCustomerRetrievalRate(), configSettings.getMaximumCapacity());
        vendorManagementService.setReleaseRate(configSettings.getTicketReleaseRate());
        customerManagementService.setRetrieveTickets(configSettings.getCustomerRetrievalRate());
        return "Configuration successful";
    }

    @PostMapping("/start")
    public String start() {
        ticketPoolManager.startSimulation();
        return "Starting ticketing system";
    }

    @PostMapping("/stop")
    public String stop() {
        ticketPoolManager.stopSimulation();
        return "Stopping ticketing system";
    }

    @PostMapping("/customer-count")
    public String setCustomerCount(@RequestBody CustomerInput customerInput) {
        customerManagementService.setCustomerTotal(customerInput.getCustomerTotal());
        return "Customer count is " + customerInput.getCustomerTotal();
    }

    @PostMapping("/vendor-count")
    public String setVendorCount(@RequestBody VendorInput vendorInput) {
        vendorManagementService.setVendorTotal(vendorInput.getVendorTotal());
        return "Vendor count is " + vendorInput.getVendorTotal();
    }
}