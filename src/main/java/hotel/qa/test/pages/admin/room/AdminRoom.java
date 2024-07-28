package hotel.qa.test.pages.admin.room;


import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminRoom {
    private RoomActController act ;
    private RoomVerifyController verify ;

    protected BasePage page;

    AdminRoom(BasePage page) {
        this.page = page;
    }

    // it is private constructor to force getting page object via static method getHomePage
    private AdminRoom(BasePage page, RoomActController act, RoomVerifyController verify) {
        this.page = page;
        this.act = act;
        this.verify = verify;
    }

    protected  String editBtn= "//button[@class='btn btn-outline-primary float-right']";

    public static AdminRoom getRoom(BasePage page) {
        return new AdminRoom(page, new RoomActController(page), new RoomVerifyController(page));
    }

    public RoomActController act() {
        return act;
    }

    public RoomVerifyController verify() {
        return verify;
    }

}
