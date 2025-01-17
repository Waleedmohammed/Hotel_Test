package hotel.qa.test.pages.admin.common;

import hotel.qa.test.core.factory.BasePage;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class HeaderVerifyController extends AdminHeader {
    HeaderVerifyController(BasePage page) {
        super(page);
    }

    public HeaderVerifyController verifyHeaderElementsIsVisible() throws Exception {

        await().atMost(10, TimeUnit.SECONDS).with().pollInterval(1, TimeUnit.SECONDS)
                .until(() -> page.getElementLocatedBy(reportTab).isVisible());

        if (page.isPageFullyLoaded()) {
            Assert.assertTrue((page.isElementVisible(roomsTabHrf)), "Rooms Tab is not visible");
            Assert.assertTrue(page.isElementVisible(reportTab), "Report Tab is not visible");
            Assert.assertTrue(page.isElementVisible(brandingTab), "Branding Tab is not visible");
        }
        return this;
    }
}
