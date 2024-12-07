package lms.spring61ref.exrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lms.spring61ref.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        URI url = null;
        try {
            url = new URI("https://open.er-api.com/v6/latest/"+ currency);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection connection = (HttpURLConnection) url.toURL().openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);

        System.out.println("Exchange Rate: " + exRateData.rates().get("KRW"));

        return exRateData.rates().get("KRW");
    }
}
