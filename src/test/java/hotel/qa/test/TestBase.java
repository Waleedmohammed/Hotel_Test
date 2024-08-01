package hotel.qa.test;

import com.microsoft.playwright.Browser;
import hotel.qa.test.core.conf.AppProperties;
import hotel.qa.test.core.conf.BrowserProperties;
import hotel.qa.test.core.conf.TestProperties;
import hotel.qa.test.core.factory.BasePage;
import hotel.qa.test.core.factory.PageManager;
import hotel.qa.test.pages.admin.common.AdminHeader;
import hotel.qa.test.pages.admin.login.AdminLogin;
import hotel.qa.test.pages.admin.main.AdminMain;
import hotel.qa.test.pages.admin.room.AdminRoom;
import hotel.qa.test.pages.users.main.UserMain;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.lang.reflect.Method;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestBase extends AbstractTestNGSpringContextTests {

    @Autowired
    protected BrowserProperties browserProperties;

    @Autowired
    protected AppProperties appProperties;

    @Autowired
    protected TestProperties testProperties;

    @Autowired
    protected PageManager pageManager;

    protected BasePage basePage;

    protected AdminLogin adminLogin;

    protected AdminHeader adminHeader;

    protected AdminMain adminMain;

    protected UserMain userMain;

    protected AdminRoom adminRoom;

    Browser.NewContextOptions contextOptions;


    @BeforeMethod
    public void setUp(Method method) {
        // Because we are using Toolkit.getDefaultToolkit().getScreenSize() which is not supporting headless mode
        if (!browserProperties.isHeadless()) {
            System.setProperty("java.awt.headless", "false");
        }
        contextOptions = new Browser.NewContextOptions()
                .setAcceptDownloads(browserProperties.isAcceptDownloads());
        if (browserProperties.isMaximize()) {
            contextOptions.setViewportSize((int) getCurrentScreenDimension().getWidth(), (int) getCurrentScreenDimension().getHeight());

        }
        basePage = pageManager.getPage();
        basePage.start(contextOptions);
        basePage.navigate(appProperties.getAdminUrl());
    }

    // take screenshot when test case fail and add it in the Screenshot folder
    @AfterMethod(alwaysRun = true)
    public void screenshotOnFailure(ITestResult result, Method method) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed!");
            System.out.println("Taking Screenshot....");
            basePage.takeScreenshot();
        }
        basePage.quit();
    }

    private static Dimension getCurrentScreenDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    protected void performSuccessAdminLogin(AdminLogin adminLogin, AdminHeader adminHeader) throws Exception {

        basePage.navigate(appProperties.getAdminUrl());
        adminLogin.verify()
                .verifyAdminPageTitle(testProperties.getAppTitle());
        adminLogin.act()
                .login(appProperties.getAdminUser(), appProperties.getAdminPassword());
        adminHeader.verify()
                .verifyHeaderElementsIsVisible();

        /*login.act()
                .clickLetMeHackBtn()
                .enterUserName(appProperties.getUser())
                .enterPassword(appProperties.getPassword())
                .clickLoginBtn();
*/
    }
}
