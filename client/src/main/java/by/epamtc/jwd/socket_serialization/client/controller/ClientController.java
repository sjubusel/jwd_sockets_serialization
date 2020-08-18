package by.epamtc.jwd.socket_serialization.client.controller;

import by.epamtc.jwd.socket_serialization.client.service.RequestBuilderService;
import by.epamtc.jwd.socket_serialization.client.service.ServiceFactory;
import by.epamtc.jwd.socket_serialization.client.view.UserCommunicator;
import by.epamtc.jwd.socket_serialization.model.RequestOperationEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientController {
    private static UserCommunicator userCommunicator = new UserCommunicator();

    public static void main(String[] args) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RequestBuilderService requestBuilderService = serviceFactory.getRequestBuilderService();

        String userInput;

        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(isr)) {
            userCommunicator.printDialogHeader();

            while (true) {
                userCommunicator.askUserAboutFileName();
                userInput = reader.readLine();
                userCommunicator.informUserAboutChosenFileName(userInput);
                userCommunicator.askUserIsFileNameCorrect();
                userInput = reader.readLine();
                if (!userCommunicator.isUserInputCorrect(userInput)) {
                    userCommunicator.informUserFileNameIsIncorrect();
                    continue;
                }
                requestBuilderService.addFileName(userInput);

                userCommunicator.askUserAboutOperation();
                userInput = reader.readLine();
                RequestOperationEntry operationEntry = userCommunicator
                        .receiveChosenOperation(userInput.trim());
                userCommunicator.informUserAboutChosenOperation(operationEntry);
                userCommunicator.askUserIsOperationCorrect();
                userInput = reader.readLine();
                if (!userCommunicator.isUserInputCorrect(userInput)) {
                    userCommunicator.informUserOperationIsIncorrect();
                    continue;
                }
                requestBuilderService.addOperation(operationEntry);

                userCommunicator.printDialogFooter();

                if (true) {
                    break;
                }
            }

        } catch (IOException e) {
            //TODO add exception handling
            e.printStackTrace();
        }

    }
}
