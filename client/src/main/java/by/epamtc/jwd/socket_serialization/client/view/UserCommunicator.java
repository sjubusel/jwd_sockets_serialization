package by.epamtc.jwd.socket_serialization.client.view;

import by.epamtc.jwd.socket_serialization.model.request.RequestOperation;
import by.epamtc.jwd.socket_serialization.model.request.RequestOperationEntry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCommunicator {
    private Pattern ordinalOperationNumber = Pattern.compile("\\d");

    public void printDialogHeader() {
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓ Программа по разбору текста по программированию из файла ▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void printDialogFooter() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓            ▓▓▓▓▓▓▓▓▓▓      ▓▓▓      ▓▓▓▓         ▓▓      ▓");
        System.out.println("▓           ▓▓              ▓▓▓      ▓▓  ▓▓       ▓▓       ▓");
        System.out.println("▓          ▓▓              ▓▓▓      ▓▓    ▓▓     ▓▓        ▓");
        System.out.println("▓         ▓▓▓▓▓▓▓▓▓       ▓▓▓      ▓▓      ▓▓   ▓▓         ▓");
        System.out.println("▓        ▓▓              ▓▓▓      ▓▓        ▓▓ ▓▓          ▓");
        System.out.println("▓       ▓▓              ▓▓▓      ▓▓          ▓▓▓           ▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void askUserAboutFileName() {
        System.out.println("Введите имя файла для загрузки: ");
    }

    public void informUserAboutChosenFileName(String userInput) {
        System.out.printf("Вы выбрали файл \"%s\"%n", userInput);

    }

    public void askUserIsFileNameCorrect() {
        System.out.println("Вы ввели корректное имя файла?");
        System.out.println("Если да, то введите символ \"Y\"");
    }

    public boolean isUserInputCorrect(String userInput) {
        return userInput.equals("Y");
    }

    public void informUserFileNameIsIncorrect() {
        System.out.println("Вы не потдвердили имя введённого файла.");
    }

    public void askUserAboutOperation() {
        System.out.println("Введите операцию с текстом.");
        for (RequestOperation value : RequestOperation.values()) {
            System.out.printf("Введите \"%s\" для выбора операции:\n" +
                    "\"%s\"\n", value.getInputFormat(), value.getDescription());
        }
    }

    public RequestOperationEntry receiveChosenOperation(String userInput) {
        userInput = userInput.replaceAll("\\s+", " ");
        int ordinalNumberOfChosenOperation = -1;
        RequestOperation chosenOperation = null;
        String chosenOperationValue = "";
        int paramStartIndex = -1;

        Matcher matcher = ordinalOperationNumber.matcher(userInput);
        if (matcher.find()) {
            String operationNumber = matcher.group();
            paramStartIndex = matcher.end() + 1;
            ordinalNumberOfChosenOperation = Integer.parseInt(matcher.group());
        }
        if ((paramStartIndex > 0) && (paramStartIndex < userInput.length())) {
            chosenOperationValue = userInput.substring(paramStartIndex);
        }
        for (RequestOperation operation : RequestOperation.values()) {
            if (operation.getOrdinalNumber() == ordinalNumberOfChosenOperation) {
                chosenOperation = operation;
            }
        }
        return new RequestOperationEntry(chosenOperation, chosenOperationValue);
    }

    public void informUserAboutChosenOperation(RequestOperationEntry operation) {
        System.out.printf("Вы выбрали операцию:\n%s\n",
                operation.getOperationKey().getDescription());
    }


    public void askUserIsOperationCorrect() {
        System.out.println("Вы ввели корректную операцию?");
        System.out.println("Если да, то введите символ \"Y\"");
    }

    public void informUserOperationIsNotApproved() {
        System.out.println("Вы не потдвердили операцию с текстом.");
    }

    public void askUserIsAnotherOperationNeeded() {
        System.out.println("Желаете ли Вы выбрать ещё одну операцию с текстом");
        System.out.println("Если да, то введите символ \"Y\"");
    }

    public void informRequestIsComplete() {
        System.out.println("Запрос для сервера готов.");
    }

    public void askUserIfSessionShouldBeTerminated() {
        System.out.println("Желаете ли Вы завершить работу программы?");
        System.out.println("Если да, то введите символ \"Y\"");
    }

    public void informUserOperationIsIncorrect(RequestOperationEntry operation) {
        System.out.printf("Вы ввели некорректные параметры \"%s\" для " +
                        "операции \"%s\"%n", operation.getOperationValue(),
                operation.getOperationKey().getDescription());
    }
}
