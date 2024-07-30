package hotel.qa.test.core.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "browser")
public class BrowserProperties {

    private String name;
    private boolean Headless;
    private boolean SlowMotion;
    private int SlowMotionMs;
    private boolean Maximize;
    private boolean acceptDownloads;
    private String downloadPath;
    private long defaultTimeOut;
}
