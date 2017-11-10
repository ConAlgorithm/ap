package jma.models;

import java.math.BigDecimal;

public class UserInfoModelLTV {
    private String id;
    private int status;
    private String company;
    private BigDecimal income;
    private String livingAddress;
    private String livingAddressCode;
    private String purpose;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public BigDecimal getIncome() {
        return income;
    }
    public void setIncome(BigDecimal income) {
        this.income = income;
    }
    public String getLivingAddress() {
        return livingAddress;
    }
    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }
    public String getLivingAddressCode() {
        return livingAddressCode;
    }
    public void setLivingAddressCode(String livingAddressCode) {
        this.livingAddressCode = livingAddressCode;
    }
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
    
}
