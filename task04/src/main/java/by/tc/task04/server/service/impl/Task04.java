package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task04 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		out.writeObject("Enter the size of the words you want to search for: ");
        out.flush();
        int lengthOfWords = Integer.parseInt((String) in.readObject());
        
        List<Word> words = new ArrayList<>();
        for (Sentence sentence : text.getSentences()){
            if (sentence.toString().contains("?")){
                for (Word word : sentence.getWords()){
                    if (word.getName().length() == lengthOfWords){
                        if (!words.contains(word)){
                            words.add(word);
                        }
                    }
                }
            }
        }
        text.getSentences().clear();
        text.setSentences(new ArrayList<Sentence>());
        text.getSentences().add(new Sentence());
        text.getSentences().get(0).setWords(words);
        return text;
	}
}
