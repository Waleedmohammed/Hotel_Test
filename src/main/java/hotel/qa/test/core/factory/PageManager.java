package hotel.qa.test.core.factory;

import hotel.qa.test.core.conf.BrowserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PageManager {
    private BrowserProperties config;

    public PageManager(BrowserProperties config) {
        this.config = config;
    }

    public BasePage getPage() {
        return getPage(config.getName());
    }

    public BasePage getPage(String name) {
        return PageFactory.fromValue(name).getPage(config);
    }
}


