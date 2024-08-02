package hotel.qa.test.CRUD;

import hotel.qa.test.TestBase;
import hotel.qa.test.helper.TestHelpers;
import hotel.qa.test.utils.RoomAccessible;
import hotel.qa.test.utils.RoomType;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static hotel.qa.test.pages.admin.common.AdminHeader.getHeader;
import static hotel.qa.test.pages.admin.login.AdminLogin.getLogin;
import static hotel.qa.test.pages.admin.main.AdminMain.getMain;
import static hotel.qa.test.pages.users.main.UserMain.getUserMain;

@Slf4j
public class CreatRoomTests extends TestBase {


    @Test
    @Description("Test That Admin User Can add new room successfully")
    @Feature("Create Room Feature")
    @Story("Create Room with valid test data")
    public void test_create_room_by_admin() throws Exception {

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);
        userMain = getUserMain(basePage);

        // Perform success admin login
        adminLogin.act()
                .login(appProperties.getAdminUser(), appProperties.getAdminPassword());
        adminLogin.verify()
                .verifyAdminPageTitle(testProperties.getAppTitle());
        adminHeader.verify()
                .verifyHeaderElementsIsVisible();

        // Creating test data for roomNumber and roomPrice
        String roomNumber = TestHelpers.getRandomNumeric(3);
        String roomPrice = TestHelpers.getRandomNumeric(3);

        // Execute create new room steps
        adminMain.act()
                .typeRoomNumber(roomNumber)
                .selectRoomType(RoomType.FAMILY.getRoomType())
                .selectRoomAccessible(RoomAccessible.FALSE.getRoomAccessible())
                .typeRoomPrice(roomPrice)
                .checkRadioCheckBox()
                .checkTvCheckBox()
                .checkRefreshmentCheckBox()
                .clickCreateRoomButton();

        // Verify that room created successfully and visible on available rooms list
        adminMain.verify()
                .verifyCreatedRoomIsVisible(roomNumber,
                        RoomType.FAMILY.getRoomType(),
                        RoomAccessible.FALSE.getRoomAccessible(),
                        roomPrice);


        // Cleanup for test records from system
        adminMain.act()
                .clickDeleteRoomIcon(roomNumber);
        adminMain.verify()
                .verifyDeletedRoomNumberIsNotVisible(roomNumber);

    }

    @Test
    @Description("Test That Admin User Can not add new room without passing room number")
    @Feature("Create Room Feature")
    @Story("Create Room with empty room number")
    public void test_create_room_without_number() throws Exception {

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);

        adminLogin.act()
                .login(appProperties.getAdminUser(), appProperties.getAdminPassword());

        adminLogin.verify()
                .verifyAdminPageTitle(testProperties.getAppTitle());

        adminHeader.verify()
                .verifyHeaderElementsIsVisible();

        String roomPrice = TestHelpers.getRandomNumeric(3);

        adminMain.act()
                .selectRoomType(RoomType.FAMILY.getRoomType())
                .selectRoomAccessible(RoomAccessible.FALSE.getRoomAccessible())
                .typeRoomPrice(roomPrice)
                .checkRadioCheckBox()
                .checkTvCheckBox()
                .checkRefreshmentCheckBox()
                .clickCreateRoomButton();

        adminMain.verify()
                .verifyErrorMessageDisplayed(testProperties.getErrorRoomNumber());

    }

    @Test
    @Description("Test That Admin User Can not add new room without passing room price")
    @Feature("Create Room Feature")
    @Story("Create Room with empty room price")
    public void test_create_room_without_price() throws Exception {

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);

        adminLogin.act()
                .login(appProperties.getAdminUser(), appProperties.getAdminPassword());

        adminLogin.verify()
                .verifyAdminPageTitle(testProperties.getAppTitle());

        adminHeader.verify()
                .verifyHeaderElementsIsVisible();

        String roomNumber = TestHelpers.getRandomNumeric(3);

        adminMain.act()
                .typeRoomNumber(roomNumber)
                .selectRoomType(RoomType.FAMILY.getRoomType())
                .selectRoomAccessible(RoomAccessible.FALSE.getRoomAccessible())
                .checkRadioCheckBox()
                .checkTvCheckBox()
                .checkRefreshmentCheckBox()
                .clickCreateRoomButton();

        adminMain.verify()
                .verifyErrorMessageDisplayed(testProperties.getErrorRoomPrice());

    }
}
