package Handler;

import Result.FindEventResult;
import Result.GetAllEventResult;
import Service.FindEventService;
import Service.FindPersonService;
import Service.GetAllEventService;
import Service.GetAllPersonService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class FindEventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        System.out.println("Starting FindEvent Handler");

        System.out.println("Request Method is: " + exchange.getRequestMethod());

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                Headers reqHeaders = exchange.getRequestHeaders();

                if (reqHeaders.containsKey("Authorization")) {
                    String authToken = reqHeaders.getFirst("Authorization");

                    String url = exchange.getRequestURI().toString();

                    String[] parts = url.split("/");

                    //Search for specific event
                    if (parts.length == 3) {
                        FindEventResult result = FindEventService.eventResponse(authToken, parts[2]);

                        if (result.isSuccess()) {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        }
                        else {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }

                        Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

                        gson.toJson(result, resBody);
                        resBody.close();
                    }

                    //Search for all events
                    else if (parts.length == 2) {
                        GetAllEventResult result = GetAllEventService.eventResponse(authToken);

                        if (result.isSuccess()) {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        }
                        else {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }

                        Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

                        gson.toJson(result, resBody);
                        resBody.close();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

            FindEventResult result = new FindEventResult(e.toString(), false);

            gson.toJson(result, resBody);
            resBody.close();

            e.printStackTrace();
        }
    }
}
