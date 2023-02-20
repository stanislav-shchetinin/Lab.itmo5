package service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

public class Parse {

    private static final Logger logger = Logger.getLogger(Parse.class.getName());
    public static String parseFromCSVtoString(File file) throws FileNotFoundException {
        String res = "";
        Scanner in = new Scanner(file);
        while (in.hasNext()){
            try {
                Reader reader = new StringReader(in.next());
                CSVReader csvReader = new CSVReader(reader);

                String[] record;
                while ((record = csvReader.readNext()) != null) {
                    res += String.join(" ", record);
                    res += "\n";
                }
            } catch (IOException e){
                logger.warning(String.format("Фаил с именем %s не найден", e.getMessage()));
            } catch (CsvValidationException e){
                logger.warning(e.getMessage());
            }

        }
        in.close();
        return res;

    }



}
