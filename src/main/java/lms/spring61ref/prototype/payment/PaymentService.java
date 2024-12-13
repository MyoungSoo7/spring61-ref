package lms.spring61ref.prototype.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import lms.spring61ref.spring6.exrate.ExRateData;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class PaymentService   {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = getBigDecimal(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusHours(1);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    private BigDecimal getBigDecimal(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/"+ currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = bufferedReader.lines().collect(Collectors.joining());

        ObjectMapper objectMapper = new ObjectMapper();
        ExRateData data = objectMapper.readValue(response, ExRateData.class);
        BigDecimal exRate = data.rates().get("KRW");
        return exRate;
    }

    public static void main(String[] args) throws IOException {
       PaymentService paymentService = new PaymentService();
       Payment payment = paymentService.prepare(100L, "USD", new BigDecimal("50.7"));
       System.out.println(payment);

    }
}
