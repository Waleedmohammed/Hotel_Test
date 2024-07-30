package hotel.qa.test.utils;

import java.util.ArrayList;
import java.util.List;

public class SelectedRoomOptions {
    private static final ThreadLocal<List<String>> selectedOptions = ThreadLocal.withInitial(ArrayList::new);

    public static List<String> getSelectedOptions() {
        return selectedOptions.get();
    }

    public static void clearSelectedOptionsList() {
        selectedOptions.get().clear();
    }

}
