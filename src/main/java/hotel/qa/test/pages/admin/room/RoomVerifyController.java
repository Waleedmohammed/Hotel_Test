package hotel.qa.test.pages.admin.room;

import hotel.qa.test.core.factory.BasePage;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class RoomVerifyController extends AdminRoom {
    RoomVerifyController(BasePage page) {
        super(page);
    }

    public RoomVerifyController verifyErrorMessageDisplayed(String expectedErrorMessage) throws Exception {
        Assert.assertEquals(page.getText(errorMessage), expectedErrorMessage, "Expected Error Message doesn't match Actual Message");
        return this;
    }

}
