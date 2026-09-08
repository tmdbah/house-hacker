package com.tmdbah;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/tenants")
public class TenantController {

    @GetMapping
    public List<Tenant> getTenants() {
        return List.of(
                new Tenant(1L, "Tenant A", "Property 1", new BigDecimal("650.00")),
                new Tenant(2L, "Tenant B", "Property 1", new BigDecimal("700.00"))
        );
    }
}
