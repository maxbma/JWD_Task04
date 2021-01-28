package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task10 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		List<String> listWords = new ArrayList<>();
        String wordToFind;
        do {
            out.writeObject("Enter list: ");
            out.flush();
            wordToFind = (String) in.readObject();
            listWords.add(wordToFind);
            out.writeObject("Continue: y/n");
            out.flush();
        }while ("y".equals(in.readObject()));
        
        Map<String, Integer> wordsCount = new HashMap<>();
        for(String listWord : listWords) {
        	wordsCount.put(listWord, 0);
        }
    	List<Sentence> sentences = text.getSentences();
    	for(Sentence sentence : sentences) {
    		for(Word word : sentence.getWords()) {
    			if(listWords.contains(word.getName())) {
    				Integer count = wordsCount.get(word.getName()) + 1;
    				wordsCount.put(word.getName(), count);
    			}
    		}
    	}
    	
    	List<Integer> values = new ArrayList<>();
    	for(Map.Entry<String, Integer> word : wordsCount.entrySet()) {
    		if(word.getValue() == 0) {
    			wordsCount.remove(word);
    			continue;
    		}
    		values.add(word.getValue());
    	}
    	
    	Collections.sort(values);
    	List<Word> words = new LinkedList<>();
    	for(Integer value : values) {
    		for(Map.Entry<String, Integer> wordCount : wordsCount.entrySet()) {
        		if(wordCount.getValue() == value) {
        			Word word = new Word(wordCount.getKey());
        			words.add(0, word);
        			wordsCount.remove(word);
        			break;
        		}
        	}
    	}
    	
    	List<Sentence> sentenceList = new ArrayList<Sentence>();
    	Sentence sentence = new Sentence();
    	sentence.setWords(words);
    	sentenceList.add(sentence);
    	text.setSentences(sentenceList);
    	
    	return text;
	}
}
