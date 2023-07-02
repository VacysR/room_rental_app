package com.example.rental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn( name = "monthlyCost_id")
    private MonthlyCost monthlyCost;

    private String serviceName;
    private Integer invoiceNumber;
    private Double price;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private String invoicePath;

}
