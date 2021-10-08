package com.route.doremake;

public class VowelStrategyImpl implements WordStrategy {

    @Override
    public String process(String word) {
        return String.format("%sway", word);
    }

    @Override
    public boolean isApplicable(String word) {
        return WordStrategy.isVowel(word.charAt(0));
    }

}
