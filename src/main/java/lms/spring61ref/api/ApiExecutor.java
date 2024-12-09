package lms.spring61ref.api;

import java.io.IOException;

public interface ApiExecutor{
    String executeApi(String url) throws IOException;
}
