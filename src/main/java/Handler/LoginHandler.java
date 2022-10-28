package Handler;

import DataAccess.DataAccessException;
import Request.LoginRequest;
import Result.LoginResult;
import Service.LoginService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.HttpURLConnection;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();

        System.out.println("Starting Login Handler");

        System.out.println("Request Method is: " + exchange.getRequestMethod());

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                Reader reqBody = new InputStreamReader(exchange.getRequestBody());
                LoginRequest request = (LoginRequest) gson.fromJson(reqBody, LoginRequest.class);

                LoginResult result = LoginService.loginResponse(request);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

                //parameter 1: Object to be written into JSON
                //parameter 2: What it is being written to
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
