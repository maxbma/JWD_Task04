package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task12 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		out.writeObject("Enter the size of the words you want to delete for: ");
        out.flush();
        int lengthOfWord = Integer.parseInt((String) in.readObject());
        
        String consonantLetters = "b c d f g h j k l m n p q r s t v x y z w";
        for (Sentence sentence : text.getSentences()){
            List<Word> words = sentence.getWords();
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).getName().length() == lengthOfWord
                        && consonantLetters.contains(words.get(i).getName().toLowerCase().substring(0,1))){
                    words.remove(i--);
                }
            }
        }
        return text;
	}
}
