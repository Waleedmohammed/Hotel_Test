package hotel.qa.test.pages.admin.login;

import hotel.qa.test.core.factory.BasePage;
import org.testng.Assert;

public class LoginVerifyController extends AdminLogin {
    LoginVerifyController(BasePage page) {
        super(page);
    }

    public LoginVerifyController verifyAdminPageTitle(String expectedTitle) {
        Assert.assertEquals(page.getTitle(),expectedTitle,"Expected Page title doesn't match actual title ... ");
        return this;
    }

}
