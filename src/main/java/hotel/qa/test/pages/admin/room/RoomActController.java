package hotel.qa.test.pages.admin.room;

import com.microsoft.playwright.Locator;
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

    public RoomActController setNewRoomNumber(String newRoomNumber) throws Exception {
        page.typeIn(roomNumTxtBox, newRoomNumber);
        return this;
    }

    public RoomActController setNewRoomType(String newRoomType) throws Exception {
        page.selectFromDDL(roomTypeDdl, newRoomType);
        return this;
    }

    public RoomActController setNewRoomAccessible(String newRoomAccessible) throws Exception {
        page.selectFromDDL(roomAccessibleDdl, newRoomAccessible);
        return this;
    }

    public RoomActController setNewRoomPrice(String newRoomPrice) throws Exception {
        page.typeIn(roomPriceTxtBox, newRoomPrice);
        return this;
    }

    public RoomActController updateWifiOption() {
        Locator wifiCheckBox = page.getElementLocatedBy(wifiChBox);
        if (wifiCheckBox.isChecked()) {
            wifiCheckBox.uncheck();
        } else {
            wifiCheckBox.check();
        }
        return this;
    }

    public RoomActController updateRefreshmentOption() {
        Locator refreshmentCheckBox = page.getElementLocatedBy(refreshmentChBox);
        if (refreshmentCheckBox.isChecked()) {
            refreshmentCheckBox.uncheck();
        } else {
            refreshmentCheckBox.check();
        }
        return this;
    }

    public RoomActController updateTvOption() {
        Locator tvCheckBox = page.getElementLocatedBy(tvChBox);
        if (tvCheckBox.isChecked()) {
            tvCheckBox.uncheck();
        } else {
            tvCheckBox.check();
        }
        return this;
    }

    public RoomActController updateSafeOption() {
        Locator safeCheckBox = page.getElementLocatedBy(safeChBox);
        if (safeCheckBox.isChecked()) {
            safeCheckBox.uncheck();
        } else {
            safeCheckBox.check();
        }
        return this;
    }

    public RoomActController updateRadioOption() {
        Locator radioCheckBox = page.getElementLocatedBy(radioChBox);
        if (radioCheckBox.isChecked()) {
            radioCheckBox.uncheck();
        } else {
            radioCheckBox.check();
        }
        return this;
    }

    public RoomActController updateViewsOption() {
        Locator viewsCheckBox = page.getElementLocatedBy(viewsChBox);
        if (viewsCheckBox.isChecked()) {
            viewsCheckBox.uncheck();
        } else {
            viewsCheckBox.check();
        }
        return this;
    }

    public RoomActController setNewRoomDescription(String newRoomDescription) throws Exception {
        page.typeIn(descriptionTxtBox, newRoomDescription);
        return this;
    }

    public RoomActController setNewRoomImageUrl(String newRoomImageUrl) throws Exception {
        page.typeIn(imageTxtBox, newRoomImageUrl);
        return this;
    }

    public RoomActController clickUpdateButton() throws Exception {
        page.clickOn(updateBtn);
        return this;
    }

    public RoomActController clearRoomNumber() throws Exception {
        page.typeIn(roomNumTxtBox," ");
        return this;
    }

    public RoomActController clearRoomPrice() throws Exception {
        page.typeIn(roomPriceTxtBox," ");
        return this;
    }

    public RoomActController clickCancelUpdateButton() throws Exception {
        page.clickOn(cancelBtn);
        return this;
    }

    public RoomActController updateAllRoomDetails(String newRoomNumber,
                                                  String newRoomType,
                                                  String newRoomAccessible,
                                                  String newRoomPrice,
                                                  String newRoomDescription,
                                                  String newRoomImageUrl) throws Exception {

        setNewRoomNumber(newRoomNumber);
        setNewRoomType(newRoomType);
        setNewRoomAccessible(newRoomAccessible);
        setNewRoomPrice(newRoomPrice);
        updateWifiOption();
        updateRefreshmentOption();
        updateTvOption();
        updateSafeOption();
        updateRadioOption();
        updateViewsOption();
        setNewRoomDescription(newRoomDescription);
        setNewRoomImageUrl(newRoomImageUrl);
        return this;
    }


}
