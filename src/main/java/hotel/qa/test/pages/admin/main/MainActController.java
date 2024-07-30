package hotel.qa.test.pages.admin.main;

import hotel.qa.test.core.factory.BasePage;
import hotel.qa.test.utils.RoomOptions;
import hotel.qa.test.utils.SelectedRoomOptions;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;


@Slf4j
public class MainActController extends AdminMain {

    MainActController(BasePage page) {
        super(page);
    }

    public MainActController getCurrentRoomsList() {
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
        SelectedRoomOptions.getInstance().addItem(RoomOptions.WIFI.getRoomOption());
        return this;
    }

    public MainActController checkRefreshmentCheckBox() throws Exception {
        page.check(refreshmentChBox);
        SelectedRoomOptions.getInstance().addItem(RoomOptions.REFRESHMENTS.getRoomOption());
        return this;
    }

    public MainActController checkTvCheckBox() throws Exception {
        page.check(tvChBox);
        SelectedRoomOptions.getInstance().addItem(RoomOptions.TV.getRoomOption());
        return this;
    }

    public MainActController checkSafeCheckBox() throws Exception {
        page.check(safeChBox);
        SelectedRoomOptions.getInstance().addItem(RoomOptions.SAFE.getRoomOption());
        return this;
    }

    public MainActController checkRadioCheckBox() throws Exception {
        page.check(radioChBox);
        SelectedRoomOptions.getInstance().addItem(RoomOptions.RADIO.getRoomOption());
        return this;
    }

    public MainActController checkViewsCheckBox() throws Exception {
        page.check(viewsChBox);
        SelectedRoomOptions.getInstance().addItem(RoomOptions.VIEWS.getRoomOption());
        return this;
    }

    public MainActController clickCreateRoomButton() throws Exception {
        page.clickOn(createRoomBtn);
        return this;
    }

    public MainActController clickOnCreatedRoom(String roomNumber) throws Exception {
        page.clickOn(getRoomNumberLbl(roomNumber));
        return this;
    }

    public MainActController clickDeleteRoomIcon(String roomNumber) throws Exception {
        page.clickOn(getDeleteRoomIcon(getRoomSystemId(roomNumber)));
        return this;
    }


}
