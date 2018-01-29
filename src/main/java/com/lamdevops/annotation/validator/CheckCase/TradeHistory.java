package com.lamdevops.annotation.validator.CheckCase;

import java.time.LocalDate;

public class TradeHistory {
    private final LocalDate startDate;
    private final LocalDate endDate;

    @ConsistentDateParameters
    public TradeHistory(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
