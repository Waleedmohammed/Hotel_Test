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

public class DeleteRoomTests extends TestBase {

    @Test
    @Description("Test That Admin User Can delete new room successfully")
    @Feature("Delete Room Feature")
    @Story("Delete Existing Room")
    public void test_delete_room_by_admin() throws Exception {

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

        adminMain.verify()
                .verifyCreatedRoomIsVisible(roomNumber,
                        RoomType.FAMILY.getRoomType(),
                        RoomAccessible.FALSE.getRoomAccessible(),
                        roomPrice);

        adminMain.act()
                .clickDeleteRoomIcon(roomNumber);

        adminMain.verify()
                .verifyDeletedRoomNumberIsNotVisible(roomNumber);

    }
}
