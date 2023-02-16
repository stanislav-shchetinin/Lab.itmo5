package service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Parse {
    public static String parseFromCSVtoString(Scanner in){
        String res = "";
        while (in.hasNext()){
            try {
                Reader reader = Files.newBufferedReader(Paths.get(in.next()));
                CSVReader csvReader = new CSVReader(reader);

                String[] record;
                while ((record = csvReader.readNext()) != null) {
                    res += String.join(" ", record);
                    res += "\n";
                }
            } catch (IOException e){
                System.out.println("fr");
            } catch (CsvValidationException e){
                System.out.println("ef");
            }


        }

        return res;

    }



}
