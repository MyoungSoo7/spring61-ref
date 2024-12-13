package lms.spring61ref.spring6.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientApiExecutor implements ApiExecutor {
    @Override
    public String executeApi(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        return sendRequest(request);
    }

    private String sendRequest(HttpRequest request)   {
        try (HttpClient client = HttpClient.newBuilder().build()) {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiRequestInterruptedException("API request was interrupted", e);
        } catch (IOException e) {
            throw new ApiIOException("Failed to send request", e);
        }
    }

    public static class ApiRequestInterruptedException extends RuntimeException {
        public ApiRequestInterruptedException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public static class ApiIOException extends RuntimeException {
        public ApiIOException(String message, IOException cause) {
            super(message, cause);
        }
    }
}
