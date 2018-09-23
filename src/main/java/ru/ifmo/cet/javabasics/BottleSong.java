package ru.ifmo.cet.javabasics;

import java.util.HashMap;
import java.util.Map;

/**
 * Нужно реализовать констурктор и метод, возвращающий слова песни про бутылки на стене.
 * <p>
 * Слова следующие:
 * <p>
 * 99 bottles of beer on the wall, 99 bottles of beer
 * Take one down, pass it around, 98 bottles of beer
 * 98 bottles of beer on the wall, 98 bottles of beer
 * Take one down, pass it around, 97 bottles of beer
 * 97 bottles of beer on the wall, 97 bottles of beer
 * Take one down, pass it around, 96 bottles of beer
 * 96 bottles of beer on the wall, 96 bottles of beer
 * Take one down, pass it around, 95 bottles of beer
 * 95 bottles of beer on the wall, 95 bottles of beer
 * ...
 * <p>
 * 3 bottles of beer on the wall, 3 bottles of beer
 * Take one down, pass it around, 2 bottles of beer
 * 2 bottles of beer on the wall, 2 bottles of beer
 * Take one down, pass it around, 1 bottles of beer
 * 1 bottle of beer on the wall, 1 bottle of beer
 * Take one down, pass it around, no more bottles of beer on the wall
 * No more bottles of beer on the wall, no more bottles of beer
 * Go to the store and buy some more, 99 bottles of beer on the wall
 * <p>
 * Дело усложняется тем, что текст песни переменный:
 * За раз может быть взято несколько бутылок.
 * Значение передается в качестве параметра конструктора
 * Нужно ограничить возможность взятия бутылок натуральным число не более 99 бутылок за раз.
 */
public class BottleSong {

    private int bottleTakenAtOnce = 0;

    private static final HashMap<Integer, String> basicNumbersSpelling = new HashMap<Integer, String>(){{
        put(1, "one");
        put(2, "two");
        put(3, "three");
        put(4, "four");
        put(5, "five");
        put(6, "six");
        put(7, "seven");
        put(8, "eight");
        put(9, "nine");
        put(0, "zero");
    }};

    public BottleSong(int newBottleTakenAtOnce) {
        if (newBottleTakenAtOnce > 99 || newBottleTakenAtOnce < 1){
            throw new IllegalArgumentException("Bad bottle number");
        }
        bottleTakenAtOnce = newBottleTakenAtOnce;
    }

    public String getBottleSongLyrics() {
        StringBuilder builder = new StringBuilder();
        int left = 99;
        while (left > 0){
            builder.append(left);
            builder.append(" bottle");
            if(left != 1){
                builder.append("s");
            }
            builder.append(" of beer on the wall, ");
            builder.append(left);
            builder.append(" bottle");
            if(left != 1){
                builder.append("s");
            }
            builder.append(" of beer.\n");

            builder.append("Take ");
            builder.append(getNumberSpelling(left > bottleTakenAtOnce ? bottleTakenAtOnce : left));
            builder.append(" down and pass around, ");
            left = left - bottleTakenAtOnce;
            if(left > 0){
                builder.append(left);
                builder.append(" bottle");
                if(left != 1){
                    builder.append("s");
                }
                builder.append(" of beer on the wall.\n");
            }
            else{
                builder.append("no more bottles of beer on the wall.\n");
            }
        }

        builder.append("No more bottles of beer on the wall, no more bottles of beer.\n");
        builder.append("Go to the store and buy some more, 99 bottles of beer on the wall.\n");

        return builder.toString();
    }

    private static String getNumberSpelling(int number){
        if (number > 99 || number < 0){
            throw new IllegalArgumentException("Bad number");
        }

        if(number / 10 == 0){
            return basicNumbersSpelling.get(number);
        }
        if(number / 10 == 1) {
            if (number == 10) return "ten";
            if (number == 11) return "eleven";
            if (number == 12) return "twelve";
            if (number == 13) return "thirteen";
            if (number == 14) return "fourteen";
            if (number == 15) return "fifteen";
            if (number == 16) return "sixteen";
            if (number == 17) return "seventeen";
            if (number == 18) return "eighteen";
            if (number == 19) return "nineteen";
        }
        if(number / 10 == 2){
            String ending = number % 10 == 0 ? "" : " " + getNumberSpelling(number % 10);
            return "twenty" + ending;
        }
        if(number / 10 == 3){
            String ending = number % 10 == 0 ? "" : " " + getNumberSpelling(number % 10);
            return "thirty" + ending;
        }
        if(number / 10 == 4){
            String ending = number % 10 == 0 ? "" : " " + getNumberSpelling(number % 10);
            return "forty" + ending;
        }
        if(number / 10 == 5){
            String ending = number % 10 == 0 ? "" : " " + getNumberSpelling(number % 10);
            return "fifty" + ending;
        }
        if(number / 10 == 6){
            String ending = number % 10 == 0 ? "" : " " + getNumberSpelling(number % 10);
            return "sixty" + ending;
        }
        if(number / 10 == 7){
            String ending = number % 10 == 0 ? "" : " " + getNumberSpelling(number % 10);
            return "seventy" + ending;
        }
        if(number / 10 == 8){
            String ending = number % 10 == 0 ? "" : " " + getNumberSpelling(number % 10);
            return "eighty" + ending;
        }
        if(number / 10 == 9){
            String ending = number % 10 == 0 ? "" : " " + getNumberSpelling(number % 10);
            return "ninety" + ending;
        }

        throw new IllegalArgumentException("Bad number");
    }
}
