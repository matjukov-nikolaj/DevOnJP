package com.devonjp.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordRating {

    private Map<String, Integer> words = new LinkedHashMap<>();
    private Integer wordsCount;
    private String filePath;

    public WordRating(String filePath, Integer wordsCount) {
        this.wordsCount = wordsCount;
        this.filePath = filePath;
    }

    public void calculateTheNumberOfWords() {
        try {
            File file = new File(this.filePath);
            this.handleFileReading(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleFileReading(File file)
            throws IOException {
        try (InputStream in = new FileInputStream(file);
             Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
             // buffer for efficiency
             Reader buffer = new BufferedReader(reader)) {
            handleCharactersReading(buffer);
        }
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
        if (this.words.containsKey(word)) {
            this.words.put(word, words.get(word) + 1);
        } else {
            this.words.put(word, 1);
        }
    }

    public void printWords() {
        this.words = this.getSortedMap(this.words);
        Integer counter = 0;
        for (Map.Entry<String, Integer> entry : this.words.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (counter.equals(this.wordsCount)) {
                break;
            }
            System.out.println(key + " " + value);
            counter++;
        }
    }

    private Map<String, Integer> getSortedMap(Map<String, Integer> map) {
        Map<String, Integer> result = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

}
