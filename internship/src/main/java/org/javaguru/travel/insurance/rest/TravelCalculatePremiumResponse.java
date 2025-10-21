package org.javaguru.travel.insurance.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse() {
    }

    public TravelCalculatePremiumResponse(String personFirstName, String personLastName, Date agreementDateFrom, Date agreementDateTo) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;

        setAgreementPrice();
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public void setAgreementDateFrom(Date agreementDateFrom) {
        this.agreementDateFrom = agreementDateFrom;
        setAgreementPrice();
    }

    public void setAgreementDateTo(Date agreementDateTo) {
        this.agreementDateTo = agreementDateTo;
        setAgreementPrice();
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public Date getAgreementDateFrom() {
        return agreementDateFrom;
    }

    public Date getAgreementDateTo() {
        return agreementDateTo;
    }

    private void setAgreementPrice() {
        if (agreementDateTo != null && agreementDateFrom != null) {
            LocalDate startDate = agreementDateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = agreementDateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            agreementPrice = BigDecimal.valueOf(ChronoUnit.DAYS.between(startDate, endDate));
        }
    }

    public BigDecimal getAgreementPrice() {
        return agreementPrice;
    }
}
