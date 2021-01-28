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

public class Task03 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		Sentence firstSentence = text.getSentences().get(0);
        List<Word> words = new ArrayList<>();
        List<Word> foundWords = new ArrayList<>();
        for (Word wordInFirstSentence : firstSentence.getWords()){
            boolean foundWord = false;
            for(Sentence sentence : text.getSentences()){
                if (!sentence.equals(firstSentence)) {
                    words.addAll(sentence.getWords());
                    for (Word wordOtherSentences : words) {
                        if (wordOtherSentences.getName().equals(wordInFirstSentence.getName())) {
                            foundWord = true;
                            break;
                        }
                    }
                }
            }
            if (!foundWord){
                foundWords.add(wordInFirstSentence);
            }
        }
        text.getSentences().clear();
        text.setSentences(new ArrayList<Sentence>());
        text.getSentences().add(new Sentence());
        text.getSentences().get(0).setWords(foundWords);
        return text;
	}
}
