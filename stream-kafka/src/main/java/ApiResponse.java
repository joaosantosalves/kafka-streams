import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiResponse {
    public static String api(String json) throws IOException {
        Client client = new Gson().fromJson(json, Client.class);
        var accessKey = "cb18939054254dbe26ca9f98031c94a6";
        URL url = new URL("http://api.ipstack.com/" + client.getIp() + "?access_key=" + accessKey);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();
        if(responseCode != 200)
            throw new RuntimeException("HttpResponseCode: " + responseCode);

        StringBuilder jsonApi = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()){
            jsonApi.append(scanner.nextLine());
        }
        scanner.close();
        LocationAPI location = new Gson().fromJson(jsonApi.toString(), LocationAPI.class);
        location.setClientId(String.valueOf(client.getClientId()));
        location.setTimestamp(client.getTimestamp());
        return new Gson().toJson(location);
    }
}
