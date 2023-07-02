package com.example.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDto {

    private String tenantName;
    private String startDate;
    private Integer roomNumber;
    private String roomName;
    private Double monthlyRent;
    private Double deposit;
    private String contractPath;

}
