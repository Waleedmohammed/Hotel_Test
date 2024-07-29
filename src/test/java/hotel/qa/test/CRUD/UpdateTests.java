package hotel.qa.test.CRUD;

import hotel.qa.test.TestBase;
import hotel.qa.test.helper.TestHelpers;
import hotel.qa.test.utils.RoomAccessible;
import hotel.qa.test.utils.RoomType;
import org.testng.annotations.Test;


import static hotel.qa.test.pages.admin.common.AdminHeader.getHeader;
import static hotel.qa.test.pages.admin.login.AdminLogin.getLogin;
import static hotel.qa.test.pages.admin.main.AdminMain.getMain;
import static hotel.qa.test.pages.admin.room.AdminRoom.getRoom;
import static hotel.qa.test.pages.users.main.UserMain.getUserMain;

public class UpdateTests extends TestBase {

    @Test
    public void test_update_room_by_admin() throws Exception {

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);
        userMain = getUserMain(basePage);
        adminRoom = getRoom(basePage);

        performAdminLogin(adminLogin, adminHeader);

        roomNumber = TestHelpers.getRandomNumeric(3);
        roomPrice = TestHelpers.getRandomNumeric(3);

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

        adminMain.verify()
                .verifyCreatedRoomIsVisible(roomNumber,
                        RoomType.FAMILY.getRoomType(),
                        RoomAccessible.FALSE.getRoomAccessible(),
                        roomPrice);

        adminMain.act()
                .clickOnCreatedRoom(roomNumber);

        roomNumber = TestHelpers.getRandomNumeric(3);
        roomPrice = TestHelpers.getRandomNumeric(3);

        adminRoom.act()
                .clickEditButton()
                .updateAllRoomDetails(roomNumber,
                        RoomType.SUITE.getRoomType(),
                        RoomAccessible.TRUE.getRoomAccessible(),
                        roomNumber,
                        testProperties.getNewRoomDescription(),
                        testProperties.getNewRoomUrl())
                .clickUpdateButton();

        basePage.navigate(appProperties.getMainUrl());

        userMain.act()
                .waitForHotelLogoLoad()
                .getListOfVisibleRooms();

        userMain.verify()
                .verifyRoomIsExistWithRoomNumber(roomNumber)
                .verifyRoomIsExistWithType(RoomType.SUITE.getRoomType())
                .verifyRoomIsExistWithoutOptions();

        basePage.navigate(appProperties.getAdminUrl());

        adminMain.act()
                .clickDeleteRoomIcon(roomNumber);
    }


    @Test
    public void test_update_room_by_admin_with_empty_room_number() throws Exception {

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);
        userMain = getUserMain(basePage);
        adminRoom = getRoom(basePage);

        performAdminLogin(adminLogin, adminHeader);

        roomNumber = TestHelpers.getRandomNumeric(3);
        roomPrice = TestHelpers.getRandomNumeric(3);


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

        adminMain.verify()
                .verifyCreatedRoomIsVisible(roomNumber,
                        RoomType.FAMILY.getRoomType(),
                        RoomAccessible.FALSE.getRoomAccessible(),
                        roomPrice);

        adminMain.act()
                .clickOnCreatedRoom(roomNumber);

        adminRoom.act()
                .clickEditButton()
                .clearRoomNumber()
                .clickUpdateButton();

        adminRoom.verify()
                .verifyErrorMessageDisplayed(testProperties.getErrorRoomNumber());

    }

    @Test
    public void test_update_room_by_admin_with_empty_room_price() throws Exception {

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);
        userMain = getUserMain(basePage);
        adminRoom = getRoom(basePage);

        performAdminLogin(adminLogin, adminHeader);

        roomNumber = TestHelpers.getRandomNumeric(3);
        roomPrice = TestHelpers.getRandomNumeric(3);


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

        adminMain.verify()
                .verifyCreatedRoomIsVisible(roomNumber,
                        RoomType.FAMILY.getRoomType(),
                        RoomAccessible.FALSE.getRoomAccessible(),
                        roomPrice);

        adminMain.act()
                .clickOnCreatedRoom(roomNumber);

        adminRoom.act()
                .clickEditButton()
                .clearRoomPrice()
                .clickUpdateButton();

        adminRoom.verify()
                .verifyErrorMessageDisplayed(testProperties.getErrorRoomPrice());

    }
}
