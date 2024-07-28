package hotel.qa.test.utils;


public enum RoomType {

    SINGLE("Single"),

    TWIN("Twin"),

    DOUBLE("Double"),

    FAMILY("Family"),

    SUITE("Suite");

    private final String roomType;

    RoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }
}
