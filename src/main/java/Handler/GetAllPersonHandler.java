package Handler;

import Result.GetAllPersonResult;
import Service.GetAllPersonService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class GetAllPersonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();

        System.out.println("Starting FindAllPerson Handler");

        System.out.println("Request Method is: " + exchange.getRequestMethod());

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                Headers reqHeaders = exchange.getRequestHeaders();

                if (reqHeaders.containsKey("Authorization")) {
                    String authToken = reqHeaders.getFirst("Authorization");

                    GetAllPersonResult result = GetAllPersonService.personResponse(authToken);

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

                    gson.toJson(result, resBody);
                    resBody.close();

                    if (result.isSuccess()) {
                        System.out.println("Get All Succeeded");
                    } else {
                        System.out.println("Get All Failed");
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();

            e.printStackTrace();
        }

    }
}
