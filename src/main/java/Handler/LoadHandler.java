package Handler;

import Request.LoadRequest;
import Result.LoadResult;
import Service.LoadService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.HttpURLConnection;

public class LoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        System.out.println("Starting Load Handler");

        System.out.println("Request Method is: " + exchange.getRequestMethod());

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                Reader reqBody = new InputStreamReader(exchange.getRequestBody());
                LoadRequest request = (LoadRequest) gson.fromJson(reqBody, LoadRequest.class);

                LoadResult result = LoadService.loadResponse(request);

                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }

                Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

                gson.toJson(result, resBody);
                resBody.close();

                if (result.isSuccess()) {
                    System.out.println("Load Succeeded");
                } else {
                    System.out.println("Load Failed");
                }

            }
        } catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

            LoadResult result = new LoadResult(e.toString(), false);

            gson.toJson(result, resBody);
            resBody.close();


            e.printStackTrace();
        }

    }
}
