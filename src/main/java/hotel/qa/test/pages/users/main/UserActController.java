package hotel.qa.test.pages.users.main;

import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@Slf4j
public class UserActController extends UserMain {

    UserActController(BasePage page) {
        super(page);
    }

    public UserActController waitForHotelLogoLoad() throws Exception {
        await().atMost(10, TimeUnit.SECONDS).until(() -> page.getElementLocatedBy(logoImg).isVisible());
        log.info("Hotel Logo Image is displayed now...");
        return this;
    }

    public UserActController getListOfVisibleRooms() throws Exception {

        int roomsCount = page.getElementsCountBy(roomsList);
        log.info("Current visible rooms count is {} ", roomsCount);
        return this;
    }

    public UserActController typeRoomNumber(String roomNumber) throws Exception {
        page.typeIn(roomNumTxtBox, roomNumber);
        return this;
    }

    public UserActController selectRoomType(String roomType) throws Exception {
        page.selectFromDDL(roomTypeDdl, roomType);
        return this;
    }

    public UserActController selectRoomAccessible(String roomAccessible) throws Exception {
        page.selectFromDDL(roomAccessibleDdl, roomAccessible);
        return this;
    }

    public UserActController typeRoomPrice(String roomPrice) throws Exception {
        page.typeIn(roomPriceTxtBox, roomPrice);
        return this;
    }

    public UserActController checkWifiCheckBox() throws Exception {
        page.check(wifiChBox);
        return this;
    }

    public UserActController checkRefreshmentCheckBox() throws Exception {
        page.check(refreshmentChBox);
        return this;
    }

    public UserActController checkTvCheckBox() throws Exception {
        page.check(tvChBox);
        return this;
    }

    public UserActController checkSafeCheckBox() throws Exception {
        page.check(safeChBox);
        return this;
    }

    public UserActController checkRadioCheckBox() throws Exception {
        page.check(radioChBox);
        return this;
    }

    public UserActController checkViewsCheckBox() throws Exception {
        page.check(viewsChBox);
        return this;
    }

    public UserActController clickCreateRoomButton() throws Exception {
        page.clickOn(createRoomBtn);
        return this;
    }


}
