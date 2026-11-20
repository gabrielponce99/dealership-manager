package com.pluralsight.dealershipmanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Contract {
    private LocalDate contractDate;
    private String customerName, customerEmail;

    public Contract(LocalDate contractDate, String customerName, String customerEmail) {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public abstract BigDecimal getTotalPrice();

    public abstract BigDecimal getMonthlyPayment();
}
