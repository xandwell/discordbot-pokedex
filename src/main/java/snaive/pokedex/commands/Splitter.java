package snaive.pokedex.commands;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Splitter {

    // makasih orang baik di internet.
    // (https://stackoverflow.com/questions/65686733/how-to-work-with-multiple-strings-and-different-types-of-data-on-a-single-comman)
    private ArrayList<String> split(String subjectString){
        ArrayList<String> matchList = new ArrayList<>();
        Pattern regex = Pattern.compile("\"([^\"\\\\]*(?:\\\\.[^\"\\\\]*)*)\"|'([^'\\\\]*(?:\\\\.[^'\\\\]*)*)'|[^\\s]+");
        Matcher regexMatcher = regex.matcher(subjectString);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                // Add double-quoted string without the quotes
                matchList.add(regexMatcher.group(1));
            } else if (regexMatcher.group(2) != null) {
                // Add single-quoted string without the quotes
                matchList.add(regexMatcher.group(2));
            } else {
                // Add unquoted word
                matchList.add(regexMatcher.group());
            }
        }
        return matchList;
    }



}
