package com.devonjp;

import java.util.ArrayList;

public class CaesarСipher {

    private ArrayList<Character> englishUppers = new ArrayList<>();
    private ArrayList<Character> englishLowers = new ArrayList<>();
    private String result = "";

    public void start(String mode, String line, Integer key) {
        this.initializeEnglishAlphabet();
        if (mode.equals("-e")) {
            this.result = getEncodeLine(line, key);
        } else if (mode.equals("-d")) {
            this.result = getDecodeLine(line, key);
        }
    }

    public String getResult() {
        return this.result;
    }

    private void initializeEnglishAlphabet() {
        for (int i = 65; i <= 90; i++) {
            englishUppers.add((char) i);
            englishLowers.add((char) (i + 32));
        }
    }

    private String getEncodeLine(String line, Integer key) {
        String result = "";
        for (int i = 0; i < line.length(); i++) {
            Character ch = line.charAt(i);
            if (ch.equals(" ")) {
                result += ch;
                continue;
            }
            if (this.englishUppers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenEncoding(this.englishUppers, ch, key);
            } else if (this.englishLowers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenEncoding(this.englishLowers, ch, key);
            }
        }

        return result;
    }

    private String getDecodeLine(String line, int key) {
        String result = "";
        for (int i = 0; i < line.length(); i++) {
            Character ch = line.charAt(i);
            if (ch.equals(" ")) {
                result += ch;
                continue;
            }
            if (this.englishUppers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenDecoding(this.englishUppers, ch, key);
            } else if (this.englishLowers.contains(ch)) {
                result += getCharacterFromAlphabetWithOffsetWhenDecoding(this.englishLowers, ch, key);
            }
        }
        return result;
    }


    private Character getCharacterFromAlphabetWithOffsetWhenEncoding(ArrayList<Character> alphabet, Character ch, Integer key) {
        Integer symbol = alphabet.indexOf(ch);
        Integer sum = symbol + key;
        Integer index = 0;
        if (sum > alphabet.size() - 1) {
            index = sum - (((int) Math.floor(sum / alphabet.size())) * alphabet.size());
            return alphabet.get(index);
        }
        return alphabet.get(symbol + key);
    }

    private Character getCharacterFromAlphabetWithOffsetWhenDecoding(ArrayList<Character> alphabet, Character ch, Integer key) {
        Integer symbol = alphabet.indexOf(ch);
        Integer diff = symbol - key;
        Integer index = 0;
        if (diff < 0) {
            Integer delta = (- diff - (((int) Math.floor( -diff / alphabet.size())) * alphabet.size()));
            if (delta == 0) {
                delta = alphabet.size();
            }
            index = alphabet.size() - delta;
            return alphabet.get(index);
        }
        return alphabet.get(symbol - key);
    }

}

