package com.routee.doremake;

public class NonVowelStrategyImpl implements WordStrategy {

    @Override
    public String process(String word) {
        boolean isUpper = Character.isUpperCase(word.charAt(0));
        boolean hasPeriod = word.endsWith(PERIOD);
        word = word.replace(PERIOD, EMPTY);
        return new StringBuilder()
                .append(isUpper ? word.substring(1, 2).toUpperCase() : word.substring(1, 2))
                .append(word.substring(2))
                .append(word.substring(0, 1).toLowerCase() + "ay")
                .append(hasPeriod ? PERIOD : EMPTY)
                .toString();
    }

    @Override
    public boolean isApplicable(String word) {
        return WordStrategy.isNonVowel(word.charAt(0));
    }

}