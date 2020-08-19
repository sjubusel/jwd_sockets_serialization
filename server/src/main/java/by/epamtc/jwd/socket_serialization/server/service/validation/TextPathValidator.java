package by.epamtc.jwd.socket_serialization.server.service.validation;

import by.epamtc.jwd.socket_serialization.model.response.RegExPattern;

public class TextPathValidator {

    public boolean isTextPathCorrect(String textName) {
        if (textName == null) {
            return false;
        }
        return textName.matches(RegExPattern.FILE_NAMING);
    }
}
