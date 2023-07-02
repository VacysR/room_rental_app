package com.example.rental.service;

import com.example.rental.model.Invoice;
import com.example.rental.model.MonthlyCost;
import com.example.rental.repository.MonthlyCostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class MonthlyCostService {

    private final MonthlyCostRepository monthlyCostRepository;

    public List<MonthlyCost> showAllMonthlyCosts() {
        return monthlyCostRepository.findAll();
    }

    public void deleteMonthlyCost(Long id) {
        monthlyCostRepository.deleteById(id);
    }

    //Calculate monthlyCost per tenant after adding invoices to a specific monthlyCost
    public MonthlyCost calculateMonthlyCost(List<Invoice> invoices) {
        Double price = invoices.stream()
                .mapToDouble(Invoice::getPrice)
                .sum();
        MonthlyCost monthlyCost = new MonthlyCost();
        monthlyCost.setMonthlyCost(price);
        return monthlyCost;
    }

}
