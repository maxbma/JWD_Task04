package by.tc.task04.server.service.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;
import by.tc.task04.server.service.FileOperation;

public class Task14 extends FileOperation{
	public Text executeFileOperation(Text text, ObjectInputStream in, ObjectOutputStream out)
			throws IOException, ClassNotFoundException{
		int lengthPalindromeWord = 0;
        Map<String, Integer> palindromeInfo = new HashMap<>();
        List<Word> words = new ArrayList<>();
        for (Sentence sentence : text.getSentences()){
            words = sentence.getWords();
            for (Word word : words) {
                String nameOfWord = word.getName();
                if(isPalindrome(nameOfWord)) {
                	palindromeInfo.put(nameOfWord, nameOfWord.length());
                }
            }
        }

        String wordPalindrome = null;
        lengthPalindromeWord = 0;
        for (Map.Entry<String, Integer> palindrome : palindromeInfo.entrySet()){
            if (lengthPalindromeWord < palindrome.getValue() || lengthPalindromeWord == 0){
                wordPalindrome = palindrome.getKey();
                lengthPalindromeWord = palindrome.getValue();
            }
        }

        words.clear();
        Word word = new Word();
        word.setName(wordPalindrome);
        words.add(word);

        text.getSentences().clear();
        text.setSentences(new ArrayList<Sentence>());
        text.getSentences().add(new Sentence());
        text.getSentences().get(0).setWords(words);
        return text;
	}
	
	public static boolean isPalindrome(String word) {
		 int len = word.length();
		 for(int i=0; i<len/2; i++) {
		     if(word.charAt(i)!=word.charAt(len-i-1)) {
		         return false;
		     } 
		 }
		 return true; 
	}
}
