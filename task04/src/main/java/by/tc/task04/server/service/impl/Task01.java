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

public class Task01 extends FileOperation {
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		List<Sentence> sentences = text.getSentences();
        List<Sentence> result = new ArrayList<>();
    	for(Sentence sentence : sentences) {
    		List<String> words = new ArrayList<>();
    		for(Word word : sentence.getWords()) {
    			words.add(word.getName());
    		}
    		boolean isWordRepeated = false;
    		for(Word word : sentence.getWords()) {
    			words.remove(word.getName());
    			if(words.contains(word.getName())) {
    				isWordRepeated = true;
    				break;
    			}
    		}
    		if(isWordRepeated) {
    			result.add(sentence);
    		}
    	}
    	text.setSentences(result);
        return text;
	}
}
