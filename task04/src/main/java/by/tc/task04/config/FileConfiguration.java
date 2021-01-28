package by.tc.task04.config;

import by.tc.task04.server.service.parser.FileParser;
import by.tc.task04.server.service.reader.FileDataReader;
import by.tc.task04.factory.FileAbstractFactory;
import by.tc.task04.factory.factories.TextFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileConfiguration {

    private FileDataReader fileDataReader;
    private FileParser fileParser;
    private  String filePath;
    public static final Logger LOGGER = Logger.getLogger(FileConfiguration.class.getName());

    public FileConfiguration(){}

    public FileConfiguration(FileAbstractFactory factory, String fileName) {
        filePath = getClass().getResource("/" + fileName).getPath();
        fileDataReader = factory.getFileDataReader(filePath);
        fileParser = factory.getFileParser();
    }

    public FileDataReader getFileDataReader() {
        return fileDataReader;
    }

    public void setFileDataReader(FileDataReader fileDataReader) {
        this.fileDataReader = fileDataReader;
    }

    public FileParser getFileParser() {
        return fileParser;
    }

    public void setFileParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPropertyValue(String propertyName){

        try {
            LogManager.getLogManager().readConfiguration(FileConfiguration.class.getResourceAsStream("/logging.properties"));
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Could not setup logger configuration: ", exception);

        }

        String propertyValue = "";
        Properties properties = new Properties();

        try(InputStream inputStream = this.getClass().getResourceAsStream("/regex.properties")) {
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);

        }catch (IOException exception){
            LOGGER.log(Level.SEVERE, "Error occurred when reading properties file from the input stream.", exception);
        }
        return propertyValue;
    }

    public static FileConfiguration configurationFile(String fileName){
        FileAbstractFactory factory;
        FileConfiguration fileApplication = null;

        Pattern typeOfFilePattern = Pattern.compile(new FileConfiguration().getPropertyValue("TYPE_OF_FILE"));
        Matcher typeOfFileMatcher = typeOfFilePattern.matcher(fileName);
        if (typeOfFileMatcher.find()){
            if (".txt".equals(typeOfFileMatcher.group())){
                factory = new TextFactory();
                fileApplication = new FileConfiguration(factory, fileName);
            }
        }
        return fileApplication;
    }
}
