package hotel.qa.test.pages.users.main;

import hotel.qa.test.core.factory.BasePage;
import hotel.qa.test.helper.TestHelpers;
import hotel.qa.test.utils.SelectedRoomOptions;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.List;

@Slf4j
public class UserVerifyController extends UserMain {
    UserVerifyController(BasePage page) {
        super(page);
    }

    public UserVerifyController verifyRoomIsExistWithRoomNumber(String expectedRoomNumber) {

        List<String> listRooms = page.getElementsAttributeValue(roomsList, "alt");

        if (!listRooms.isEmpty()) {
            List<String> listRoomNumber = TestHelpers.getFilteredList(listRooms, "room");
            for (String roomNumber : listRoomNumber) {
                log.info("There is Room available with Room Number {} ", roomNumber);
            }
            Assert.assertTrue(listRoomNumber.contains(expectedRoomNumber), "Room is not available in visible rooms list.");
        }
        return this;
    }

    public UserVerifyController verifyRoomIsExistWithType(String expectedRoomType) throws Exception {
        int roomsCount = page.getElementsCountBy(roomsList);
        if (roomsCount > 0) {
            String actualRoomType = page.getText(getVisibleRoomType(roomsCount));
            Assert.assertEquals(actualRoomType, expectedRoomType, "Room Type is not matching expected Type");

        }
        return this;
    }

    public UserVerifyController verifyRoomIsExistWithSelectedOptions() {
        int roomsCount = page.getElementsCountBy(roomsList);
        log.info("Expected Room Options {}",SelectedRoomOptions.getInstance().getItems());
        if (roomsCount > 0) {
            List<String> actualRoomOptions = page.getAllAvailableOptionsFromUl(getVisibleRoomOptions(roomsCount));
            log.info("Actual Room Options {}",actualRoomOptions);
            Assert.assertTrue(actualRoomOptions.containsAll(SelectedRoomOptions.getInstance().getItems()),"Room Options actual results don't match the expected Options");
            SelectedRoomOptions.getInstance().clear();
        }
        return this;
    }

    public UserVerifyController verifyRoomIsExistWithoutOptions() throws Exception {
        int roomsCount = page.getElementsCountBy(roomsList);
        if (roomsCount > 0) {
            Assert.assertFalse(page.isElementVisible(getVisibleRoomOptions(roomsCount)));
        }
        return this;
    }
}
