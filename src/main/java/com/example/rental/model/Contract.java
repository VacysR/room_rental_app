package com.example.rental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn( name = "tenant_id")
    private Tenant tenant;

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer roomNumber;
    private String roomName;
    private Double monthlyRent;
    private Double deposit;
    private String contractPath;

}
