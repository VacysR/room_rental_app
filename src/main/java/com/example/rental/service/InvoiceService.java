package com.example.rental.service;

import com.example.rental.model.Invoice;
import com.example.rental.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public List<Invoice> showAllInvoices() {
        return invoiceRepository.findAll();
    }

}
