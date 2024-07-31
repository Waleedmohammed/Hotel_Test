package hotel.qa.test.pages.users.main;


import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class
UserMain {
    private UserActController act;
    private UserVerifyController verify;
    protected BasePage page;
    protected int roomListCount;

    UserMain(BasePage page) {
        this.page = page;
    }

    // it is private constructor to force getting page object via static method getHomePage
    private UserMain(BasePage page, UserActController act, UserVerifyController verify) {
        this.page = page;
        this.act = act;
        this.verify = verify;
    }

    protected String logoImg = "img.hotel-logoUrl";

    protected String roomsList = "img.img-responsive.hotel-img";


    public static UserMain getUserMain(BasePage page) {
        return new UserMain(page, new UserActController(page), new UserVerifyController(page));
    }

    public UserActController act() {
        return act;
    }

    public UserVerifyController verify() {
        return verify;
    }

    protected String getVisibleRoomType(int roomNumberInTheList) {
        return "(//div[@class='row hotel-room-info'])[" + roomNumberInTheList + "]/div[@class='col-sm-7']/h3";
    }

    protected String getVisibleRoomOptions(int roomNumberInTheList) {
        return "(//div[@class='row hotel-room-info'])[" + roomNumberInTheList + "]/div[@class='col-sm-7']/ul";
    }


}
