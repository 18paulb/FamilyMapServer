package Handler;

import DataAccess.DataAccessException;
import Request.ClearRequest;
import Request.LoginRequest;
import Result.ClearResult;
import Result.LoginResult;
import Service.ClearService;
import Service.LoginService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();

        System.out.println("Starting Clear Handler");

        System.out.println("Request Method is: " + exchange.getRequestMethod());

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                //Reader reqBody = new InputStreamReader(exchange.getRequestBody());

                ClearResult result = ClearService.clearResponse();

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

                //parameter 1: Object to be written into JSON
                //parameter 2: What it is being written to
                gson.toJson(result, resBody);
                resBody.close();

                if (result.isSuccess()) {
                    System.out.println("Clear Succeeded");
                } else {
                    System.out.println("Clear failed");
                }

            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();

            e.printStackTrace();
        } catch (DataAccessException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
