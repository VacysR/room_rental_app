package com.example.rental.service;

import com.example.rental.model.Tenant;
import com.example.rental.repository.TenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Tenant service implementation
 */
@AllArgsConstructor
@Service
public class TenantService {

    private final TenantRepository tenantRepository;

    public List<Tenant> showAllTenants() {
        return tenantRepository.findAll();
    }

    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id);
    }

}
