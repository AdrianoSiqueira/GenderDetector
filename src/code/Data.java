package code;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class Data {
    static final String VERSION = "1.0";

    private static StringProperty gender = new SimpleStringProperty("");

    static StringProperty genderProperty() {
        return gender;
    }

    static void setGender(String gender) {
        Data.gender.set(gender);
    }
}
