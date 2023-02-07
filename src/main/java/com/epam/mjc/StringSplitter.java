package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        List<String> listOfDelimiters;
        listOfDelimiters = new ArrayList<>(delimiters);

        List<String> substrings = new ArrayList<>();
        substrings.add(source);

        List<String> temp = new ArrayList<>();

        for (String delimiter : listOfDelimiters) {

            for (String substring : substrings) {

                StringTokenizer stringTokenizer = new StringTokenizer(substring, delimiter);

                while (stringTokenizer.hasMoreTokens())
                    temp.add(stringTokenizer.nextToken());
            }

            substrings.clear();
            substrings.addAll(temp);
            temp.clear();
        }

        return substrings;

    }
}
