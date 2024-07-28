package hotel.qa.test.core.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "test")
public class TestProperties {

    private String errorRoomNumber;
    private String errorRoomPrice;
}
