package by.tc.task04.server;

import by.tc.task04.config.FileConfiguration;
import by.tc.task04.server.service.parser.FileParser;
import by.tc.task04.server.service.reader.FileDataReader;
import by.tc.task04.entity.Text;
import by.tc.task04.factory.factories.FileOperationFactory;
import by.tc.task04.server.service.FileOperation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;


public class Server {

    private static ServerSocket server;
    private static Socket client;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private static final String TEXT_OF_FILE;
    public static final Logger LOGGER = Logger.getLogger(Server.class.getName());
    public static final FileParser fileParser;

    static {
        FileConfiguration configuration = FileConfiguration.configurationFile("text.txt");
        FileDataReader fileDataReader = configuration.getFileDataReader();
        fileParser = configuration.getFileParser();
        TEXT_OF_FILE = fileDataReader.read();
    }

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(Server.class.getResourceAsStream("/logging.properties"));
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Could not setup logger configuration: ", exception);
        }

        try {
            server = new ServerSocket(4004);
            try {
                Server.connectionWithClient();
                Text text = fileParser.fillTextComponents(TEXT_OF_FILE);
                int taskNumber = in.readInt();
                FileOperationFactory factory = new FileOperationFactory();
                FileOperation fileOperation = factory.createFileOperation(taskNumber);
                text = fileOperation.executeFileOperation(text, in, out);
                out.writeObject(text);
                out.flush();
            }catch (IOException exception){
                LOGGER.log(Level.SEVERE, "I / O error", exception);
            }catch (ClassNotFoundException exception){
                LOGGER.log(Level.SEVERE, " Class of a serialized object cannot be found.", exception);
            }finally {
                try {
                    in.close();
                    out.close();
                } catch (IOException exception) {
                    LOGGER.log(Level.SEVERE, "Error closing streams input / output", exception);
                }
            }

        }catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Cannot listen on port", exception);
        }finally {
            System.out.println("Server close");
            try {
                if (client != null){
                    client.close();
                }
                if (server !=null){
                    server.close();
                }
            } catch (IOException exception) {
                LOGGER.log(Level.SEVERE, "Error closing server / client", exception);
            }
        }

    }

    private static void connectionWithClient() {
        try{
            client = server.accept();
            OpenInputOutputStreams(client);
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "The server socket is closed or you have run out of resources", exception);
        }
    }

    private static void OpenInputOutputStreams(Socket client){
        try {
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "I / O error when opening an stream", exception);
        }
    }
}

