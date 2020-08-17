package by.epamtc.jwd.socket_serialization.model;

public enum RequestOperation {
    WORDS_DELETION_12(1, "Из текста удалить все слова" +
            " заданной длины, начинающиеся на согласную букву."),
    FIRST_LETTER_DELETION_15(2, "Преобразовать каждое " +
            "слово в тексте, удалив из него все последующие вхождения первой " +
            "буквы этого слова"),
    WORD_REPLACEMENT_16(3, "В некотором предложении " +
            "текста слова заданной длины заменить указанной подстрокой, длина" +
            " которой может не совпадать с длиной слова.");

    int ordinalNumber;
    String description;


    RequestOperation(int ordinalNumber, String description) {
        this.ordinalNumber = ordinalNumber;
        this.description = description;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public String getDescription() {
        return description;
    }
}
