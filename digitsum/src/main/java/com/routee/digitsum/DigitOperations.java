package com.route.digitsum;


public class DigitOperations {

    public int digitSum(int number){
        while (number > 9) {
            number = String.valueOf(number).chars()
                    .map(Character::getNumericValue)
                    .reduce(0, Integer::sum);
        }

        return number;
    }

}
