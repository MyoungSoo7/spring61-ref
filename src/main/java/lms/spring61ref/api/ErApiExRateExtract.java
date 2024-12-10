package lms.spring61ref.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lms.spring61ref.exrate.ExRateData;

import java.math.BigDecimal;

public class ErApiExRateExtract implements ExRateExtrator {
    @Override
    public BigDecimal extractExRate(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);
        return exRateData.rates().get("KRW");
    }
}
