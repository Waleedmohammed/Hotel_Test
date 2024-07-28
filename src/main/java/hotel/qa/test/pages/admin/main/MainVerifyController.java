package hotel.qa.test.pages.admin.main;

import hotel.qa.test.core.factory.BasePage;
import hotel.qa.test.pages.users.main.UserVerifyController;
import org.testng.Assert;

public class MainVerifyController extends AdminMain {
    MainVerifyController(BasePage page) {
        super(page);
    }

    public MainVerifyController verifyErrorMessageDisplayed(String expectedErrorMessage) {
        Assert.assertEquals(page.getLastElementText(errorMessage), expectedErrorMessage, "Expected Error Message doesn't match Actual Message");
        return this;
    }
}
