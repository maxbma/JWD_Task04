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

public class Task13 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		out.writeObject("Enter one letter: ");
        out.flush();
        char character = in.readObject().toString().charAt(0);
        
        List<Word> words = new ArrayList<>();
        for(Sentence sentence : text.getSentences()){
            words.addAll(sentence.getWords());
        }
        int positionOfWordWithMaxCharacter = 0;
        for (int i = 0; i < words.size(); i++) {
            int countMatchesCharInPreviousFoundWord = 0;
            for (int j = i; j < words.size(); j++) {
                int numberOfMatchesOfSpecifiedCharacter = 0;
                String name = words.get(j).getName();
                while (!name.isEmpty()){
                    int index;
                    if ((index = name.indexOf(character)) != -1){
                        name = name.substring(index + 1);
                        numberOfMatchesOfSpecifiedCharacter++;
                    }else {
                        name = "";
                    }
                }
                if (countMatchesCharInPreviousFoundWord < numberOfMatchesOfSpecifiedCharacter){
                    positionOfWordWithMaxCharacter = j;
                    countMatchesCharInPreviousFoundWord = numberOfMatchesOfSpecifiedCharacter;
                }else if (countMatchesCharInPreviousFoundWord == numberOfMatchesOfSpecifiedCharacter){
                    if (words.get(j).compareTo(words.get(positionOfWordWithMaxCharacter)) < 0){
                        String nameWord = words.get(j).getName();
                        words.get(j).setName(words.get(positionOfWordWithMaxCharacter).getName());
                        words.get(positionOfWordWithMaxCharacter).setName(nameWord);
                    }
                }
            }
            String nameWord = words.get(i).getName();
            words.get(i).setName(words.get(positionOfWordWithMaxCharacter).getName());
            words.get(positionOfWordWithMaxCharacter).setName(nameWord);
        }

        text.getSentences().clear();
        text.setSentences(new ArrayList<Sentence>());
        text.getSentences().add(new Sentence());
        text.getSentences().get(0).setWords(words);
        return text;
	}
}
