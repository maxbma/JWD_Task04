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

public class Task08 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		String vowels = "a e i o u y";
        Character[] consonantLetters =
                {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'x', 'z' ,'w'};

        List<Word> words = new ArrayList<>();
        for(Sentence sentence : text.getSentences()){
            words.addAll(sentence.getWords());
        }

        for (int i = 0; i < words.size(); i++) {
            int minPositionOfWord = 0;
            int position = 0;
            char character = 0;
            char minCharacter = 0 ;
            for (int j = i; j < words.size(); j++) {
                boolean isFoundCharacter = false;
                String nameOfWord = words.get(j).getName().toLowerCase();
                if (vowels.contains(nameOfWord.substring(0, 1))) {
                    for (int k = 1; k < nameOfWord.length(); k++) {
                        for (Character consonantCharacter : consonantLetters) {
                            if (nameOfWord.charAt(k) == consonantCharacter && minCharacter == 0) {
                                minCharacter = consonantCharacter;
                                minPositionOfWord = j;
                                isFoundCharacter = true;
                                break;
                            }else if (nameOfWord.charAt(k) == consonantCharacter) {
                                character = consonantCharacter;
                                position = j;
                                isFoundCharacter = true;
                                break;
                            }
                        }
                        if (isFoundCharacter){
                            break;
                        }
                    }
                }
                if (minCharacter > character && character!=0) {
                    minPositionOfWord = position;
                }
            }
            if (position!=0 && minPositionOfWord!=0){
            String temp = words.get(i).getName();
            words.get(i).setName(words.get(minPositionOfWord).getName());
            words.get(minPositionOfWord).setName(temp);
            }
        }
        return text;
	}
}
