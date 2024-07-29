package hotel.qa.test.core.factory.web;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import hotel.qa.test.core.conf.BrowserProperties;
import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebFirFoxPage extends BasePage {

    public WebFirFoxPage(BrowserProperties config) {
        super(config);
    }

    @Override
    protected Page init(Browser.NewContextOptions context) {
        tlPlaywright.set(Playwright.create());
        tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions()
                .setHeadless(browserConfig.isHeadless())));
                //.setSlowMo(browserConfig.getSlowMotionMs())));
        if (context != null) {
            tlBrowserContext.set(getBrowser().newContext(context));
        } else {
            tlBrowserContext.set(getBrowser().newContext());
        }
        tlPage.set(getBrowserContext().newPage());
        return getPage();
    }

}
