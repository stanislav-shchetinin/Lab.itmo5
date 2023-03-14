package service;

import base.Coordinates;
import base.Vehicle;
import base.VehicleType;
import exceptions.ReadTypeException;
import exceptions.ReadValueException;

import java.lang.reflect.Field;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

public class Validate { //все методы видны в пределах классов service

    static String readString(Scanner in, int numberWord){
        return in.next();
    }
    static float readFloat(Scanner in, int numberWord) throws ReadTypeException {
        if (in.hasNextFloat()){
            return in.nextFloat(); //Должна быть запятая
        } else {
            throw new ReadTypeException(Float.class, numberWord, in.next());
        }
    }
    static Long readLong(Scanner in, int numberWord) throws ReadTypeException {
        if (in.hasNextLong()){
            return in.nextLong();
        } else {
            throw new ReadTypeException(Long.class, numberWord, in.next());
        }
    }
    static double readDouble(Scanner in, int numberWord) throws ReadTypeException {
        if (in.hasNextDouble()){
            return in.nextDouble();
        } else {
            throw new ReadTypeException(Double.class, numberWord, in.next());
        }
    }

    static VehicleType readVehicleType(Scanner in, int numberWord) throws ReadTypeException {
        String string = in.next();
        try {
            return VehicleType.valueOf(string); //Возвращает тип VehicleType с именем string
        } catch (IllegalArgumentException e){
            throw new ReadTypeException(VehicleType.class, numberWord, in.next());
        }

    }

    static Vehicle readVehicle(Scanner in, int numberWord) throws NoSuchElementException, ReadTypeException, ReadValueException {
        Vehicle vehicle = new Vehicle(
                readString(in, numberWord), //name
                new Coordinates(readFloat(in, numberWord + 1), readFloat(in, numberWord + 2)),
                readDouble(in, numberWord + 3), //enginePower
                readLong(in, numberWord + 4), //capacity
                readDouble(in, numberWord + 5), //distanceTravelled
                readVehicleType(in, numberWord + 6)
        );
        return vehicle;
    }

    public static Object thisType (String value, Field field){
        try {
            if (field.getType().equals(UUID.class)){
                return UUID.fromString(value); //6f369f5d-7b26-4d0c-9c35-cb6c980f5f24
            } else if (field.getType().equals(String.class)){
                return value;
            } else if (field.getType().equals(Coordinates.class)){
                String[] str = value.split(" ");
                return new Coordinates(Float.parseFloat(str[0]), Float.parseFloat(str[1])); //12.5 -8877.9
            } else if (field.getType().equals(ZonedDateTime.class)){
                return ZonedDateTime.parse(value); //2022-05-26T08:15:30+07:00[Asia/Ho_Chi_Minh]
            } else if (field.getType().equals(Double.class) || field.getType().equals(double.class)){
                return Double.parseDouble(value);
            } else if (field.getType().equals(Long.class)){
                return Long.parseLong(value);
            } else if (field.getType().equals(VehicleType.class)){
                return VehicleType.valueOf(value);
            }
        } catch (IllegalArgumentException e){
            LoggerForCommands.loggerWarning((String.format("Неверный тип %s", field.getName())));
        }
        return null;
    }

}
