import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationAPI {
    private String clientId;
    private Long timestamp;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("country_name")
    private String country_name;
    @JsonProperty("region_name")
    private String region_name;
    @JsonProperty("city")
    private String city;
}
