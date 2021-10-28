package com.route.findseven;

import java.util.Arrays;

public class SevenOperations {

    public String findSeven(Integer[] numbers){
        return Arrays.asList(numbers).stream()
                .filter(n -> String.valueOf(n).contains("7"))
                .map(n -> "Found")
                .findFirst().orElse("there is no 7 in the array");
    }

}
