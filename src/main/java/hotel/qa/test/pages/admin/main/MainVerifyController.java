package hotel.qa.test.pages.admin.main;

import hotel.qa.test.core.factory.BasePage;
import hotel.qa.test.utils.SelectedRoomOptions;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@Slf4j
public class MainVerifyController extends AdminMain {
    MainVerifyController(BasePage page) {
        super(page);
    }


    public MainVerifyController verifyCreatedRoomIsVisible(String expectedRoomNumber,String expectedRoomType,String expectedRoomAccessible, String expectedRoomPrice) {

        String roomId = getRoomSystemId(expectedRoomNumber);

        String actualRoomOptions = page.getElementLocatedBy(getRoomDetailsLbl(roomId)).textContent();
        List<String> optionsItems = Arrays.stream(actualRoomOptions.split(","))
                .map(String::trim)
                .toList();

        log.info("Room ID in system is "+roomId);

        log.info("Expected Options "+SelectedRoomOptions.getSelectedOptions());
        log.info("Actual Options "+optionsItems);


        Assert.assertEquals(page.getElementLocatedBy(getRoomNumberLbl(expectedRoomNumber)).textContent(), expectedRoomNumber, "Room Number is not matching expected number");
        Assert.assertEquals(page.getElementLocatedBy(getRoomTypeLbl(roomId)).first().textContent(), expectedRoomType, "Room Type is not matching expected Type");
        Assert.assertEquals(page.getElementLocatedBy(getRoomAccessibleLbl(roomId)).nth(1).textContent(), expectedRoomAccessible, "Room Accessible is not matching expected Accessible");
        Assert.assertEquals(page.getElementLocatedBy(getRoomPriceLbl(expectedRoomPrice)).textContent(), expectedRoomPrice, "Room Price is not matching expected Price");
        Assert.assertTrue(optionsItems.containsAll(SelectedRoomOptions.getSelectedOptions()),"Room Options is not matching expected Options");
        SelectedRoomOptions.clearSelectedOptionsList();
        return this;
    }

    public MainVerifyController verifyErrorMessageDisplayed(String expectedErrorMessage) throws Exception {
        await().atMost(10, TimeUnit.SECONDS).until(() -> page.getElementLocatedBy(errorMessage).isVisible());
        Assert.assertEquals(page.getText(errorMessage), expectedErrorMessage, "Expected Error Message doesn't match Actual Message");
        return this;
    }

    public MainVerifyController verifyDeletedRoomNumberIsNotVisible(String expectedRoomNumber) throws Exception {
        await().pollDelay(1, TimeUnit.SECONDS).until(() -> true);
        Assert.assertFalse(page.isElementVisible(getRoomNumberLbl(expectedRoomNumber)));
        return this;
    }

}
