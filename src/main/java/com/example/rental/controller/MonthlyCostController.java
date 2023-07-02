package com.example.rental.controller;

import com.example.rental.model.MonthlyCost;
import com.example.rental.service.MonthlyCostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("monthlyCosts")
public class MonthlyCostController {

    private final MonthlyCostService monthlyCostService;

    @GetMapping("/")
    @CrossOrigin
    public List<MonthlyCost> showAllMonthlyCosts() {
        return monthlyCostService.showAllMonthlyCosts();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void deleteMonthlyCost(@PathVariable Long id) {
        monthlyCostService.deleteMonthlyCost(id);
    }

}
