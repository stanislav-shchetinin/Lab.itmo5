package service;

import base.Coordinates;
import base.Vehicle;
import com.opencsv.exceptions.CsvValidationException;
import exceptions.ReadTypeException;
import exceptions.ReadValueException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static service.Validate.*;

public class FileRead {

    private static final Logger logger = Logger.getLogger(FileRead.class.getName());

    public static PriorityQueue<Vehicle> fileRead(File file){

        PriorityQueue<Vehicle> priorityQueue = new PriorityQueue<>();
        int numberWord = 1;

        String str = Parse.parseFromCSVtoString(file);
        Scanner in = new Scanner(str);

        while (in.hasNext()){
            Vehicle vehicle = null;
            try {
                vehicle = readVehicle(in, numberWord);
                numberWord += 7;
            } catch (NoSuchElementException e){
                logger.warning("Недостаточно значений, чтобы добавить последний объект");
            } catch (ReadTypeException | ReadValueException e) {
                logger.warning(e.getMessage());
                break;
            }
            if (vehicle != null){
                priorityQueue.add(vehicle);
            }
        }
        in.close();
        return priorityQueue;

    }

}
