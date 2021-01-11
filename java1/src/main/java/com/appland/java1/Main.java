/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appland.java1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tariqul
 */
public class Main {

    public static void main(String... args) throws FileNotFoundException {
        Scanner fileContent = new Scanner(new File("src/main/java/com/appland/java1/input.txt")).useDelimiter("\\n");

        System.out.println("" + getSumOfCounts(fileContent));
    }

    private static int getSumOfCounts(Scanner fileContent) {
        int finalCount = 0;
        ArrayList<String> persons = new ArrayList<>();

        while (fileContent.hasNext()) {
            String person = fileContent.next();
            if (!person.equals("")) {
                persons.add(person);
            } else {
                finalCount += getCountForGroup(persons);
                persons = new ArrayList<>();
            }
        }
        finalCount += getCountForGroup(persons);

        return finalCount;
    }

    private static int getCountForGroup(ArrayList<String> persons) {
        String commonChars = "";
        if (persons.size() == 1) {
            return persons.get(0).length();
        }
        for (int i = 0; i < persons.size(); i++) {
            if (i == 0) {
                commonChars = getCommonChars(persons.get(i), persons.get(i + 1));
                i = i + 1;
            } else {
                commonChars = getCommonChars(commonChars, persons.get(i));
            }
            if (commonChars.length() == 0) {
                break;
            }
        }
        return commonChars.length();
    }

    private static String getCommonChars(String a, String b) {
        StringBuilder commons = new StringBuilder();

        for (int i = 0; i < a.length(); i++) {
            if (b.contains("" + a.charAt(i))) {
                commons.append("" + a.charAt(i));
            }
        }
        return commons.toString();
    }

}
