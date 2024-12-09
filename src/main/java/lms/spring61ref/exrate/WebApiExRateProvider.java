package lms.spring61ref.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
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

        String url = "https://open.er-api.com/v6/latest/"+ currency;

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = getString(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return getBigDecimal(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    private static String getString(URI uri) throws IOException {
        String response;
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream())) ){
            response = br.lines().collect(Collectors.joining());
        }
        return response;
    }

    private static BigDecimal getBigDecimal(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateData exRateData = mapper.readValue(response, ExRateData.class);
        return exRateData.rates().get("KRW");
    }
}
