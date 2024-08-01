package hotel.qa.test.CRUD;

import hotel.qa.test.TestBase;
import hotel.qa.test.helper.TestHelpers;
import hotel.qa.test.utils.RoomAccessible;
import hotel.qa.test.utils.RoomType;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;


import static hotel.qa.test.pages.admin.common.AdminHeader.getHeader;
import static hotel.qa.test.pages.admin.login.AdminLogin.getLogin;
import static hotel.qa.test.pages.admin.main.AdminMain.getMain;
import static hotel.qa.test.pages.users.main.UserMain.getUserMain;

public class ViewRoomTests extends TestBase {

    @Test
    @Description("Test That Admin User Can view his created room successfully on main home page")
    @Feature("View Room Feature")
    @Story("View Room on main system page after got created by admin")
    public void test_admin_can_view_created_room_on_system() throws Exception {

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
                .clickCreateRoomButton();

        basePage.navigate(appProperties.getMainUrl());

        userMain.act()
                .waitForHotelLogoLoad()
                .getListOfVisibleRooms();

        userMain.verify()
                .verifyRoomIsExistWithRoomNumber(roomNumber)
                .verifyRoomIsExistWithType(RoomType.FAMILY.getRoomType())
                .verifyRoomIsExistWithSelectedOptions();

        basePage.navigate(appProperties.getAdminUrl());

        adminMain.act()
                .clickDeleteRoomIcon(roomNumber);
        adminMain.verify()
                .verifyDeletedRoomNumberIsNotVisible(roomNumber);

    }
}
