package by.tc.task04.factory;

import by.tc.task04.server.service.parser.FileParser;
import by.tc.task04.server.service.reader.FileDataReader;

public interface FileAbstractFactory {
    FileParser getFileParser();
    FileDataReader getFileDataReader(String path);
}
