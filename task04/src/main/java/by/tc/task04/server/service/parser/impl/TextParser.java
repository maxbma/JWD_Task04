package by.tc.task04.server.service.parser.impl;

import by.tc.task04.config.FileConfiguration;
import by.tc.task04.server.service.parser.FileParser;
import by.tc.task04.entity.Code;
import by.tc.task04.entity.Sentence;
import by.tc.task04.entity.Text;
import by.tc.task04.entity.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser implements FileParser {

    private final FileConfiguration configuration = new FileConfiguration();

    public Text fillTextComponents(String textOfFile){
        Text text = new Text();
        List<Sentence> sentences = fillSentencesOfText(textOfFile);
        List<Code> codes = searchCodeInText(textOfFile);
        text.setSentences(sentences);
        text.setCodes(codes);
        return text;
    }

    private List<Sentence> fillSentencesOfText(String text){
        List<Word> words;
        List<Sentence> sentence = new ArrayList<>();
        Pattern sentencePattern = Pattern.compile(configuration.getPropertyValue("SENTENCE_REGEX"));
        Matcher sentenceMatcher = sentencePattern.matcher(text);
        while (sentenceMatcher.find()){
            words = searchWordsInSentence(sentenceMatcher.group());
            sentence.add(new Sentence(words));
        }
        return sentence;
    }
    
    private List<Word> searchWordsInSentence(String text){
        List<Word> words = new ArrayList<>();
        Pattern nameWordPattern = Pattern.compile(configuration.getPropertyValue("WORDS_IN_SENTENCE_REGEX"));
        Matcher nameWordMatcher = nameWordPattern.matcher(text);
        while (nameWordMatcher.find()){
            words.add(new Word(nameWordMatcher.group()));
        }
        return words;
    }

    private List<Code> searchCodeInText(String text){
        List<Code> codes = new ArrayList<>();
        Pattern codePattern = Pattern.compile(configuration.getPropertyValue("CODE_IN_TEXT_REGEX"));
        Matcher codeMatcher = codePattern.matcher(text);
        StringBuilder s = new StringBuilder();
        while (codeMatcher.find()){
            if (codeMatcher.group().contains("}")){
                s.append('\n');
            }
            s.append(codeMatcher.group());
        }

        String[] strings = s.toString().split(configuration.getPropertyValue("CODE_SEPARATION"));
        for (String codeBlock : strings){
            Code code = new Code();
            code.setBlocksOfCode(codeBlock);
            codes.add(code);
        }
        return codes;
    }
}