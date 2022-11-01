package Handler;

import DataAccess.AuthTokenDao;
import DataAccess.DataAccessException;
import DataAccess.Database;
import Model.AuthToken;
import Result.FindPersonResult;
import Result.GetAllPersonResult;
import Service.FindPersonService;
import Service.GetAllPersonService;
import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;

public class FindPersonHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        Gson gson = new Gson();

        System.out.println("Starting FindPerson Handler");

        System.out.println("Request Method is: " + exchange.getRequestMethod());

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                Headers reqHeaders = exchange.getRequestHeaders();

                if (reqHeaders.containsKey("Authorization")) {
                    String authToken = reqHeaders.getFirst("Authorization");

                    String url = exchange.getRequestURI().toString();

                    String[] parts = url.split("/");

                    //Search for Specific Person
                    if (parts.length == 3) {
                        FindPersonResult result = FindPersonService.personResponse(authToken, parts[2]);

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

                    //Search for all Persons
                    else if (parts.length == 2) {
                        GetAllPersonResult result = GetAllPersonService.personResponse(authToken);

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

            FindPersonResult result = new FindPersonResult(e.toString(), false);

            gson.toJson(result, resBody);
            resBody.close();

            e.printStackTrace();
        }
    }
}
