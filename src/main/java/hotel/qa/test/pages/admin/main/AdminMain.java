package hotel.qa.test.pages.admin.main;


import com.microsoft.playwright.Locator;
import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class
AdminMain {
    private MainActController act;
    private MainVerifyController verify;

    protected BasePage page;
    protected int roomListCount;

    AdminMain(BasePage page) {
        this.page = page;
    }

    // it is private constructor to force getting page object via static method getHomePage
    private AdminMain(BasePage page, MainActController act, MainVerifyController verify) {
        this.page = page;
        this.act = act;
        this.verify = verify;
    }


    protected String roomsList = "[data-testid='roomlisting']";

    protected String roomNumTxtBox = "id=roomName";

    protected String roomTypeDdl = "id=type";

    protected String roomAccessibleDdl = "id=accessible";

    protected String roomPriceTxtBox = "id=roomPrice";

    protected String wifiChBox = "id=wifiCheckbox";

    protected String refreshmentChBox = "id=refreshCheckbox";

    protected String tvChBox = "id=tvCheckbox";

    protected String safeChBox = "id=safeCheckbox";

    protected String radioChBox = "id=radioCheckbox";

    protected String viewsChBox = "id=viewsCheckbox";

    protected String createRoomBtn = "id=createRoom";

    protected String errorMessage = "div.alert.alert-danger p";


    public static AdminMain getMain(BasePage page) {
        return new AdminMain(page, new MainActController(page), new MainVerifyController(page));
    }

    public MainActController act() {
        return act;
    }

    public MainVerifyController verify() {
        return verify;
    }

    protected String getRoomNumberLbl(String roomNumber) {
        return "p#roomName" + roomNumber;
    }

    protected String getRoomTypeLbl(String roomId) {
        return "div[id='room" + roomId + "'] #typeFamily";
    }

    protected String getRoomAccessibleLbl(String roomId) {
        return "div[id='room" + roomId + "'] #accessiblefalse";
    }

    protected String getRoomPriceLbl(String roomId, String roomPrice) {
        return "div[id='room" + roomId + "'] #roomPrice" + roomPrice;
    }

    protected String getRoomDetailsLbl(String roomId) {
        return "div[id='room" + roomId + "'] div.col-sm-5";
    }

    protected String getDeleteRoomIcon(String roomId) {
        return "div[id='room" + roomId + "'] span.fa.fa-remove.roomDelete";
    }

    protected String getRoomSystemId(String roomNumber) {
        Locator roomNumberElement = page.getElementLocatedBy(getRoomNumberLbl(roomNumber));
        return roomNumberElement.locator("..").locator("..").getAttribute("id").substring(4);
    }

}
