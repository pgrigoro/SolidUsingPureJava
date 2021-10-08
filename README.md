[![CircleCI](https://circleci.com/gh/pgrigoro/assessment/tree/master.svg?style=svg)](https://circleci.com/gh/pgrigoro/assessment/tree/master)

# Documentation

### Modules

**1. findseven: A function (findSeven) that takes an array of numbers and return "Found"**

if the character 7 appears in the array of the numbers. Otherwise, return "there is no 7 in the array".

Examples :

    findSeven(\[1, 2, 3, 4, 5, 6, 7\]) ➞ "Found"
    // 7 contains the number seven.
    
    findSeven(\[8, 6, 33, 100\]) ➞ "there is no 7 in the array"
    // None of the items contain 7 within them.
    
    findSeven(\[2, 55, 60, 97, 86\]) ➞ "Found"
    // 97 contains the number seven.


**2. digitsum: A function that accepts an integer and calculates the sum of it's digits.**

If the sum is greater than 9 repeats the calculation of the sum of it's digits until we get sum < 10.
Returns the final sum.

Examples:

    10 -> 1 + 0 = 1 ... returns 1 
    38 -> 3 + 8 = 11 -> 1 + 1 = 2 ... returns 2 
    785 -> 7 + 8 + 5 = 20 -> 2 + 0 = 2 ... returns 2 
    99892 -> 9 + 9 + 8 + 9 + 2 = 37 -> 3 + 7 = 10 -> 1 + 0 = 1 returns 1


**3. doremake: A function (doRemake) that takes a string of words and**
 - Moves the first letter of each word to the end of the word.
 - Adds "ay" to the end of the word.
 - Words starting with a vowel (a,e,i,o,u, A, E, I, O, U) simply have "way" appended to the end.
 - Be sure to preserve proper capitalization and punctuation.

Examples:

    doRemake("Cats are great pets.") 
    returns "Atscay areway reatgay etspay."
    
    doRemake("Tom got a small piece of pie.") 
    returns "Omtay otgay away mallsay iecepay ofway iepay."
    
    doRemake("He told us a very exciting tale.") 
    returns "Ehay oldtay usway away eryvay excitingway aletay."

**4. weather: A module capable to examine whether data and depending on the temperature send an sms message to a specified number.**

Uses the Weather Api https://openweathermap.org/ to access current weather data for the Thessaloniki.

API documentation https://openweathermap.org/api.
For the api calls uses the key b385aa7d4e568152288b3c9f5c2458a5

If the temperature is greater than 20C send SMS message to +30 6922222222 with text "Your name and Temperature more than 20C. &lt;the actual temperature&gt;" else send sms message to +30  6922222222 with text "Your name and Temperature less than 20C. &lt;the actual temperature&gt;"
where &lt;the actual temperature&gt; the temperature that the weather api returns for Thessaloniki.

**Repeat the above procedure every 10 minutes for 10 times and then stop.**

In order to send the SMS uses the Routee API
https://docs.routee.net/docs/

*Application ID for Routee API: 5c5d5e28e4b0bae5f4accfec*

*Application secret: MGkNfqGud0*

**General Guidelines:**

    Use pure Java
    Use object-oriented programming
    Use SOLID design principles
    The code should be well documented.
    Use defensive programming approach.