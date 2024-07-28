package hotel.qa.test.CRUD;

import hotel.qa.test.TestBase;
import hotel.qa.test.helper.TestHelpers;
import hotel.qa.test.utils.RoomAccessible;
import hotel.qa.test.utils.RoomOptions;
import hotel.qa.test.utils.RoomType;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static hotel.qa.test.pages.admin.common.AdminHeader.getHeader;
import static hotel.qa.test.pages.admin.login.AdminLogin.getLogin;
import static hotel.qa.test.pages.admin.main.AdminMain.getMain;
import static hotel.qa.test.pages.users.main.UserMain.getUserMain;

@Slf4j
public class CreatRoomTests extends TestBase {


    @Test
    public void test_create_room_by_admin() throws Exception {

        basePage.navigate(appProperties.getAdminUrl());

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);
        userMain = getUserMain(basePage);

        roomNumber = TestHelpers.getRandomNumeric(3);
        roomPrice = TestHelpers.getRandomNumeric(3);

        adminLogin.act()
                .login(appProperties.getAdminUser(), appProperties.getAdminPassword());

        adminLogin.verify()
                .verifyAdminPageTitle(appProperties.getAppTitle());

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
                .clickCreateRoomButton();


        basePage.navigate(appProperties.getMainUrl());

        userMain.act()
                .waitForHotelLogoLoad()
                .getListOfVisibleRooms();


        List<String> expectedRoomOptions = new ArrayList<>();
        expectedRoomOptions.add(RoomOptions.RADIO.getRoomOption());
        expectedRoomOptions.add(RoomOptions.TV.getRoomOption());
        expectedRoomOptions.add(RoomOptions.REFRESHMENTS.getRoomOption());
        userMain.verify()
                .verifyRoomIsExistWithRoomNumber(roomNumber)
                .verifyRoomIsExistWithType(RoomType.FAMILY.getRoomType())
                .verifyRoomIsExistWithOptions(expectedRoomOptions);


    }

    @Test
    public void test_create_room_without_number() throws Exception {
        basePage.navigate(appProperties.getAdminUrl());

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);

        roomPrice = TestHelpers.getRandomNumeric(3);

        adminLogin.act()
                .login(appProperties.getAdminUser(), appProperties.getAdminPassword());

        adminLogin.verify()
                .verifyAdminPageTitle(appProperties.getAppTitle());

        adminHeader.verify()
                .verifyHeaderElementsIsVisible();

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
        basePage.navigate(appProperties.getAdminUrl());

        adminLogin = getLogin(basePage);
        adminHeader = getHeader(basePage);
        adminMain = getMain(basePage);

        roomNumber = TestHelpers.getRandomNumeric(3);

        adminLogin.act()
                .login(appProperties.getAdminUser(), appProperties.getAdminPassword());

        adminLogin.verify()
                .verifyAdminPageTitle(appProperties.getAppTitle());

        adminHeader.verify()
                .verifyHeaderElementsIsVisible();

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
