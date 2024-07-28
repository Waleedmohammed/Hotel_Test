package hotel.qa.test.utils;

public enum RoomOptions {

    WIFI("WiFi"),
    REFRESHMENTS("Refreshments"),
    TV("TV"),
    SAFE("Safe"),
    RADIO("Radio"),
    VIEWS("Views");


    private final String roomOption;

    RoomOptions(String roomOption) {
        this.roomOption = roomOption;
    }

    public String getRoomOption() {
        return roomOption;
    }
}
