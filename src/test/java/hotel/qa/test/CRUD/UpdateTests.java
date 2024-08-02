package hotel.qa.test.CRUD;

import hotel.qa.test.TestBase;
import hotel.qa.test.helper.TestHelpers;
import hotel.qa.test.utils.RoomAccessible;
import hotel.qa.test.utils.RoomType;
import io.qameta.allure.*;
import org.testng.annotations.Test;


import static hotel.qa.test.pages.admin.common.AdminHeader.getHeader;
import static hotel.qa.test.pages.admin.login.AdminLogin.getLogin;
import static hotel.qa.test.pages.admin.main.AdminMain.getMain;
import static hotel.qa.test.pages.admin.room.AdminRoom.getRoom;
import static hotel.qa.test.pages.users.main.UserMain.getUserMain;

public class UpdateTests extends TestBase {

    @Test
    @Description("Test That Admin User Can update existing room successfully")
    @Feature("Update Room Feature")
    @Story("Update Existing Room with valid test data")
    public void test_update_room_by_admin() throws Exception {

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);
        userMain = getUserMain(basePage);
        adminRoom = getRoom(basePage);

        // Perform success Admin login
        performSuccessAdminLogin(adminLogin, adminHeader);

        // Creating test data for roomNumber and roomPrice
        String roomNumber = TestHelpers.getRandomNumeric(3);
        String roomPrice = TestHelpers.getRandomNumeric(3);

        // Execute create new room steps as requisite for updating it
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

        // Verify that room created successfully and visible on available rooms list
        adminMain.verify()
                .verifyCreatedRoomIsVisible(roomNumber,
                        RoomType.FAMILY.getRoomType(),
                        RoomAccessible.FALSE.getRoomAccessible(),
                        roomPrice);

        // Click on created room to edit it
        adminMain.act()
                .clickOnCreatedRoom(roomNumber);

        // Creating new test data for updating roomNumber and roomPrice
        String newRoomNumber = TestHelpers.getRandomNumeric(3);

        // Execute Update room steps
        adminRoom.act()
                .clickEditButton()
                .updateAllRoomDetails(newRoomNumber,
                        RoomType.SUITE.getRoomType(),
                        RoomAccessible.TRUE.getRoomAccessible(),
                        newRoomNumber,
                        testProperties.getNewRoomDescription(),
                        testProperties.getNewRoomUrl())
                .clickUpdateButton();

        // Navigate to main system page
        basePage.navigate(appProperties.getMainUrl());

        userMain.act()
                .waitForHotelLogoLoad()
                .getListOfVisibleRooms();

        // Verify that updated room is visible with updated data
        userMain.verify()
                .verifyRoomIsExistWithRoomNumber(newRoomNumber)
                .verifyRoomIsExistWithType(RoomType.SUITE.getRoomType())
                .verifyRoomIsExistWithoutOptions();

        // Go back to admin page to clean up test record
        basePage.navigate(appProperties.getAdminUrl());

        adminMain.act()
                .clickDeleteRoomIcon(newRoomNumber);
        adminMain.verify()
                .verifyDeletedRoomNumberIsNotVisible(newRoomNumber);
    }


    @Test
    @Description("Test That Admin User should not update room with empty number")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Update Room Feature")
    @Story("Update Room with empty number")
    public void test_update_room_by_admin_with_empty_room_number() throws Exception {

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);
        userMain = getUserMain(basePage);
        adminRoom = getRoom(basePage);

        performSuccessAdminLogin(adminLogin, adminHeader);

        String roomNumber = TestHelpers.getRandomNumeric(3);
        String roomPrice = TestHelpers.getRandomNumeric(3);


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
    @Description("Test That Admin User should not update room with empty price")
    @Feature("Update Room Feature")
    @Story("Update Room with empty price")
    public void test_update_room_by_admin_with_empty_room_price() throws Exception {

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);
        userMain = getUserMain(basePage);
        adminRoom = getRoom(basePage);

        performSuccessAdminLogin(adminLogin, adminHeader);

        String roomNumber = TestHelpers.getRandomNumeric(3);
        String roomPrice = TestHelpers.getRandomNumeric(3);


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
