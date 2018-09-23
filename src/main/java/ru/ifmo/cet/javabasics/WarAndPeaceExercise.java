package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WarAndPeaceExercise {

    private static final String parsingRegexp =
            "\\s|\\,|\\.|\\;|\\:|\\!|\\(|\\)|/|\"|\'|\\?|>|<" +
            "|«|»|\\…|\\]|\\[|\\„|1|2|3|4|5|6|7|8|9|0|-|“";

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO Iff word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order iff needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        return Stream.concat(
                Files.readAllLines(tome12Path, Charset.forName("Cp1251")).stream(),
                Files.readAllLines(tome34Path, Charset.forName("Cp1251")).stream())
                .flatMap((line) -> Arrays.stream(line.split(parsingRegexp)))
                .map(String::toLowerCase)
                .filter( word -> word.length() > 3)
                .collect(Collectors.toMap( word -> word,
                        word -> 1,
                        (number, anotherNumber) -> number + anotherNumber))
                .entrySet().stream().filter((entry) -> entry.getValue() >=10)
                .sorted( (entry, anotherEntry) ->
                    entry.getValue() - anotherEntry.getValue() == 0 ?
                        entry.getKey().compareTo(anotherEntry.getKey()) :
                        anotherEntry.getValue() - entry.getValue()
                )
                .map( entry -> entry.getKey() + " - " + entry.getValue() + "\n")
                .reduce( (string, anotherString) -> string + anotherString).get().trim();
    }

}