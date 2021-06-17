package com.oracle.tech.domain;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Customer {

	@NotNull(message = "CustoerId can not be null")
	@NotEmpty
    private String customerId;
	
    @NotNull(message = "ContractId can not be null")
   	@NotEmpty
    private String contractId;
    
    @NotNull(message = "Geozone can not be null")
   	@NotEmpty
    private String geozone;
    
    @NotNull(message = "Teamcode can not be null")
   	@NotEmpty
    private String teamcode;
    
    @NotNull(message = "Projectcode can not be null")
   	@NotEmpty
    private String projectcode;
    
    @NotNull(message = "Buildduration can not be null")
   	@NotEmpty
    private String buildduration;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getGeozone() {
        return geozone;
    }

    public void setGeozone(String geozone) {
        this.geozone = geozone;
    }

    public String getTeamcode() {
        return teamcode;
    }

    public void setTeamcode(String teamcode) {
        this.teamcode = teamcode;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getBuildduration() {
        return buildduration;
    }

    public void setBuildduration(String buildduration) {
        this.buildduration = buildduration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return contractId == customer.contractId &&
                Objects.equals(customerId, customer.customerId) &&
                Objects.equals(geozone, customer.geozone) &&
                Objects.equals(teamcode, customer.teamcode) &&
                Objects.equals(projectcode, customer.projectcode) &&
                Objects.equals(buildduration, customer.buildduration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, contractId, geozone, teamcode, projectcode, buildduration);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", contractId=" + contractId +
                ", geozone='" + geozone + '\'' +
                ", teamcode='" + teamcode + '\'' +
                ", projectcode='" + projectcode + '\'' +
                ", buildduration='" + buildduration + '\'' +
                '}';
    }
}
