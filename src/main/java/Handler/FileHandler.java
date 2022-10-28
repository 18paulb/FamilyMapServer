package Handler;

import com.sun.net.httpserver.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

public class FileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                //Headers reqHeaders = (Headers) exchange.getRequestHeaders();

                String urlPath = exchange.getRequestURI().toString();

                if (urlPath.equals("/")) {
                    urlPath = "/index.html";
                }

                String filePath = "web" + urlPath;

                File file = new File(filePath);

                if (file.exists()) {

                    //Reads file and rights it to the HttpExchange output stream
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    OutputStream respBody = exchange.getResponseBody();

                    Files.copy(file.toPath(), respBody);

                    respBody.close();

                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    exchange.getResponseBody().close();
                }
            }

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
