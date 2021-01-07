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

    public static void main(String... args) throws FileNotFoundException {
        Scanner fileContent = new Scanner(new File("src/main/java/com/appland/java1/input.txt")).useDelimiter("\\n");

        System.out.println("" + getHighestSeatId(fileContent));
    }

    private static String getHighestSeatId(Scanner fileContent) {
        int seatId = -1;

        while (fileContent.hasNext()) {
            int f = 0;
            int b = 127;
            int l = 0;
            int r = 7;
            String input = fileContent.next();
            for (int i = 0; i <= 6; i++) {
                char c = input.charAt(i);
                if (c == 'F') {
                    b = (int) Math.floor((f + b) / 2.0);
                } else if (c == 'B') {
                    f = (int) Math.ceil((f + b) / 2.0);
                }
            }
            for (int i = 7; i <= 9; i++) {
                char c = input.charAt(i);
                if (c == 'R') {
                    l = (int) Math.ceil((l + r) / 2.0);
                } else if (c == 'L') {
                    r = (int) Math.floor((l + r) / 2.0);
                }
            }
            if (seatId < ((f * 8) + l)) {
                seatId = (f * 8) + l;
            }

        }

        return "" + seatId;
    }

}
