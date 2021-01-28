package by.tc.task04.server.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import by.tc.task04.entity.Text;

public abstract class FileOperation {
	public abstract Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException;
}
