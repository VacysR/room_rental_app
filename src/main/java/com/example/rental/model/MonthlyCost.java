package com.example.rental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class MonthlyCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn( name = "tenant_id")
    private Tenant tenant;

    private LocalDate correspondingMonth;
    private Double monthlyCost;

    @OneToMany(mappedBy = "monthlyCost", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

}
