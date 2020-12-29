/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appland.java1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author tariqul
 */
public class Main {

    public static void main(String... args) {
        int[] rightValues = new int[]{1, 3, 5, 7, 1};
        int[] downValues = new int[]{1, 1, 1, 1, 2};
        int[] countOfTrees = new int[rightValues.length];
        long treesMultiply = 1;

        for (int i = 0; i < rightValues.length; i++) {
            countOfTrees[i] = getCountOfTrees(rightValues[i], downValues[i]);
        }
        for (int n : countOfTrees) {
            treesMultiply = treesMultiply * n;
        }
        System.out.println("" + Arrays.toString(countOfTrees));
        System.out.println("" + treesMultiply);
    }

    static int getCountOfTrees(int nextRight, int nextDown) {
        int trees = 0;
        try {
            Scanner fileContent = new Scanner(new File("src/main/java/com/appland/java1/input.txt")).useDelimiter("\\n");
            int locationNow = 0;
            boolean shouldIncreaseByThree = false;
            int numberOfTrees = 0;
            String line = fileContent.next();
//            System.out.println(line);
            while (fileContent.hasNext()) {
                locationNow = (locationNow + nextRight) % line.trim().length();
                line = getNextLine(fileContent, nextDown);
                if (isTreeFound(line, locationNow)) {
                    trees++;
                }
//                System.out.println(line);
                //System.out.println("Location now: " + locationNow + ", " + line.charAt(locationNow));
            }
            //System.out.println("" + countOfTrees);

        } catch (FileNotFoundException fnfe) {
        }
        return trees;
    }

    static String getNextLine(Scanner fileContent, int nextDown) {
        int downCount = 0;
        String line = "";
        while (fileContent.hasNext() && ++downCount <= nextDown) {
            line = fileContent.next();
        }
        return line;
    }

    static boolean isTreeFound(String line, int location) {
        if (line.charAt(location) == '#') {
            return true;
        }
        return false;
//        if (line.charAt(location) == '#') {
//            countOfTrees++;
//            return line.substring(0, location) + 'X' + line.substring(location + 1);
//        } else {
//            return line.substring(0, location) + 'O' + line.substring(location + 1);
//        }
    }

}
