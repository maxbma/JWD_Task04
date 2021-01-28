package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task07 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		List<Word> words = new ArrayList<>();
        String vowels = "a e i o u y";
        Map<String, Double> vowelsProportion = new HashMap<>();
        for (Sentence sentence : text.getSentences()) {
            words.addAll(sentence.getWords());
        }
        for (Word word : words) {
            double countVowelsLetter = 0;
            double countLetterInWord = word.getName().length();
            for (int i = 1; i <= countLetterInWord; i++) {
                String letter = word.getName().toLowerCase().substring(i-1 , i);
                if (vowels.contains(letter)) {
                    countVowelsLetter++;
                }
            }
            double proportionOfVowels = countVowelsLetter / countLetterInWord;
            vowelsProportion.put(word.getName(), proportionOfVowels);
        }

        words.clear();
        double valueOfProportion = 0;
        String nameOfWord = null;
        
        List<Double> values = new ArrayList<>();
        for(Map.Entry<String, Double> word : vowelsProportion.entrySet()) {
        	values.add(word.getValue());
        }
        Collections.sort(values);
        
        for(Double value : values) {
        	for(Map.Entry<String, Double> vowelProportion : vowelsProportion.entrySet()) {
        		if(vowelProportion.getValue() == value) {
        			Word word = new Word(vowelProportion.getKey());
        			words.add(word);
        			vowelsProportion.remove(vowelProportion);
        			break;
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
