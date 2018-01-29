package com.lamdevops.annotation.validator.CheckCase;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public enum CaseMode {
    UPPER,
    LOWER;

    static class TradeHistoryTest {

        private Validator validator;
        @BeforeEach
        public void init() throws Exception {
            validator = Validation.byDefaultProvider().configure()
                    .messageInterpolator(
                            new ResourceBundleMessageInterpolator(
                                    new PlatformResourceBundleLocator("ValidationMessages")
                            )
                    ).buildValidatorFactory().getValidator();
        }

        @Test
        public void testTradeHistory() throws Exception {

            TradeHistory tradeHistory = new TradeHistory(LocalDate.now(), LocalDate.now().minusDays(2));

            Constructor<TradeHistory> constructor = TradeHistory.class.getConstructor(LocalDate.class, LocalDate.class);

            Set<ConstraintViolation<TradeHistory>> constraintViolations =
                    validator.forExecutables()
                            .validateConstructorParameters(constructor,
                                    new Object[]{tradeHistory.getStartDate(), tradeHistory.getEndDate()});

            assertEquals(1, constraintViolations.size());

            constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));

        }

    }
}
