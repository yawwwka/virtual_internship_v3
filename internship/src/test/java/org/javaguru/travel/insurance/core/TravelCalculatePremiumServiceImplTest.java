package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private DateTimeService dateTimeService;
    private TravelCalculatePremiumServiceImpl service;

    @BeforeEach
    public void setUp() {
        dateTimeService = new DateTimeService();
        service = new TravelCalculatePremiumServiceImpl(dateTimeService);
    }

    @Test
    public void shouldPopulateResponse() {
        var request = createRequestWithAllFields();

        var response = service.calculatePremium(request);

        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void shouldPopulateFirstName() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getPersonFirstName(), request.getPersonFirstName());
    }

    @Test
    public void shouldPopulateLastName() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getPersonLastName(), request.getPersonLastName());
    }

    @Test
    public void shouldPopulateDateFrom() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateFrom(), request.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateDateTo() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);
        assertEquals(response.getAgreementDateTo(), request.getAgreementDateTo());
    }

    @Test
    public void shouldCalculateDaysBeetween() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);

        assertEquals(BigDecimal.valueOf(7670), response.getAgreementPrice());
    }

    @Test
    public void shouldWorkDateTimeService() {
        var request = createRequestWithAllFields();
        var response = service.calculatePremium(request);

        DateTimeService dateTimeService = new DateTimeService();

        assertEquals(7670, dateTimeService.getDaysBetween(response.getAgreementDateFrom(), response.getAgreementDateTo()));
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("John");
        request.setPersonLastName("Peterson");
        request.setAgreementDateFrom(new Date(2004, Calendar.JUNE, 30));
        request.setAgreementDateTo(new Date(2025, Calendar.JUNE, 30));
        return request;
    }
}