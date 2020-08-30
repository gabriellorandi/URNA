package Utils;

import java.util.regex.Pattern;

public class ValidateFields {
    private static final String REGEX_TEXT;
    private static final String REGEX_NUMBER;

    static {
        REGEX_TEXT = "[a-zA-Z\\u00C0-\\u00FF ]*";
        REGEX_NUMBER = "[0-9]*";
    }

    public static boolean validateTextField (String content) {
        if (Pattern.matches(REGEX_TEXT, content) && !content.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean validateNumberField (String content) {
        if (Pattern.matches(REGEX_NUMBER, content) && !content.isEmpty()) {
            return true;
        }
        return false;
    }
}
