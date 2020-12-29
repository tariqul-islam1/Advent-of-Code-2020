/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appland.java1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author tariqul
 */
public class Main {

    static int countOfTrees = 0;

    public static void main(String... args) {
        try {
            Scanner fileContent = new Scanner(new File("src/main/java/com/appland/java1/input.txt")).useDelimiter("\\n");
            int locationNow = 3;
            boolean shouldIncreaseByThree = false;
            int numberOfTrees = 0;
            String line = fileContent.next();
            //System.out.println(line);
            while (fileContent.hasNext()) {
                line = fileContent.next();
                if (shouldIncreaseByThree) {
                    line = getUpdatedLine(line, locationNow);
                    locationNow = (locationNow + 3) % line.trim().length();
                    shouldIncreaseByThree = false;
                } else {
                    shouldIncreaseByThree = true;
                    line = getUpdatedLine(line, locationNow);
                    locationNow = (locationNow + 3) % line.trim().length();
                }
                //System.out.println(line);
                //System.out.println("Location now: " + locationNow + ", " + line.charAt(locationNow));
            }
            System.out.println("" + countOfTrees);

        } catch (FileNotFoundException fnfe) {
        }

    }

    static String getUpdatedLine(String line, int location) {
        if (line.charAt(location) == '#') {
            countOfTrees++;
            return line.substring(0, location) + 'X' + line.substring(location + 1);
        } else {
            return line.substring(0, location) + 'O' + line.substring(location + 1);
        }
    }

}
