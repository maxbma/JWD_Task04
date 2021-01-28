package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task05 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		for (Sentence sentence : text.getSentences()){

            Word firstWord = sentence.getWords().get(0);
            Word lastWord =  sentence.getWords().get(sentence.getWords().size() - 1);

            sentence.getWords().remove(0);
            sentence.getWords().add(0, lastWord);

            sentence.getWords().remove(sentence.getWords().size() - 1);
            sentence.getWords().add(sentence.getWords().size(), firstWord);

        }
        return text;
	}
}
