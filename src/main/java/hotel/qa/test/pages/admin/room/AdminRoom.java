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
    protected String descriptionTxtBox = "id=description";
    protected String imageTxtBox = "id=image";
    protected String updateBtn = "id=update";
    protected String cancelBtn = "id=cancelEdit";

    protected String errorMessage = "//div[@class='alert alert-danger']/p";


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
