package hotel.qa.test.utils;


public enum RoomAccessible {

    TRUE("true"),

    FALSE("false");

    private final String roomAccessible;

    RoomAccessible(String roomAccessible) {
        this.roomAccessible = roomAccessible;
    }

    public String getRoomAccessible() {
        return roomAccessible;
    }
}
