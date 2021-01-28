package by.tc.task04.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import by.tc.task04.controller.FileController;

public class Client {
    public static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(Client.class.getResourceAsStream("/logging.properties"));
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Could not setup logger configuration: ", exception);

        }

        FileController fileController = new FileController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the task number: ");

        try {
            int taskNumber = Integer.parseInt(reader.readLine());
            fileController.request(taskNumber);
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "I / O error when setting a task", exception);
        }
    }
}
