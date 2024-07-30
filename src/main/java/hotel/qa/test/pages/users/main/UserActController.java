package hotel.qa.test.pages.users.main;

import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@Slf4j
public class UserActController extends UserMain {

    UserActController(BasePage page) {
        super(page);
    }

    public UserActController waitForHotelLogoLoad() {
        await().atMost(10, TimeUnit.SECONDS).with().pollInterval(1, TimeUnit.SECONDS).
                until(() -> page.getElementLocatedBy(logoImg).isVisible());
        log.info("Hotel Logo Image is displayed now...");
        return this;
    }

    public UserActController getListOfVisibleRooms() {

        int roomsCount = page.getElementsCountBy(roomsList);
        log.info("Current visible rooms count is {} ", roomsCount);
        return this;
    }

}
