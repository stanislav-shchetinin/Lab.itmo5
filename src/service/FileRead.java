package service;

import base.Coordinates;
import base.Vehicle;
import base.VehicleType;
import com.opencsv.exceptions.CsvValidationException;
import exceptions.ReadDoubleException;
import exceptions.ReadFloatException;
import exceptions.ReadLongException;
import exceptions.ReadVehicleTypeException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

import static service.Validate.*;

public class FileRead {

    public static PriorityQueue<Vehicle> fileRead(Scanner in) throws CsvValidationException, IOException {
        PriorityQueue<Vehicle> priorityQueue = new PriorityQueue<>();
        int numberWord = 1;

        String str = Parse.parseFromCSVtoString(in);
        in = new Scanner(str);

        while (in.hasNext()){
            try {
                priorityQueue.add(new Vehicle(
                        readString(in, numberWord), //name
                        new Coordinates(readFloat(in, numberWord + 1), readFloat(in, numberWord + 2)),
                        readDouble(in, numberWord + 3), //enginePower
                        readLong(in, numberWord + 4), //capacity
                        readDouble(in, numberWord + 5), //distanceTravelled
                        readVehicleType(in, numberWord + 6),
                        numberWord //для каждого объекта это значение уникально, поэтому можно использовать как id
                ));
                numberWord += 7;
            } catch (NoSuchElementException e){
                System.out.println("Недостаточно значений, чтобы добавить последний объект");
            } catch (ReadVehicleTypeException | ReadFloatException | ReadLongException | ReadDoubleException e) {
                System.out.println(e.getMessage());
            }

        }
        return priorityQueue;

    }

}
