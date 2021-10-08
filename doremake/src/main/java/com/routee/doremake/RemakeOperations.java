package com.routee.doremake;

import java.util.Arrays;

import static com.routee.doremake.WordStrategy.SPACE;
import static java.util.stream.Collectors.joining;

public class RemakeOperations {

    public String doRemake(String sentence) {
        return Arrays.asList(sentence.split(SPACE)).stream()
                .map(this::process)
                .collect(joining(SPACE));
    }

    private String process(String word) {
        return WordStrategyResolver.resolve(word)
                .map(s -> s.process(word))
                .orElse(word);
    }

}