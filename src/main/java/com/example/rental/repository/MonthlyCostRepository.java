package com.example.rental.repository;

import com.example.rental.model.MonthlyCost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyCostRepository extends JpaRepository<MonthlyCost, Long> {
}
