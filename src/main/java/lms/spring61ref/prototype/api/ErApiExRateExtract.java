package lms.spring61ref.prototype.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lms.spring61ref.prototype.exrate.ExRateData;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class ErApiExRateExtract {

    public BigDecimal getValue(String response){
        ObjectMapper objectMapper = new ObjectMapper();
        ExRateData data = null;
        try {
            data = objectMapper.readValue(response, ExRateData.class);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        BigDecimal exRate = data.rates().get("KRW");
        return exRate;
    }

}
