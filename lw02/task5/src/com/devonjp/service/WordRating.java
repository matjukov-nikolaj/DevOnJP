package com.devonjp.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordRating {

    private TreeMap<String, Integer> words = new TreeMap<>();
    private ArrayList<TreeSet<String>> occurrences = new ArrayList<>();

    private Integer wordsCount;
    private String filePath;

    public WordRating(String filePath, Integer wordsCount) {
        this.wordsCount = wordsCount;
        this.filePath = filePath;
    }

    public void calculateTheNumberOfWords() throws IOException {
        File file = new File(this.filePath);
        this.handleFileReading(file);
    }

    private void handleFileReading(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
        Reader buffer = new BufferedReader(reader);
        handleCharactersReading(buffer);
    }

    private void handleCharactersReading(Reader reader)
            throws IOException {
        int symbol;
        String word = "";
        while ((symbol = reader.read()) != -1) {
            char ch = (char) symbol;
            String s = Character.toString(ch);
            if (!s.equals(" ")) {
                word += ch;
            } else {
                this.addWordToMap(word);
                word = "";
            }
        }
    }

    private void addWordToMap(String word) {
        word = word.replaceAll("[^A-Za-zА-Яа-я0-9-]", "");
        if (word.isEmpty()) {
            return;
        }
        Integer prevOccurrences = 0;
        Integer currOccurrences = 1;
        if (words.containsKey(word)) {
            prevOccurrences = words.get(word);
            currOccurrences = prevOccurrences + 1;
        }
        words.put(word, currOccurrences);
        this.addWordToList(prevOccurrences, currOccurrences, word);
    }

    private void addWordToList(Integer prevOccurrences, Integer currOccurrences, String word) {
        if (prevOccurrences != 0) {
            occurrences.get(prevOccurrences - 1).remove(word);
        }
        if (occurrences.size() < currOccurrences) {
            occurrences.add(new TreeSet<String>());
        }
        occurrences.get(currOccurrences - 1).add(word);
    }

    public void printWords() {
        ListIterator<TreeSet<String>> listIterator = occurrences.listIterator(occurrences.size());
        Integer counter = 0;
        while (!counter.equals(this.wordsCount)) {
            TreeSet<String> wordsSet = listIterator.previous();
            if (wordsSet.isEmpty()) {
                continue;
            }
            for (String word: wordsSet) {
                System.out.println(word + " " + this.words.get(word));
                counter++;
                if (counter.equals(this.wordsCount)) {
                    break;
                }
            }
            if (counter >= this.words.size()) {
                break;
            }
        }
    }
}
