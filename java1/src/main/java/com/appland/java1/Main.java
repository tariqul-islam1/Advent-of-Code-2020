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
        boolean isNewPassport;
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
        //validate the fields
        for (String p : passport) {
            String key = p.split(":")[0];
            String value = p.split(":")[1];
            switch (key) {
                case "byr":
                    if (value.length() != 4 || Integer.valueOf(value) < 1920 || Integer.valueOf(value) > 2002) {
                        return false;
                    }
                    break;
                case "iyr":
                    if (value.length() != 4 || Integer.valueOf(value) < 2010 || Integer.valueOf(value) > 2020) {
                        return false;
                    }
                    break;
                case "eyr":
                    if (value.length() != 4 || Integer.valueOf(value) < 2020 || Integer.valueOf(value) > 2030) {
                        return false;
                    }
                    break;
                case "hgt":
                    String unit = value.substring(value.length() - 2, value.length());
                    int reading = Integer.valueOf(value.substring(0, value.length() - 2));
                    if (unit.equals("cm")) {
                        if (reading < 150 || reading > 193) {
                            return false;
                        }
                    } else if (unit.equals("in")) {
                        if (reading < 59 || reading > 76) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
                case "hcl":
                    if (!value.matches("^[#][\\da-f]{6}")) {
                        return false;
                    }
                    break;
                case "ecl":
                    if (!getEyeColors().contains(value)) {
                        return false;
                    }
                    break;
                case "pid":
                    if (!value.matches("[0\\d]{9}")) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
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

    private static ArrayList<String> getEyeColors() {
        //amb blu brn gry grn hzl oth
        ArrayList<String> validColors = new ArrayList<>();
        validColors.add("amb");
        validColors.add("blu");
        validColors.add("brn");
        validColors.add("gry");
        validColors.add("grn");
        validColors.add("hzl");
        validColors.add("oth");
        return validColors;
    }

}
