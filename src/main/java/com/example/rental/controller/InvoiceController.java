package com.example.rental.controller;

import com.example.rental.model.Invoice;
import com.example.rental.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/")
    @CrossOrigin
    public List<Invoice> showAllInvoices() {
        return invoiceService.showAllInvoices();
    }
}
