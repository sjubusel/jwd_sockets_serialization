package by.epamtc.jwd.socket_serialization.client.controller;

import by.epamtc.jwd.socket_serialization.client.service.ClientSocketService;
import by.epamtc.jwd.socket_serialization.client.service.RequestBuilderService;
import by.epamtc.jwd.socket_serialization.client.service.ServiceFactory;
import by.epamtc.jwd.socket_serialization.client.service.exception.ServiceException;
import by.epamtc.jwd.socket_serialization.client.view.ResponsePrinter;
import by.epamtc.jwd.socket_serialization.client.view.UserCommunicator;
import by.epamtc.jwd.socket_serialization.model.request.Request;
import by.epamtc.jwd.socket_serialization.model.request.RequestOperationEntry;
import by.epamtc.jwd.socket_serialization.model.response.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ClientController.class);
    private static UserCommunicator userCommunicator = new UserCommunicator();
    private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static RequestBuilderService requestBuilderService
            = serviceFactory.getRequestBuilderService();
    private static ClientSocketService clientSocketService
            = serviceFactory.getClientSocketService();
    private static ResponsePrinter responsePrinter = new ResponsePrinter();

    public static void main(String[] args) {
        String userInput;

        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(isr)) {
            clientSocketService.startConnection("localhost", 443);
            userCommunicator.printDialogHeader();

            while (true) {
                userInput = receiveFileNameFromUser(reader);
                if (userInput == null) {
                    continue;
                }
                requestBuilderService.addFileName(userInput);

                while (true) {
                    RequestOperationEntry operationEntry =
                            receiveOperationFromUser(reader);
                    if (operationEntry == null) {
                        continue;
                    }
                    requestBuilderService.addOperation(operationEntry);

                    if (isRequestComplete(reader)) {
                        break;
                    }
                }

                userCommunicator.informRequestIsComplete();
                Request request = requestBuilderService.buildRequest();
                clientSocketService.sendObject(request);
                System.out.println("Запрос направлен на сервер...");

                Text responseText = (Text) clientSocketService.receiveObject();
                System.out.println("Результат выполнения запроса.");
                responsePrinter.print(responseText);

                performAdditionalOperationsIfNecessary(responseText, reader);

                userCommunicator.askUserIfSessionShouldBeTerminated();
                userInput = reader.readLine();
                if (userCommunicator.isUserInputCorrect(userInput)) {
                    request = requestBuilderService.buildRequest();
                    clientSocketService.sendObject(request);
                    break;
                }
            }

            clientSocketService.stopConnection();
            userCommunicator.printDialogFooter();
        } catch (IOException e) {
            LOGGER.error("IO ERROR WHILE COMMUNICATION WITH A USER", e);
            System.out.println("Приложение временно не работает, " +
                    "обратитесь к администратору");
        } catch (ServiceException e) {
            LOGGER.error("REQUEST BUILDING ERROR", e);
            System.out.println("Приложение временно не работает, " +
                    "обратитесь к администратору");
        }

    }

    private static String receiveFileNameFromUser(BufferedReader reader) throws IOException {
        String userInput;
        userCommunicator.askUserAboutFileName();
        userInput = reader.readLine();
        userCommunicator.informUserAboutChosenFileName(userInput);
        userCommunicator.askUserIsFileNameCorrect();
        userInput = reader.readLine();
        if (!userCommunicator.isUserInputCorrect(userInput)) {
            userCommunicator.informUserFileNameIsIncorrect();
            return null;
        }
        return userInput;
    }

    private static RequestOperationEntry receiveOperationFromUser(BufferedReader reader) throws IOException {
        String userInput;
        userCommunicator.askUserAboutOperation();
        userInput = reader.readLine();
        RequestOperationEntry operationEntry = userCommunicator
                .receiveChosenOperation(userInput.trim());
        userCommunicator.informUserAboutChosenOperation(operationEntry);
        userCommunicator.askUserIsOperationCorrect();
        userInput = reader.readLine();
        if (!userCommunicator.isUserInputCorrect(userInput)) {
            userCommunicator.informUserOperationIsIncorrect();
            return null;
        }
        return operationEntry;
    }

    private static boolean isRequestComplete(BufferedReader reader) throws IOException {
        String userInput;
        userCommunicator.askUserIsAnotherOperationNeeded();
        userInput = reader.readLine();
        return !userCommunicator.isUserInputCorrect(userInput);
    }

    private static void performAdditionalOperationsIfNecessary(Text responseText,
            BufferedReader reader) throws IOException, ServiceException {
        String userInput;
        userCommunicator.askUserIsAnotherOperationNeeded();
        userInput = reader.readLine();
        if (userCommunicator.isUserInputCorrect(userInput)) {
            requestBuilderService.addText(responseText);
            while (true) {
                RequestOperationEntry operationEntry =
                        receiveOperationFromUser(reader);
                if (operationEntry == null) {
                    continue;
                }
                requestBuilderService.addOperation(operationEntry);
                if (isRequestComplete(reader)) {
                    break;
                }
            }

            if (requestBuilderService.doOperationsExist()) {
                Request request = requestBuilderService.buildRequest();
                clientSocketService.sendObject(request);
                System.out.println("Запрос направлен на сервер...");

                responseText = (Text) clientSocketService.receiveObject();
                System.out.println("Результат выполнения запроса.");
                responsePrinter.print(responseText);
                performAdditionalOperationsIfNecessary(responseText, reader);
            }
        }

    }
}
