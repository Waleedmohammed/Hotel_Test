package hotel.qa.test.CRUD;

import hotel.qa.test.TestBase;
import hotel.qa.test.helper.TestHelpers;
import hotel.qa.test.utils.RoomAccessible;
import hotel.qa.test.utils.RoomType;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static hotel.qa.test.pages.admin.common.AdminHeader.getHeader;
import static hotel.qa.test.pages.admin.login.AdminLogin.getLogin;
import static hotel.qa.test.pages.admin.main.AdminMain.getMain;
import static hotel.qa.test.pages.users.main.UserMain.getUserMain;

@Slf4j
public class CreatRoomTests extends TestBase {


    @Test
    public void test_create_room_by_admin() throws Exception {

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);
        userMain = getUserMain(basePage);

        adminLogin.act()
                .login(appProperties.getAdminUser(), appProperties.getAdminPassword());

        adminLogin.verify()
                .verifyAdminPageTitle(testProperties.getAppTitle());

        adminHeader.verify()
                .verifyHeaderElementsIsVisible();

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
                .clickCreateRoomButton();

        adminMain.verify()
                .verifyCreatedRoomIsVisible(roomNumber,
                        RoomType.FAMILY.getRoomType(),
                        RoomAccessible.FALSE.getRoomAccessible(),
                        roomPrice);

        adminMain.act()
                .clickDeleteRoomIcon(roomNumber);
    }

    @Test
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

        roomPrice = TestHelpers.getRandomNumeric(3);

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

        roomNumber = TestHelpers.getRandomNumeric(3);

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
