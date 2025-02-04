package example.miscellaneous;

import android.text.InputFilter;
import android.widget.EditText;

public class Util {

    public static void removeSpace(EditText textBox) {
        textBox.setFilters(new InputFilter[]{(source, start, end, dest, dstart, dend) -> {
            if (end == 1) {
                if (Character.isWhitespace(source.charAt(0))) {
                    return "";
                }
            }
            return null;
        }});
    }

    public static void removeSpaceAndSpecialChars(EditText textBox) {
        textBox.setFilters(new InputFilter[]{(input, start, end, dest, dstart, dend) -> {
            if (end == 1) {
                if (Character.isWhitespace(input.charAt(0)) || !Character.isLetter(input.charAt(0)) && !Character.isDigit(input.charAt(0))) {
                    return "";
                }
            }
            return null;
        }});
    }
}
