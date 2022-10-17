package Handler;

import com.sun.net.httpserver.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;

import java.io.IOException;

public class FindPersonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        if (exchange.getRequestMethod().toLowerCase().equals("get")) {

            Headers reqHeaders = (Headers) exchange.getRequestHeaders();

        }
    }
}
