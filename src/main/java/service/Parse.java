package service;

import base.Coordinates;
import base.Vehicle;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.logging.Logger;

public class Parse {

    private static final Logger logger = Logger.getLogger(Parse.class.getName());
    public static String parseFromCSVtoString(File file) {

        String res = "";

        try (Scanner in = new Scanner(file)){
            in.nextLine(); //Заголовок
            while (in.hasNext()) {

                Reader reader = new StringReader(in.next());
                CSVReader csvReader = new CSVReader(reader);

                String[] record;
                while ((record = csvReader.readNext()) != null) {
                    res += String.join(" ", record);
                    res += "\n";
                }
            }
        } catch (FileNotFoundException e){
            logger.warning(String.format("%s: Нет прав на чтение файла", e.getMessage()));
        } catch (IOException e){
            logger.warning(String.format("Файл с именем %s не найден", e.getMessage()));
        } catch (CsvValidationException e){
            logger.warning(e.getMessage());
        }


        return res;

    }

    public static String queueToString(PriorityQueue<Vehicle> priorityQueue){
        String ans = "";
        while (!priorityQueue.isEmpty()){
            Vehicle vehicle = (Vehicle) priorityQueue.poll();
            for (Field field : vehicle.getClass().getDeclaredFields()){
                field.setAccessible(true);
                try{
                    if (field.getType() == Double.class || field.getType() == double.class){
                        ans += String.format("\"%f\"", field.get(vehicle));
                    } else if (field.getType() == Coordinates.class){
                        Field[] fieldsCoordinates = field.getType().getDeclaredFields();
                        fieldsCoordinates[0].setAccessible(true); //x
                        fieldsCoordinates[1].setAccessible(true); //y
                        ans += String.format("\"%f\",\"%f\"", fieldsCoordinates[0].get(vehicle.getCoordinates()) ,
                                fieldsCoordinates[1].get(vehicle.getCoordinates()));
                    } else {
                        ans += field.get(vehicle).toString();
                    }
                    ans += ",";
                } catch (IllegalAccessException e){
                    logger.warning("Нет доступа к полю класса");
                }
            }
            ans += "\n";
        }
        return ans;
    }



}
