import base.Vehicle;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import service.Parse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException, CsvValidationException, FileNotFoundException {


        try (Reader reader = Files.newBufferedReader(Paths.get("files/input.csv"));
             CSVReader csvReader = new CSVReader(reader)) {

            // read one record at a time
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                System.out.println(String.join(", ", record));
            }

        } catch (IOException | CsvValidationException ex) {
            System.err.println(ex.getMessage());
        }


    }
}