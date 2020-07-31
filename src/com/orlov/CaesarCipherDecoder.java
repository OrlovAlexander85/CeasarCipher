package com.orlov;

import java.util.ArrayList;

public class CaesarCipherDecoder {
    ArrayList<Character> alphabetArrayList = new ArrayList<>();
    ArrayList<Character> inputTextCharArrayList = new ArrayList<>();
    ArrayList<Integer> textCharAlphabeticPosition = new ArrayList<>();
    private final char[] alphabetArray = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
    String inputText;

    public CaesarCipherDecoder(String inputText) {
        this.inputText = inputText;
    }

    public String decodeText(int step) {
        getAlphabetArrayList();
        textToArrayList();
        getTextCharAlphabeticalPosition();
        return modifiedCharListToString(modifiedCharacterList(movedCharPositions(step)));
    }

    public void textToArrayList() {
        char[] chars = inputText.toLowerCase().toCharArray();
        for (char c : chars) {
            inputTextCharArrayList.add(c);
        }
    }

    private void getTextCharAlphabeticalPosition() {
        for (Character c : inputTextCharArrayList) {
            if (Character.isLetter(c)) {
                textCharAlphabeticPosition.add(alphabetArrayList.indexOf(c));
            } else {
                textCharAlphabeticPosition.add(-1);
            }
        }
    }

    private void getAlphabetArrayList() {
        for (char c : alphabetArray) {
            alphabetArrayList.add(c);
        }
    }

    private ArrayList<Integer> movedCharPositions(int step) {
        ArrayList<Integer> modifiedPositionArrayList = new ArrayList<>();
        for (Integer position : textCharAlphabeticPosition) {
            if (position != -1) {
                position = (position + step) % alphabetArrayList.size();
            }
            modifiedPositionArrayList.add(position);
        }
        return modifiedPositionArrayList;
    }

    private ArrayList<Character> modifiedCharacterList(ArrayList<Integer> movedCharPositions) {
        ArrayList<Character> modifiedCharacterArrayList = new ArrayList<>();
        for (int position = 0; position < movedCharPositions.size(); position++) {
            if (movedCharPositions.get(position) != -1) {
                modifiedCharacterArrayList.add(alphabetArrayList.get(movedCharPositions.get(position)));
            } else {
                modifiedCharacterArrayList.add(inputTextCharArrayList.get(position));
            }
        }
        return modifiedCharacterArrayList;
    }

    private String modifiedCharListToString(ArrayList<Character> charArrayList) {
        StringBuilder builder = new StringBuilder(charArrayList.size());
        for (Character ch : charArrayList) {
            builder.append(ch);
        }
        return builder.toString();
    }
}
