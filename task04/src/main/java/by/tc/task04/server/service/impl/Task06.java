package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task06 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		List<Word> words = new ArrayList<>();
	       for (Sentence sentence : text.getSentences()){
	           for (Word word : sentence.getWords()){
	               word.setName(word.getName().toLowerCase());
	               words.add(word);
	           }
	       }
	       Collections.sort(words);
	       for (int i = 1; i < words.size(); i++) {
	            if (words.get(i-1).getName().charAt(0) == words.get(i).getName().charAt(0)){
	                words.get(i-1).setName("\t " + words.get(i-1).getName());
	            }else {
	                words.get(i-1).setName("\t " + words.get(i-1).getName() + "\n");
	            }
	       }
	       text.getSentences().clear();
	       List<Sentence> sentence = new ArrayList<>();
	       sentence.add(new Sentence());
	       sentence.get(0).setWords(words);
	       text.setSentences(sentence);
	       return text;
	}
}
