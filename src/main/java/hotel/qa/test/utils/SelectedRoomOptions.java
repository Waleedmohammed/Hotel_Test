package hotel.qa.test.utils;

import java.util.ArrayList;
import java.util.List;

public class SelectedRoomOptions {
    private static SelectedRoomOptions instance;
    private List<String> roomOptions;

    private SelectedRoomOptions() {
        roomOptions = new ArrayList<>();
    }

    public static synchronized SelectedRoomOptions getInstance() {
        if (instance == null) {
            instance = new SelectedRoomOptions();
        }
        return instance;
    }

    public void addItem(String item) {
        roomOptions.add(item);
    }

    public List<String> getItems() {
        return roomOptions;
    }
}
