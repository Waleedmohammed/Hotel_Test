package hotel.qa.test.core.factory;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import hotel.qa.test.core.conf.BrowserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@Slf4j
@Component
public abstract class BasePage {

    private Page page;
    protected BrowserProperties browserConfig;

    protected static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    protected static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    protected static ThreadLocal<Page> tlPage = new ThreadLocal<>();
    protected static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public BasePage(BrowserProperties browserConfig) {
        this.browserConfig = browserConfig;
    }


    public void start(Browser.NewContextOptions context) {
        log.info("Starting page session...");
        page = init(context);
        page.setDefaultTimeout(browserConfig.getDefaultTimeOut());
    }

    protected abstract Page init(Browser.NewContextOptions context);


    public void quit() {
        if (page != null) {
            getPage().close();
            getBrowserContext().close();
            getBrowser().close();
            getPlaywright().close();
            log.info("Page context closed");
            tlPlaywright.remove();
            tlBrowser.remove();
            tlBrowserContext.remove();
            tlPage.remove();
        }
    }

    public void navigate(String url) {
        page.navigate(url);
        log.info("opened {}", url);
    }

    public String getTitle() {
        String title = page.title();
        log.info("Title is {}", title);
        return title;
    }

    public void clickOn(String locator) throws Exception {
        await().atMost(10, TimeUnit.SECONDS).with().pollInterval(10, TimeUnit.MILLISECONDS)
                .until(() -> page.locator(locator).isVisible());

        try {
            page.locator(locator).click();
            log.info("Element located by " + locator + " is succesfully clicked");
        } catch (Exception e) {
            log.error("Cannot click element located by " + locator);
            e.printStackTrace();
            throw new Exception("Cannot click element located by" + locator);
        }
    }

    public void typeIn(String locator, String text) throws Exception {
        await().atMost(10, TimeUnit.SECONDS).with().pollInterval(10, TimeUnit.MILLISECONDS)
                .until(() -> page.locator(locator).isVisible());

        try {
            page.locator(locator).fill(text);
            log.info("Entered value: " + text + " on element located by " + locator);
        } catch (Exception e) {
            log.error("Cannot enter value in : " + text + " on element located by " + locator);
            throw new Exception("Cannot enter value in element located by : " + locator);
        }
    }


    public boolean isElementVisible(String locator) throws Exception {
        try {
            page.waitForLoadState(LoadState.LOAD);
            boolean visible = page.locator(locator).isVisible();
            log.info("Element located by " + locator + " Visiblity is " + visible);
            return visible;
        } catch (Exception e) {
            log.error("Element located by " + locator + " is not visible");
            e.printStackTrace();
            throw new Exception("Element located by " + locator + " is not visible");
        }
    }

    public void selectFromDDL(String locator, String value) throws Exception {
        await().atMost(10, TimeUnit.SECONDS).with().pollInterval(10, TimeUnit.MILLISECONDS)
                .until(() -> page.locator(locator).isVisible());

        try {
            page.selectOption(locator, value);
            log.info(value + " has been selected from DDL located by " + locator);
        } catch (Exception e) {
            log.error(value + " can not be selected from DDL located by " + locator);
            e.printStackTrace();
            throw new Exception(value + " can not be selected from DDL located by " + locator);
        }
    }

    public String getText(String locator) throws Exception {
        await().atMost(10, TimeUnit.SECONDS).with().pollInterval(10, TimeUnit.MILLISECONDS)
                .until(() -> page.locator(locator).isVisible());
        try {
            String text = page.innerText(locator);
            log.info(text + " retrieved from Element located by " + locator);
            return text;
        } catch (Exception e) {
            log.error("can not be get Text from Element located by " + locator);
            e.printStackTrace();
            throw new Exception("can not be get Text from Element located by" + locator);
        }
    }

    public void check(String locator) throws Exception {
        await().atMost(10, TimeUnit.SECONDS).with().pollInterval(10, TimeUnit.MILLISECONDS)
                .until(() -> page.locator(locator).isVisible());

        try {
            page.check(locator);
            log.info("Checkbox located by {} checked", locator);
        } catch (Exception e) {
            log.info("Checkbox located by {} not checked", locator);
            e.printStackTrace();
            throw new Exception("Checkbox located by " + locator + " not checked");
        }
    }

    public boolean isPageFullyLoaded() {
        Object result = page.evaluate("document.readyState === 'complete' && window.performance.navigation.type === 0");
        log.info("Loading eveluation is {}", result);
        return result instanceof Boolean && (Boolean) result;

    }

    public int getElementsCountBy(String locator) {
        return page.locator(locator).count();
    }

    public List<String> getElementsAttributeValue(String locator, String attribute) {

        List<String> attributeValues = new ArrayList<>();

        int elementCount = page.locator(locator).count();

        for (int i = 0; i < elementCount; i++) {
            String attributeValue = page.locator(locator).nth(i).getAttribute(attribute);
            attributeValues.add(attributeValue);
        }
        return attributeValues;
    }


    public List<String> getAllAvailableOptionsFromUl(String ulLocator) {

        await().atMost(10, TimeUnit.SECONDS).with().pollInterval(10, TimeUnit.MILLISECONDS)
                .until(() -> page.locator(ulLocator).isVisible());

        List<String> listItems = new ArrayList<>();
        Locator liElements = page.locator(ulLocator).locator("li");

        if (liElements.count() > 0) {
            for (int i = 0; i < liElements.count(); i++) {
                String listItemText = liElements.nth(i).textContent();
                listItems.add(listItemText);
            }
            log.info("Room available options are {}", listItems);
            return listItems;
        } else {
            log.info("There is not available Options for that given Room ");
            return null;
        }
    }

    public Locator getElementLocatedBy(String locator) {
        await().atMost(10, TimeUnit.SECONDS).with().pollInterval(10, TimeUnit.MILLISECONDS)
                .until(() -> page.locator(locator).isVisible());
        log.info("Returning element located by {}", locator);
        return page.locator(locator);
    }

    public String takeScreenshot() {

        String path = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }
}
