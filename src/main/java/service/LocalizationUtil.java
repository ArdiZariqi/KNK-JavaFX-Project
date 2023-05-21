package service;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationUtil {
    private static ResourceBundle resourceBundle;

    public static void setLanguage(String language) {
        Locale locale = new Locale(language);
        resourceBundle = ResourceBundle.getBundle("i18n.messages", locale);
    }

    public static String getString(String key) {
        if (resourceBundle == null) {
            throw new IllegalStateException("Language has not been set. Call setLanguage() first.");
        }

        return resourceBundle.getString(key);
    }
}
