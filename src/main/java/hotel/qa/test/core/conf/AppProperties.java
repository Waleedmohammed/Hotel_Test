package hotel.qa.test.core.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String adminUrl;
    private String adminUser;
    private String adminPassword;
    private String appTitle;
    private String mainUrl;

}
