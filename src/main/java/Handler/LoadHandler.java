package Handler;

import DataAccess.DataAccessException;
import Request.LoadRequest;
import Request.LoginRequest;
import Result.LoadResult;
import Result.LoginResult;
import Service.LoadService;
import Service.LoginService;
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

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

                gson.toJson(result, resBody);
                resBody.close();

                if (result.isSuccess()) {
                    System.out.println("Login Succeeded");
                } else {
                    System.out.println("Login Failed");
                }

            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();

            e.printStackTrace();
        } catch (DataAccessException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
