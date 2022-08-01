import lombok.Data;

import java.util.UUID;

@Data
public class Client {
    private UUID clientId;
    private Long timestamp;
    private String ip;
}
