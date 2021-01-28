package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task11 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		out.writeObject("Enter the first letter of the word: ");
        out.flush();
        char firstLetter = in.readObject().toString().charAt(0);
        out.writeObject("Enter the last letter of the word: ");
        out.flush();
        char lastLetter = in.readObject().toString().charAt(0);
        
        for (Sentence sentence : text.getSentences()){
            int positionWordWithMaxLength = 0;
            int wordWithMaxLength = -1;
            List<Word> words = sentence.getWords();
            for (int i = 0; i < words.size(); i++) {
                int start = words.get(i).toString().indexOf(firstLetter);
                int end = words.get(i).toString().lastIndexOf(lastLetter);
                int length = end - start;

                if (start >= 0 && end > 0 && length>=0){
                    if (wordWithMaxLength < length){
                        wordWithMaxLength = length;
                        positionWordWithMaxLength = i;
                    }
                }
            }
            if (positionWordWithMaxLength!=0){
                words.remove(positionWordWithMaxLength);
            }
        }
        return text;
	}
}
