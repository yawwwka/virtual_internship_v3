package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(calculateAgreementPrice(request));

        return response;
    }

    private BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {
        LocalDate start = request.getAgreementDateFrom().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate end = request.getAgreementDateTo().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return BigDecimal.valueOf(ChronoUnit.DAYS.between(start, end));
    }
}
