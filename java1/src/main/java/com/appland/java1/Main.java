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


    
    public static void main(String... args) {
        try{
            Scanner fileContent = new Scanner(new File("src/main/java/com/appland/java1/input.txt")).useDelimiter("\\n");
            int correct = 0;
            while (fileContent.hasNext()) {
                String next = fileContent.next();
                String[] parts = next.split(" ");
                String[] minMax = parts[0].split("-");
                int min = Integer.valueOf(minMax[0]);
                int max = Integer.valueOf(minMax[1]);
                char c =  parts[1].charAt(0);
                
                int occurance = 0;
                for(char a : parts[2].toCharArray()){
                    if(a == c){
                        occurance++;
                    }
                }
                if(occurance >= min && occurance <= max){
                    correct++;
                }
            }
            System.out.println("" + correct);
            
        } catch (FileNotFoundException fnfe){
        }
        
        
    }

}
