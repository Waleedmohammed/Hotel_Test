package hotel.qa.test.pages.admin.room;

import hotel.qa.test.core.factory.BasePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomActController extends AdminRoom {

    RoomActController(BasePage page) {
        super(page);
    }

    public RoomActController clickEditButton() throws Exception {
        page.clickOn(editBtn);
        return this;
    }



}
