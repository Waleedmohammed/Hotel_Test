package hotel.qa.test.pages.admin.login;

import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginActController extends AdminLogin {

    LoginActController(BasePage page) {
        super(page);
    }

    public LoginActController clickLetMeHackBtn() throws Exception {
        page.clickOn(letMeHackBtn);
        return this;
    }

    public LoginActController enterUserName(String userName) throws Exception {
        page.typeIn(userNameTxtBox, userName);
        return this;
    }

    public LoginActController login(String userName, String password) throws Exception {
        page.clickOn(letMeHackBtn);
        page.typeIn(userNameTxtBox, userName);
        page.typeIn(passwordTxtBox, password);
        page.clickOn(loginBtn);
        return this;
    }

    public LoginActController enterPassword(String password) throws Exception {
        page.typeIn(passwordTxtBox, password);
        return this;
    }

    public void clickLoginBtn() throws Exception {
        page.clickOn(loginBtn);
    }

}
