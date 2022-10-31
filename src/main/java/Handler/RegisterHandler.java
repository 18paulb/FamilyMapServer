package Handler;

import DataAccess.DataAccessException;
import Request.RegisterRequest;
import Result.RegisterResult;
import Service.LoginService;
import Service.RegisterService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.concurrent.locks.ReentrantLock;

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();

        System.out.println("Starting Register Handler");

        System.out.println("Request Method is: " + exchange.getRequestMethod());

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                Reader reqBody = new InputStreamReader(exchange.getRequestBody());
                RegisterRequest request = (RegisterRequest) gson.fromJson(reqBody, RegisterRequest.class);

                RegisterResult result = RegisterService.registerResponse(request);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                Writer resBody = new OutputStreamWriter(exchange.getResponseBody());

                gson.toJson(result, resBody);
                resBody.close();

                if (result.isSuccess()) {
                    System.out.println("User Registered");
                } else {
                    System.out.println("Error Occurred in Registration");
                }
            }
        } catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();

            e.printStackTrace();
        }
        /*
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();

            e.printStackTrace();
        }
            catch (DataAccessException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        */



    }
}
