package by.epamtc.jwd.socket_serialization.client.controller;

import by.epamtc.jwd.socket_serialization.client.service.RequestBuilderService;
import by.epamtc.jwd.socket_serialization.client.service.ServiceFactory;
import by.epamtc.jwd.socket_serialization.client.view.UserCommunicator;
import by.epamtc.jwd.socket_serialization.model.Request;
import by.epamtc.jwd.socket_serialization.model.RequestOperationEntry;

import java.io.*;
import java.net.Socket;

public class ClientController {
    private static UserCommunicator userCommunicator = new UserCommunicator();
    private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static RequestBuilderService requestBuilderService
            = serviceFactory.getRequestBuilderService();

    public static void main(String[] args) {
        String userInput;
        Socket clientSocket;
        ObjectOutputStream oStream;
        ObjectInputStream iStream;

        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(isr)) {
            clientSocket = new Socket("localhost", 443);
            oStream = new ObjectOutputStream(clientSocket.getOutputStream());
            iStream = new ObjectInputStream(clientSocket.getInputStream());

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
                oStream.writeObject(request);
                System.out.println("Запрос направлен на сервер...");

                Text responseText = ((Text) iStream.readObject());


                if (true) {
                    break;
                }

            }

            userCommunicator.printDialogFooter();
        } catch (IOException e) {
            //TODO add exception handling
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //TODO add exception handling
            e.printStackTrace();
        }

    }
}
