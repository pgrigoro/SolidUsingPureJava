package com.route.doremake;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

public abstract class WordStrategyResolver {

    private static final List<WordStrategy> STRATEGIES = asList(new VowelStrategyImpl(), new NonVowelStrategyImpl());

    private WordStrategyResolver(){
    }

    public static Optional<WordStrategy> resolve(String word) {
        return STRATEGIES.stream()
                .filter(s -> s.isApplicable(word))
                .findFirst();
    }

}
