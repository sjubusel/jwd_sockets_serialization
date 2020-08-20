package by.epamtc.jwd.socket_serialization.model.request;

import static by.epamtc.jwd.socket_serialization.model.RegExPattern.*;
import static by.epamtc.jwd.socket_serialization.model.RegExPattern.DIGITS;
import static by.epamtc.jwd.socket_serialization.model.RegExPattern.EMPTY_STRING;

public enum RequestOperation implements java.io.Serializable {
    VIEW_OPERATION(0, "Просмотр текста из файла (выполняется в самом конце)",
            EMPTY_STRING, EMPTY_STRING),
    WORDS_DELETION_12(1, "Из текста удалить все слова" +
            " заданной длины, начинающиеся на согласную букву.",
            "*пробел**длина слова для удаления*", DIGITS),

    FIRST_LETTER_DELETION_15(2, "Преобразовать каждое " +
            "слово в тексте, удалив из него все последующие вхождения первой " +
            "буквы этого слова", EMPTY_STRING, EMPTY_STRING),

    WORD_REPLACEMENT_16(3, "В некотором предложении " +
            "текста слова заданной длины заменить указанной подстрокой, длина" +
            " которой может не совпадать с длиной слова.",
            "*пробел**длина слова для замены**пробел**заменяющая подстрока*",
            WORD_REPLACEMENT_16_REGEXP);

    int ordinalNumber;
    String description;
    String inputFormat;
    String paramsValidationRegExp;

    RequestOperation(int ordinalNumber, String description, String format,
            String paramsValidationRegExp) {
        this.ordinalNumber = ordinalNumber;
        this.description = description;
        this.inputFormat = ordinalNumber + format;
        this.paramsValidationRegExp = paramsValidationRegExp;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public String getParamsValidationRegExp() {
        return paramsValidationRegExp;
    }

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public void setParamsValidationRegExp(String paramsValidationRegExp) {
        this.paramsValidationRegExp = paramsValidationRegExp;
    }

    @Override
    public String toString() {
        return "RequestOperation{" +
                "ordinalNumber=" + ordinalNumber +
                ", description='" + description + '\'' +
                ", inputFormat='" + inputFormat + '\'' +
                '}';
    }
}
