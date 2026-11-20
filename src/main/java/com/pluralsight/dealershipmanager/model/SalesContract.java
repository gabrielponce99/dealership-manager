package com.pluralsight.dealershipmanager.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static com.pluralsight.dealershipmanager.model.LoanCalculator.calculateMonthlyPayment;

public class SalesContract extends Contract {
    private final BigDecimal PRICE_THRESHOLD = new BigDecimal("10000");
    private BigDecimal salesTaxAmount, recordingFee;
    private boolean isFinanced;

    public SalesContract(LocalDate contractDate, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(contractDate, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = getVehicleSold().getPrice().multiply(new BigDecimal(".05"));
        this.recordingFee = new BigDecimal("100");
        this.isFinanced = isFinanced;
    }

    public BigDecimal getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(BigDecimal salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public BigDecimal getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(BigDecimal recordingFee) {
        this.recordingFee = recordingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    public BigDecimal getProcessingFee(){
        boolean isUnderPriceThreshold = getVehicleSold().getPrice().compareTo(PRICE_THRESHOLD) <= 0;
        if (isUnderPriceThreshold) {
            return new BigDecimal("295.00");
        } else {
            return new BigDecimal("495.00");
        }
    }

    @Override
    public BigDecimal getTotalPrice() {
        return getVehicleSold()
                .getPrice()
                .add(getSalesTaxAmount()
                        .add(getRecordingFee()
                                .add(getProcessingFee())))
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        if (!isFinanced) {
            return BigDecimal.ZERO;
        } else {
            boolean isAbovePriceThreshold = getVehicleSold().getPrice().compareTo(PRICE_THRESHOLD) >= 0;

            if (isAbovePriceThreshold) {
                return calculateMonthlyPayment(getVehicleSold().getPrice(), new BigDecimal("4.25"), 48);
            } else {
                return calculateMonthlyPayment(getVehicleSold().getPrice(), new BigDecimal("5.25"), 24);
            }
        }
    }
}
