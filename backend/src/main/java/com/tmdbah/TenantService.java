package com.tmdbah;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;
    private final AiService aiService;

    public TenantService(TenantRepository tenantRepository, AiService aiService) {
        this.tenantRepository = tenantRepository;
        this.aiService = aiService;
    }

    public List<Tenant> getAllTenants() {
        // TODO: Currently exposing entity directly in the response — should map to a DTO
        // instead (avoids leaking internal fields, decouples API shape from DB
        // schema). Revisit once covered in "Building APIs with Spring Boot" course.
        return tenantRepository.findAll();
    }

    public void insertTenant(Tenant tenant) {
        String prompt = "Write a brief, friendly one-sentence welcome message for a new tenant named "
                + tenant.getName() + " moving into " + tenant.getPropertyName() + ".";
        String chatRes = aiService.chat(prompt);
        tenant.setWelcomeMessage(chatRes);
        tenantRepository.save(tenant); // We should have validation here e.g. null checks
    }

    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void deleteTenantById(Long id) {
        if (!tenantRepository.existsById(id)) {
            throw new IllegalStateException(id + " not found");
        }
        tenantRepository.deleteById(id);
    }

    public void updateTenantById(Long id, Tenant update) {
        Tenant tenant = tenantRepository.findById(id)
                        .orElseThrow(() -> new IllegalStateException(id + " not found"));
        tenant.setName(update.getName());
        tenant.setMonthlyRent(update.getMonthlyRent());
        tenant.setPropertyName(update.getPropertyName());
        tenantRepository.save(tenant); // We should have validation here e.g. null checks
    }
}
