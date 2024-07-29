package hotel.qa.test.pages.admin.main;

import com.microsoft.playwright.Locator;
import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;


@Slf4j
public class MainActController extends AdminMain {

    MainActController(BasePage page) {
        super(page);
    }

    public MainActController getCurrentRoomsList() throws Exception {
        await().atMost(10, TimeUnit.SECONDS).until(() -> page.getElementLocatedBy(wifiChBox).isVisible());

        roomListCount = page.getElementsCountBy(roomsList);
        log.info("Current room list Count is {}", roomListCount);
        return this;
    }

    public MainActController typeRoomNumber(String roomNumber) throws Exception {
        page.typeIn(roomNumTxtBox, roomNumber);
        log.info("Room will be created with Room Number {}", roomNumber);
        return this;
    }

    public MainActController selectRoomType(String roomType) throws Exception {
        page.selectFromDDL(roomTypeDdl, roomType);
        log.info("Room will be created with Room Type {}", roomType);
        return this;
    }

    public MainActController selectRoomAccessible(String roomAccessible) throws Exception {
        page.selectFromDDL(roomAccessibleDdl, roomAccessible);
        log.info("Room will be created with Room Accessible {}", roomAccessible);
        return this;
    }

    public MainActController typeRoomPrice(String roomPrice) throws Exception {
        page.typeIn(roomPriceTxtBox, roomPrice);
        log.info("Room will be created with Room Price {}", roomPrice);
        return this;
    }

    public MainActController checkWifiCheckBox() throws Exception {
        page.check(wifiChBox);
        return this;
    }

    public MainActController checkRefreshmentCheckBox() throws Exception {
        page.check(refreshmentChBox);
        return this;
    }

    public MainActController checkTvCheckBox() throws Exception {
        page.check(tvChBox);
        return this;
    }

    public MainActController checkSafeCheckBox() throws Exception {
        page.check(safeChBox);
        return this;
    }

    public MainActController checkRadioCheckBox() throws Exception {
        page.check(radioChBox);
        return this;
    }

    public MainActController checkViewsCheckBox() throws Exception {
        page.check(viewsChBox);
        return this;
    }

    public MainActController clickCreateRoomButton() throws Exception {
        page.clickOn(createRoomBtn);
        return this;
    }

    public MainActController clickOnCreatedRoom() throws Exception {
        page.getLastElementWith(roomsList).click();
        return this;
    }

    public MainActController clickDeleteRoomIcon(String roomNumber) throws Exception {
        Locator roomNumberElement = page.getElementLocatedBy(getRoomNumberLbl(roomNumber));
        String roomNumberId = roomNumberElement.locator("..").locator("..").getAttribute("id").substring(4);
        page.clickOn(getDeleteRoomIcon(roomNumberId));
        return this;
    }


}
