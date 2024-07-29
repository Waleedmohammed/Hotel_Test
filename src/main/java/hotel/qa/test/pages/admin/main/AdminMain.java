package hotel.qa.test.pages.admin.main;


import com.microsoft.playwright.Locator;
import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

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

    protected String roomsGrid = "//div[@class='container']";

    protected String roomsList = "//div[@class='row detail']";

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

    protected String errorMessage = "//div[@class='alert alert-danger']/p";

    protected String roomNumberLbl = "//div[@class='alert alert-danger']/p";

    protected String deleteIcon = "//span[@class='fa fa-remove roomDelete']";

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
        return "//p[@id='roomName" + roomNumber + "']";
    }

    protected String getRoomTypeLbl(String roomId) {
        return "//div[@id='room" + roomId + "']/div[@class='col-sm-2']";
    }

    protected String getRoomAccessibleLbl(String roomId) {
        return "//div[@id='room" + roomId + "']/div[@class='col-sm-2']";
    }

    protected String getRoomPriceLbl(String roomNumber) {
        return "//p[@id='roomPrice" + roomNumber + "']";
    }

    protected String getRoomDetailsLbl(String roomId) {
        return "//div[@id='room" + roomId + "']/div[@class='col-sm-5']/p";
    }

    protected String getDeleteRoomIcon(String roomNumber) {
        return "//span[@id='" + roomNumber + "']";
    }

    protected String getRoomSystemId(String roomNumber) {
        Locator roomNumberElement = page.getElementLocatedBy(getRoomNumberLbl(roomNumber));
        return roomNumberElement.locator("..").locator("..").getAttribute("id").substring(4);
    }

}
