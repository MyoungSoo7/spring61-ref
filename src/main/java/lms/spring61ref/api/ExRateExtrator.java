package lms.spring61ref.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;

public interface ExRateExtrator {
    BigDecimal extractExRate(String response) throws JsonProcessingException;
}
