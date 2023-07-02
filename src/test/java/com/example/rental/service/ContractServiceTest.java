package com.example.rental.service;

import com.example.rental.model.Contract;
import com.example.rental.repository.ContractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContractServiceTest {

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks //Injectina arba iskiepija visus MOCKUS i duotaji instance'a
    private ContractService contractService;

    @BeforeEach
    public void setUp() {
        //nesvarbu ka jis daro, bet reikia kiekvienam testui, kur naudoju MOCKUS
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCalculateTotalMonthlyRentIncomeFromActiveContracts() {

        Contract contract1 = new Contract(LocalDate.of(2022, 01, 01), 200.0);
        Contract contract2 = new Contract(null, 200.0);
        Contract contract3 = new Contract(null, 500.0);

        List<Contract> contractList = Arrays.asList(contract1, contract2, contract3);

        //Aprasau salyga, kada man turetu testinius duomenis grazinti
        when(contractRepository.findAll()).thenReturn(contractList);

        Double actualMonthlyRentIncome = contractService.showTotalMonthlyRentIncome();
        assertEquals(700.0, actualMonthlyRentIncome);
    }

    @Test
    void shouldExcludeNegativeAmountsFromActiveContracts() {

        Contract contract1 = new Contract(LocalDate.of(2022, 01, 01), 200.0);
        Contract contract2 = new Contract(null, -200.0);
        Contract contract3 = new Contract(null, 500.0);

        List<Contract> contractList = Arrays.asList(contract1, contract2, contract3);

        //Aprasau salyga, kada man turetu testinius duomenis grazinti
        when(contractRepository.findAll()).thenReturn(contractList);

        Double actualMonthlyRentIncome = contractService.showTotalMonthlyRentIncome();
        assertEquals(500.0, actualMonthlyRentIncome);
    }

}