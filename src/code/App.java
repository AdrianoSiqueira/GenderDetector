package code;

import ai.GenderDetector;

class App {

    /**
     * <p> Analyzes the name, invokes the method from the library and updates the gender in data class. </p>
     *
     * @param fullName Complete name to be analyzed.
     */
    void detect(String fullName) {
        if (fullName.length() >= 3) {
            switch (new GenderDetector().detect(fullName)) {
                case 0:
                    Data.setGender("Undefined");
                    break;

                case 1:
                    Data.setGender("Feminine");
                    break;

                case 2:
                    Data.setGender("Masculine");
                    break;

                default:
                    break;
            }
        } else {
            Data.setGender("");
        }
    }
}
