package com.example.rental.controller;

import com.example.rental.dto.ContractDto;
import com.example.rental.model.Contract;
import com.example.rental.service.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("contracts")
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/")
    @CrossOrigin
    public List<Contract> showAllContracts() {
        return contractService.showAllContracts();
    }

    @GetMapping("/totalMonthlyRentIncome")
    @CrossOrigin
    public Double showTotalMonthlyRentIncome() {
        return contractService.showTotalMonthlyRentIncome();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public void addContractEndDate(@PathVariable Long id, @RequestBody String endDate) {
        Contract existingContract = contractService.getContractById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(endDate, formatter);
        existingContract.setEndDate(localDate);
        contractService.saveContract(existingContract);
    }

    @PostMapping("/")
    @CrossOrigin
    public List<Contract> addContract(@RequestBody ContractDto contractDto) {
        contractService.addNewContract(contractDto);
        return contractService.showAllContracts();
    }

}
