package by.epamtc.jwd.socket_serialization.model.request;

public enum RequestOperation implements java.io.Serializable {
    VIEW_OPERATION(0, "Просмотр текста из файла (выполняется в самом конце)",
            ""),
    WORDS_DELETION_12(1, "Из текста удалить все слова" +
            " заданной длины, начинающиеся на согласную букву.",
            "*пробел**длина слова для удаления*"),

    FIRST_LETTER_DELETION_15(2, "Преобразовать каждое " +
            "слово в тексте, удалив из него все последующие вхождения первой " +
            "буквы этого слова", ""),

    WORD_REPLACEMENT_16(3, "В некотором предложении " +
            "текста слова заданной длины заменить указанной подстрокой, длина" +
            " которой может не совпадать с длиной слова.",
            "*пробел**длина слова для замены**пробел**заменяющая подстрока*");

    int ordinalNumber;

    String description;
    String inputFormat;

    RequestOperation(int ordinalNumber, String description, String format) {
        this.ordinalNumber = ordinalNumber;
        this.description = description;
//        this.inputFormat = "Формат ввода: " + ordinalNumber + format;
        this.inputFormat = ordinalNumber + format;
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

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
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
