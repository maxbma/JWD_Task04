package by.tc.task04.factory.factories;

import by.tc.task04.server.service.parser.FileParser;
import by.tc.task04.server.service.parser.impl.TextParser;
import by.tc.task04.server.service.reader.FileDataReader;
import by.tc.task04.server.service.reader.impl.TextDataReader;
import by.tc.task04.factory.FileAbstractFactory;

public class TextFactory implements FileAbstractFactory {
    @Override
    public FileParser getFileParser() {
        return new TextParser();
    }

    @Override
    public FileDataReader getFileDataReader(String path) {
        return new TextDataReader(path);
    }
}
