package com.route.doremake;

public interface WordStrategy {

    String PERIOD = ".";
    String EMPTY = "";
    String SPACE = " ";

    String process(String word);

    boolean isApplicable(String word);

    static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    static boolean isNonVowel(char c) {
        return !isVowel(c);
    }

}
