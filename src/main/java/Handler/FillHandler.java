package Handler;

import Request.FillRequest;
import Result.FillResult;
import Result.FindPersonResult;
import Result.GetAllPersonResult;
import Service.FillService;
import Service.FindPersonService;
import Service.GetAllPersonService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        System.out.println("Starting Fill Handler");

        System.out.println("Request Method is: " + exchange.getRequestMethod());

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                String url = exchange.getRequestURI().toString();

                String[] parts = url.split("/");

                if (parts.length == 4) {
                    String username = parts[2];
                    int numGens = Integer.parseInt(parts[3]);
                    FillRequest request = new FillRequest(username, numGens);

                    FillResult result = FillService.fillResponse(request);

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

                if (parts.length == 3) {
                    String username = parts[2];

                    FillRequest request = new FillRequest(username, 4);

                    FillResult result = FillService.fillResponse(request);

                    if (result.isSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    } else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }

                    Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

                    gson.toJson(result, resBody);
                    resBody.close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

            FillResult result = new FillResult("Error: [" + e.toString() + "]", false);

            Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

            gson.toJson(result, resBody);
            resBody.close();
        }
    }
}
