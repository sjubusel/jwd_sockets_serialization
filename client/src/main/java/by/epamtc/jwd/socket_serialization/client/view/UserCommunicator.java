package by.epamtc.jwd.socket_serialization.client.view;

public class UserCommunicator {
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

    public boolean isFileNameCorrect(String userInput) {
        return userInput.equals("Y");
    }

    public void informUserFileNameIsIncorrect() {
        System.out.println("Вы не потдвердили имя введённого файла.");
    }

    public void askUserAboutOperation() {
        System.out.println("Введите операцию с текстом.");
    }

    public void informUserAboutChosenOperation(String userInput) {
        System.out.printf("Вы выбрали операцию \"%s\"%n", userInput);
    }
}
