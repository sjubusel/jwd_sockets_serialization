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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientController {
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

                while (true) {
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

                    userCommunicator.askUserIsAnotherOperationNeeded();
                    userInput = reader.readLine();
                    if (!userCommunicator.isUserInputCorrect(userInput)) {
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

                userCommunicator.askUserIsAnotherOperationNeeded();
                userInput = reader.readLine();
                if (userCommunicator.isUserInputCorrect(userInput)) {
                    requestBuilderService.addText(responseText);
                    // method to query an operation

                }

                userCommunicator.askUserIfSessionShouldBeTerminated();
                userInput = reader.readLine();
                if (userCommunicator.isUserInputCorrect(userInput)) {
                    break;
                }
            }

            clientSocketService.stopConnection();
            userCommunicator.printDialogFooter();
        } catch (IOException e) {
            //TODO add exception handling
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
