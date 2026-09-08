package com.tmdbah;

import java.math.BigDecimal;
import java.util.Objects;

public class Tenant {
    private Long id;
    private String name;
    private String propertyName;
    private BigDecimal monthlyRent;

    public Tenant() {
    }

    public Tenant(Long id, String name, String propertyName, BigDecimal monthlyRent) {
        this.id = id;
        this.name = name;
        this.propertyName = propertyName;
        this.monthlyRent = monthlyRent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(id, tenant.id) && Objects.equals(name, tenant.name) && Objects.equals(propertyName, tenant.propertyName) && Objects.equals(monthlyRent, tenant.monthlyRent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, propertyName, monthlyRent);
    }
}
