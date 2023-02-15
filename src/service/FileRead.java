package service;

import base.Coordinates;
import base.Vehicle;
import base.VehicleType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FileRead {

    private static PriorityQueue<Vehicle> priorityQueue = new PriorityQueue<>();

    private static String readString(Scanner in, int numberWord){
        return in.next();
    }
    private static float readFloat(Scanner in, int numberWord){
        if (in.hasNextFloat()){
            return in.nextFloat(); //Должна быть запятая
        } else {
            System.out.println("Слово #" + numberWord + ": \"" + in.next() + "\" не типа float, оно было пропущено" +
                    "\nДобавлено значение по умолчанию для типа float (0)");
            return 0f;
        }
    }
    private static Long readLong(Scanner in, int numberWord){
        if (in.hasNextLong()){
            return in.nextLong();
        } else {
            System.out.println("Слово #" + numberWord + ": \"" + in.next() + "\" не типа Long, оно было пропущено" +
                    "\nДобавлено значение по умолчанию для типа Long (4)");
            return 4L;
        }
    }
    private static double readDouble(Scanner in, int numberWord){
        if (in.hasNextDouble()){
            return in.nextDouble();
        } else {
            System.out.println("Слово #" + numberWord + ": \"" + in.next() + "\" не типа double, оно было пропущено" +
                    "\nДобавлено значение по умолчанию для типа double (100)");
            return 100d;
        }
    }

    private static VehicleType readVehicleType(Scanner in, int numberWord){
        String string = in.next();
        try {
            return VehicleType.valueOf(string); //Возвращает тип VehicleType с именем string
        } catch (IllegalArgumentException e){
            System.out.println("Нет такого типа VehicleType, как: \"" + string + "\", слово #" + numberWord + " было пропущено" +
                    "\nДобавлено значение по умолчанию для типа VehicleType (CAR)");
            return VehicleType.CAR;
        }

    }
    public static PriorityQueue<Vehicle> fileRead(String nameFile){
        int numberWord = 1;
        try {
            Scanner in = new Scanner(Path.of(nameFile), StandardCharsets.UTF_8);

            while (in.hasNext()){
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
            }
            return priorityQueue;
        } catch (IOException e){
            System.out.println("Не существует файла с именем: " + e.getMessage());
        }
        priorityQueue = null;
        return priorityQueue;
    }

}
