import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.util.UUID;


class apiResponseTest {

    @Test
    void AnaliseResponse() throws IOException, JSONException {
        Client client = new Client();
        client.setClientId(UUID.fromString(UUID.randomUUID().toString()));
        client.setIp("134.201.250.155");
        client.setTimestamp(System.currentTimeMillis());
        var response = ApiResponse.api(new Gson().toJson(client));
        JSONAssert.assertEquals("{\"clientId\":\"d4d0c84f\",\"timestamp\":16,\"ip\":\"134.201.250.155\",\"latitude\":\"34.0655517578125\",\"longitude\":\"-118.24053955078125\",\"country_name\":\"United States\",\"region_name\":\"California\",\"city\":\"Los Angeles\"}"
                ,response, true);
    }
}
