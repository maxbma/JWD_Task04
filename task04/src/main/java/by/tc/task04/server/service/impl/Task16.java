package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task16 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		out.writeObject("Enter a substring: ");
        out.flush();
        String subString = (String) in.readObject();
        out.writeObject("Enter a length word: ");
        out.flush();
        int length = Integer.parseInt((String) in.readObject());
        
        for (Sentence sentence : text.getSentences()){
            for (Word word : sentence.getWords()){
                if (word.getName().length() == length){
                    word.setName(subString);
                }
            }
        }
        return text;
	}
}
