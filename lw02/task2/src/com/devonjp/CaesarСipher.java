package com.devonjp;

import java.util.ArrayList;

public class CaesarСipher {

    private ArrayList<Character> englishUppers = new ArrayList<>();
    private ArrayList<Character> englishLowers = new ArrayList<>();
    private ArrayList<Character> russianUppers = new ArrayList<>();
    private ArrayList<Character> russianLowers = new ArrayList<>();

    private String word;
    private Integer key;
    private String mode;
    private String result = "";

    public CaesarСipher(String word, Integer key, String mode) {
        this.word = word;
        this.key = key;
        this.mode = mode;
    }

    private void initializeAlphabet() {
        this.initializeEnglishAlphabet();
        this.initializeRussianAlphabet();
    }

    private void initializeRussianAlphabet() {
        for (int i = 1040; i < 1072; i++) {
            russianUppers.add((char) (i));
            russianLowers.add((char) (i + 32));
            if (i == 1045) {
                russianUppers.add((char) 1025);
                russianLowers.add((char) 1025);
            }
        }
    }

    private void initializeEnglishAlphabet() {
        for (int i = 65; i <= 90; i++) {
            englishUppers.add((char) i);
            englishLowers.add((char) (i + 32));
        }
    }

    public void start() {
        this.initializeAlphabet();
        if (this.mode.equals("-e")) {
            this.result = GetEncodeWord(word, key);
        } else if (this.mode.equals("-d")) {
            this.result = GetDecodeWord(word, key);
        }
    }

    public String getResult() {
        return this.result;
    }

    private String GetEncodeWord(String word, int key) {
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            if (this.englishUppers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenEncoding(this.englishUppers, ch);
            } else if (this.englishLowers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenEncoding(this.englishLowers, ch);
            } else if (this.russianUppers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenEncoding(this.russianUppers, ch);
            } else if (this.russianLowers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenEncoding(this.russianLowers, ch);
            }
        }

        return result;
    }

    private Character getCharacterFromAlphabetWithOffsetWhenEncoding(ArrayList<Character> alphabet, Character ch) {
        Integer symbol = alphabet.indexOf(ch);
        Integer sum = symbol + key;
        Integer index = 0;
        if (sum > alphabet.size()) {
            index = sum - (((int) Math.floor(sum / alphabet.size())) * alphabet.size());
            return alphabet.get(index);
        }
        return alphabet.get(symbol + key);
    }

    private String GetDecodeWord(String word, int key) {
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            if (this.englishUppers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenDecoding(this.englishUppers, ch);
            } else if (this.englishLowers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenDecoding(this.englishLowers, ch);
            } else if (this.russianUppers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenDecoding(this.russianUppers, ch);
            } else if (this.russianLowers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenDecoding(this.russianLowers, ch);
            }
        }

        return result;
    }

    private Character getCharacterFromAlphabetWithOffsetWhenDecoding(ArrayList<Character> alphabet, Character ch) {
        Integer symbol = alphabet.indexOf(ch);
        Integer diff = symbol - key;
        Integer index = 0;
        if (diff < 0) {
            index = alphabet.size() - (((int) Math.floor(diff / alphabet.size())) * alphabet.size());
            return alphabet.get(index - 1);
        }
        return alphabet.get(symbol - key);
    }

}
