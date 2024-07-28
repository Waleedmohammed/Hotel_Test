package hotel.qa.test.CRUD;

import hotel.qa.test.TestBase;
import hotel.qa.test.helper.TestHelpers;
import hotel.qa.test.pages.admin.room.AdminRoom;
import hotel.qa.test.utils.RoomAccessible;
import hotel.qa.test.utils.RoomOptions;
import hotel.qa.test.utils.RoomType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static hotel.qa.test.pages.admin.common.AdminHeader.getHeader;
import static hotel.qa.test.pages.admin.login.AdminLogin.getLogin;
import static hotel.qa.test.pages.admin.main.AdminMain.getMain;
import static hotel.qa.test.pages.admin.room.AdminRoom.getRoom;
import static hotel.qa.test.pages.users.main.UserMain.getUserMain;

public class UpdateTests extends TestBase {

    @Test
    public void test_update_room_by_admin() throws Exception {
        basePage.navigate(appProperties.getAdminUrl());

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);
        userMain = getUserMain(basePage);
        adminRoom = getRoom(basePage);

        roomNumber = TestHelpers.getRandomNumeric(3);
        roomPrice = TestHelpers.getRandomNumeric(3);

        adminLogin.act()
                .login(appProperties.getAdminUser(), appProperties.getAdminPassword());

        adminLogin.verify()
                .verifyAdminPageTitle(testProperties.getAppTitle());

        adminHeader.verify()
                .verifyHeaderElementsIsVisible();

        adminMain.act()
                .typeRoomNumber(roomNumber)
                .selectRoomType(RoomType.FAMILY.getRoomType())
                .selectRoomAccessible(RoomAccessible.FALSE.getRoomAccessible())
                .typeRoomPrice(roomPrice)
                .checkRadioCheckBox()
                .checkTvCheckBox()
                .checkRefreshmentCheckBox()
                .checkWifiCheckBox()
                .checkViewsCheckBox()
                .checkSafeCheckBox()
                .clickCreateRoomButton();

        basePage.navigate(appProperties.getMainUrl());

        userMain.act()
                .waitForHotelLogoLoad()
                .getListOfVisibleRooms();


        List<String> expectedRoomOptions = new ArrayList<>();
        expectedRoomOptions.add(RoomOptions.RADIO.getRoomOption());
        expectedRoomOptions.add(RoomOptions.TV.getRoomOption());
        expectedRoomOptions.add(RoomOptions.REFRESHMENTS.getRoomOption());
        expectedRoomOptions.add(RoomOptions.WIFI.getRoomOption());
        expectedRoomOptions.add(RoomOptions.VIEWS.getRoomOption());
        expectedRoomOptions.add(RoomOptions.SAFE.getRoomOption());

        userMain.verify()
                .verifyRoomIsExistWithRoomNumber(roomNumber)
                .verifyRoomIsExistWithType(RoomType.FAMILY.getRoomType())
                .verifyRoomIsExistWithOptions(expectedRoomOptions);

        basePage.navigate(appProperties.getAdminUrl());

        adminMain.act()
                .clickOnCreatedRoom();

        adminRoom.act()
                .clickEditButton();

        Thread.sleep(20000);
    }
}
