package service;

import base.Coordinates;
import base.Vehicle;
import com.opencsv.exceptions.CsvValidationException;
import exceptions.ReadTypeException;
import exceptions.ReadValueException;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

import static service.Validate.*;

public class FileRead {

    public static PriorityQueue<Vehicle> fileRead(Scanner in) {
        PriorityQueue<Vehicle> priorityQueue = new PriorityQueue<>();
        int numberWord = 1;

        String str = Parse.parseFromCSVtoString(in);
        in = new Scanner(str);

        while (in.hasNext()){
            Vehicle vehicle = null;
            try {
                vehicle = new Vehicle(
                        readString(in, numberWord), //name
                        new Coordinates(readFloat(in, numberWord + 1), readFloat(in, numberWord + 2)),
                        readDouble(in, numberWord + 3), //enginePower
                        readLong(in, numberWord + 4), //capacity
                        readDouble(in, numberWord + 5), //distanceTravelled
                        readVehicleType(in, numberWord + 6)
                );
                numberWord += 7;
            } catch (NoSuchElementException e){
                System.out.println("Недостаточно значений, чтобы добавить последний объект");
            } catch (ReadTypeException | ReadValueException e) {
                System.out.println(e.getMessage());
                break;
            }
            if (vehicle != null){
                priorityQueue.add(vehicle);
            }
        }
        return priorityQueue;

    }

}
