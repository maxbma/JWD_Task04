package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task15 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		for (Sentence sentence : text.getSentences()){
            for(Word word : sentence.getWords()){
                String firstLetter = word.getName().substring(0,1);
                String lastLetter = word.getName().substring(word.getName().length() - 1);
                if ("?".equals(lastLetter) || "?".equals(firstLetter)){
                    lastLetter = "\\?";
                }
                word.setName(word.getName().replaceAll(firstLetter, "").replaceAll(lastLetter, ""));
            }
        }
        return text;
	}
}
