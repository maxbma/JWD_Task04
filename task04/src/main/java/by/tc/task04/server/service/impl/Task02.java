package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task02 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		List<Sentence> sentences = text.getSentences();
        Collections.sort(sentences);
        StringBuilder s = new StringBuilder();
        for (Sentence sentence : sentences) {
            for (Word word : sentence.getWords()) {
                s.append(word.getName()).append(" ");
            }
        }
        return text;
    }
}
