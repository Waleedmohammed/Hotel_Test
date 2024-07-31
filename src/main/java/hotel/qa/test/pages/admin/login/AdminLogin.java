package hotel.qa.test.pages.admin.login;


import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminLogin {
    private LoginActController act ;
    private LoginVerifyController verify ;

    protected BasePage page;

    AdminLogin(BasePage page) {
        this.page = page;
    }

    // it is private constructor to force getting page object via static method getHomePage
    private AdminLogin(BasePage page, LoginActController act, LoginVerifyController verify) {
        this.page = page;
        this.act = act;
        this.verify = verify;
    }

    protected  String letMeHackBtn= "button.btn.btn-primary";

    protected  String userNameTxtBox= "id=username";

    protected  String passwordTxtBox= "id=password";

    protected  String loginBtn= "id=doLogin";


    public static AdminLogin getLogin(BasePage page) {
        return new AdminLogin(page, new LoginActController(page), new LoginVerifyController(page));
    }

    public LoginActController act() {
        return act;
    }

    public LoginVerifyController verify() {
        return verify;
    }

}
