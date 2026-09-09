package com.tmdbah;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public List<Tenant> getTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("{id}")
    public Tenant getTenantById(@PathVariable Long id) {
        return tenantService.getTenantById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTenantById(@PathVariable Long id ) {
        tenantService.deleteTenantById(id);
    }

    @PutMapping("{id}")
    public void updateTenantById(@PathVariable Long id, @RequestBody Tenant tenant) {
        tenantService.updateTenantById(id, tenant);
    }

    // TODO: Currently exposing entity directly in the response — should map to a DTO
    @PostMapping
    public void addNewTenant(@RequestBody Tenant tenant) {
        tenantService.insertTenant(tenant);
    }
}
