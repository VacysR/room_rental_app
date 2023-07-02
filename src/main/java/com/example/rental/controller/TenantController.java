package com.example.rental.controller;

import com.example.rental.model.Tenant;
import com.example.rental.service.TenantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("tenants")
public class TenantController {

    final TenantService tenantService;

    @GetMapping("/")
    @CrossOrigin
    public List<Tenant> showAllTenants() {
        return tenantService.showAllTenants();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public void deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
    }

}
