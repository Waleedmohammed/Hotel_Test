package hotel.qa.test.pages.admin.room;

import hotel.qa.test.core.factory.BasePage;
import org.testng.Assert;

public class RoomVerifyController extends AdminRoom {
    RoomVerifyController(BasePage page) {
        super(page);
    }

    public RoomVerifyController verifyAdminPageTitle(String expectedTitle) {
        Assert.assertEquals(page.getTitle(),expectedTitle,"Expected Page title doesn't match actual title ... ");
        return this;
    }

}
