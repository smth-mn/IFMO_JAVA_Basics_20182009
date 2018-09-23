package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class WarAndPeaceExercise {

    public static void main(String[] args) throws IOException{
        System.out.println(warAndPeace());
    }

    public static String warAndPeace() throws IOException {
        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");

        // TODO map lowercased words to its amount in text and concatenate its entries.
        // TODO If word "котик" occurred in text 23 times then its entry would be "котик - 23\n".
        // TODO Entries in final String should be also sorted by amount and then in alphabetical order if needed.
        // TODO Also omit any word with lengths less than 4 and frequency less than 10

        //reading all words
        Map<String, Integer> dictionary = new HashMap<>();

        List<String> stringList = Files.readAllLines(tome12Path, Charset.forName("Cp1251"));
        processLines(dictionary, stringList);

        stringList = Files.readAllLines(tome34Path, Charset.forName("Cp1251"));
        processLines(dictionary, stringList);

        //preparing word list
        List<String> alphabeticalOrderList = new LinkedList<>();

        // bullshit code to avoid ConcurrentModificationException
        Set<String> words = new HashSet<>();
        words.addAll(dictionary.keySet());

        for(String word : words){
//            alphabeticalOrderList.add(word);
            if(dictionary.get(word) < 10){
                dictionary.remove(word);
                continue;
            }
            alphabeticalOrderList.add(word);
        }

//        for(String word : alphabeticalOrderList){
//            if(word.contains("день")){
//                System.out.println(word);
//            }
//        }

        //preparing output

        alphabeticalOrderList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        //finding the biggest number of entries
        int maximalLeftNumber = 0;
        for(int number : dictionary.values()){
            if(number > maximalLeftNumber) maximalLeftNumber = number;
        }

        StringBuilder builder = new StringBuilder();

        List<String> deletedWords = new ArrayList<>();
        while(!dictionary.isEmpty()){
            for(String word : alphabeticalOrderList){
                if(dictionary.get(word) == maximalLeftNumber){
                    builder.append(word);
                    builder.append(" - ");
                    builder.append(maximalLeftNumber);
                    builder.append("\n");
                    dictionary.remove(word);
                    deletedWords.add(word);
                }
            }
            maximalLeftNumber--;

            alphabeticalOrderList.removeAll(deletedWords);
            deletedWords.clear();
        }

        return builder.toString().trim();
    }

    private static void processLines(Map<String, Integer> dictionary, List<String> stringList){
        for(String line : stringList){
            List<String> words =
                    Arrays.asList(line.split("\\s|\\,|\\.|\\;|\\:|\\!|\\(|\\)|/|\"|\'|\\?|>|<" +
                            "|«|»|\\…|\\]|\\[|\\„|1|2|3|4|5|6|7|8|9|0|-|“"));

            for(String word : words){
                word = word.toLowerCase();
                if(word.length() < 4) continue;
                if(!dictionary.containsKey(word)){
                    dictionary.put(word, 0);
                }
                dictionary.put(word, dictionary.get(word) + 1);
            }
        }
    }

}