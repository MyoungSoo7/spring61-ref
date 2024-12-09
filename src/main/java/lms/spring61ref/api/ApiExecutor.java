package lms.spring61ref.api;

import java.io.IOException;
import java.net.URI;

public interface ApiExecutor{
    String executeApi(URI url) throws IOException;
}
