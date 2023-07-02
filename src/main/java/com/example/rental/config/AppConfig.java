package com.example.rental.config;

import com.example.rental.model.Contract;
import com.example.rental.model.Invoice;
import com.example.rental.model.MonthlyCost;
import com.example.rental.model.Tenant;
import com.example.rental.repository.ContractRepository;
import com.example.rental.repository.MonthlyCostRepository;
import com.example.rental.repository.TenantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner createInitialTenantData(TenantRepository tenantRepository, ContractRepository contractRepository, MonthlyCostRepository monthlyCostRepository) {
        return args -> {

            Tenant cankut = Tenant.builder()
                    .name("Cankut")
                    .build();

            Tenant chara = Tenant.builder()
                    .name("Chara")
                    .build();

            Tenant alejandro = Tenant.builder()
                    .name("Alejandro")
                    .build();

            Tenant amanda = Tenant.builder()
                    .name("Amanda")
                    .build();

            Invoice invoice = Invoice.builder()
                    .serviceName("Internet")
                    .price(20.0)
                    .invoicePath("invoices/cankut_01.pdf")
                    .build();

            MonthlyCost january2022 = MonthlyCost.builder()
                    .correspondingMonth(LocalDate.of(2022, 1, 31))
                    .monthlyCost(50.5)
                    .invoices(Collections.singletonList(invoice))
                    .build();

            invoice.setMonthlyCost(january2022);

            MonthlyCost february2022 = MonthlyCost.builder()
                    .correspondingMonth(LocalDate.of(2022, 2, 28))
                    .monthlyCost(52.5)
                    .build();

            MonthlyCost march2022 = MonthlyCost.builder()
                    .correspondingMonth(LocalDate.of(2022, 3, 31))
                    .monthlyCost(40.5)
                    .build();

            Contract contract1 = Contract.builder()
                    .startDate(LocalDate.of(2022, 1, 1))
                    .roomNumber(1)
                    .roomName("double room")
                    .monthlyRent(500.0)
                    .deposit(500.0)
                    .tenant(cankut)
                    .contractPath("static/invoices/cankut_01.pdf")
                    .build();

            Contract contract2 = Contract.builder()
                    .startDate(LocalDate.of(2022, 1, 1))
                    .roomNumber(2)
                    .roomName("exterior Gran Via")
                    .monthlyRent(400.0)
                    .deposit(400.0)
                    .tenant(chara)
                    .contractPath("static/invoices/chara_01.pdf")
                    .build();

            Contract contract3 = Contract.builder()
                    .startDate(LocalDate.of(2021, 1, 1))
                    .endDate(LocalDate.of(2022, 8, 30))
                    .roomNumber(3)
                    .roomName("interior")
                    .monthlyRent(300.0)
                    .deposit(300.0)
                    .tenant(alejandro)
                    .contractPath("static/invoices/alejandro_01.pdf")
                    .build();

            Contract contract4 = Contract.builder()
                    .startDate(LocalDate.of(2022, 4, 1))
                    .endDate(LocalDate.of(2022, 6, 30))
                    .roomNumber(4)
                    .roomName("interior w/ balcony")
                    .monthlyRent(250.0)
                    .deposit(250.0)
                    .tenant(amanda)
                    .contractPath("static/invoices/amanda_01.pdf")
                    .build();

            cankut.setMonthlyCosts(Arrays.asList(january2022, february2022));
            january2022.setTenant(cankut);
            february2022.setTenant(cankut);
            cankut.setContracts(Collections.singletonList(contract1));
            tenantRepository.save(cankut);

            chara.setMonthlyCosts(Arrays.asList(march2022));
            march2022.setTenant(chara);
            chara.setContracts(Collections.singletonList(contract2));
            tenantRepository.save(chara);

            alejandro.setContracts(Collections.singletonList(contract3));
            tenantRepository.save(alejandro);

            amanda.setContracts(Collections.singletonList(contract4));
            tenantRepository.save(amanda);
        };
    }
}
