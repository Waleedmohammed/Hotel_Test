package hotel.qa.test.pages.admin.common;


import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminHeader {
    private HeaderActController act ;
    private HeaderVerifyController verify ;
    protected BasePage page;

    AdminHeader(BasePage page) {
        this.page = page;
    }

    // it is private constructor to force getting page object via static method getHomePage
    private AdminHeader(BasePage page, HeaderActController act, HeaderVerifyController verify) {
        this.page = page;
        this.act = act;
        this.verify = verify;
    }

    protected  String roomsTabHrf= "a.nav-link[href='#/admin/']";

    protected String reportTab="id=reportLink";

    protected String brandingTab="id=brandingLink";

    public static AdminHeader getHeader(BasePage page) {
        return new AdminHeader(page, new HeaderActController(page), new HeaderVerifyController(page));
    }

    public HeaderActController act() {
        return act;
    }

    public HeaderVerifyController verify() {
        return verify;
    }

}
