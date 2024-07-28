package hotel.qa.test.pages.admin.common;

import hotel.qa.test.core.factory.BasePage;
import org.testng.Assert;

public class HeaderVerifyController extends AdminHeader {
    HeaderVerifyController(BasePage page) {
        super(page);
    }

    public HeaderVerifyController verifyHeaderElementsIsVisible() throws Exception {
        page.waitForSelector(reportTab);
        if(page.isPageFullyLoaded()){
            Assert.assertTrue((page.getElementByHrefLocator(roomsTabHrf).isVisible()), "Create Room button is not visible");
            Assert.assertTrue(page.isElementVisible(reportTab), "Create Room button is not visible");
            Assert.assertTrue(page.isElementVisible(brandingTab), "Branding Tab is not visible");
        }
        return this;
    }
}
