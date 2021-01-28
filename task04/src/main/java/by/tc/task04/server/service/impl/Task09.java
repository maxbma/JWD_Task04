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

public class Task09 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		List<Word> words = new ArrayList<>();
        for(Sentence sentence : text.getSentences()){
            words.addAll(sentence.getWords());
        }

        for (int i = 0; i < words.size(); i++) {
            for (int j = 1; j < words.size(); j++) {
                if (words.get(j-1).getName().length() > words.get(j).getName().length()){
                    String name = words.get(j).getName();
                    words.get(j).setName(words.get(j-1).getName());
                    words.get(j-1).setName(name);
                }else if (words.get(j-1).getName().length() == words.get(j).getName().length()){
                    if (words.get(j - 1).compareTo(words.get(j)) > 0){
                        String name = words.get(j).getName();
                        words.get(j).setName(words.get(j-1).getName());
                        words.get(j-1).setName(name);
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
