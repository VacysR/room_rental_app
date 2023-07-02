package com.example.rental.service;

import com.example.rental.dto.ContractDto;
import com.example.rental.model.Contract;
import com.example.rental.model.Tenant;
import com.example.rental.repository.ContractRepository;
import com.example.rental.repository.TenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final TenantRepository tenantRepository;

    public List<Contract> showAllContracts() {
        return contractRepository.findAll();
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }

    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contract not found with id: " + id));
    }

    public Contract addContractEndDate(Long id, String endDate) {
        Contract existingContract = getContractById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(endDate, formatter);
        existingContract.setEndDate(localDate);
        return saveContract(existingContract);
    }

    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public void addNewContract(ContractDto contractDto) {
        try {
            // Check if new contract has tenant name and start date
            if (contractDto.getTenantName() == null || contractDto.getStartDate() == null) {
                throw new IllegalArgumentException("Tenant name and start date are required");
            }
            // Create and populate the contract entity
            Contract contract = new Contract();
            contract.setMonthlyRent(contractDto.getMonthlyRent());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            contract.setStartDate(LocalDate.parse(contractDto.getStartDate(), formatter));
            contract.setRoomNumber(contractDto.getRoomNumber());
            contract.setRoomName(contractDto.getRoomName());
            contract.setDeposit(contractDto.getDeposit());
            // Create and populate the tenant entity
            Tenant tenant = new Tenant();
            if (contractDto.getTenantName() == null) {
                throw new IllegalArgumentException("Tenant name is required");
            }
            tenant.setName(contractDto.getTenantName());
            // Establish the relationship between tenant and contract
            contract.setTenant(tenant);
            tenant.setContracts(Arrays.asList(contract));
            // Save the tenant and its associated contract
            tenantRepository.save(tenant);
        } catch (Exception e) {
            // Handle the exception or rethrow it as needed
            System.err.println("Error adding new contract: " + e.getMessage());
        }
    }

    public Double showTotalMonthlyRentIncome() {
        return contractRepository.findAll().stream()
                .filter(contract -> contract.getEndDate() == null)// Only active contracts
                .filter(contract -> contract.getMonthlyRent() > 0)
                .mapToDouble(Contract::getMonthlyRent)
                .sum();
    }
}
