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

        System.out.println("" + getValidPassportCount(fileContent));
    }

    static int getValidPassportCount(Scanner fileContent) {
                / boolean isNewPassport;
        int count = 0;
        ArrayList<String> passport = new ArrayList<>();
        StringBuilder exact = new StringBuilder();
        while (fileContent.hasNext()) {
            String line = fileContent.nextLine();
            isNewPassport = line.equals("");
            if (!isNewPassport) {
                exact.append("\n" + line);
                String[] fields = line.split(" ");
                for (String f : fields) {
                    passport.add(f);
                }

            } else {
                if (isPassportValid(passport)) {
                    count++;
                }
                passport = new ArrayList<>();
                exact.setLength(0);
            }
        }
        //we didn't check the last passport
        if (isPassportValid(passport)) {
            count++;
        }
        return count;
    }

    private static boolean isPassportValid(ArrayList<String> passport) {
        ArrayList<String> fields = new ArrayList<>();
        for (String field : passport) {
            fields.add(field.split(":")[0]);
        }
        //check if all the required fields are available
        ArrayList<String> validFields = getValidFields();
        for (String vf : validFields) {
            if (!fields.contains(vf)) {
                return false;
            }
        }
        //
        return true;
    }

    private static ArrayList<String> getValidFields() {
        ArrayList<String> validFields = new ArrayList<>();
        validFields.add("byr");
        validFields.add("iyr");
        validFields.add("eyr");
        validFields.add("hgt");
        validFields.add("hcl");
        validFields.add("ecl");
        validFields.add("pid");
//        validFields.add("cid");
        return validFields;
    }

}
