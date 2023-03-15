package service;

import base.Coordinates;
import base.Vehicle;
import base.VehicleType;
import exceptions.ReadTypeException;
import exceptions.ReadValueException;

import java.io.File;
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

    public static UUID uuidFromString(String value, CollectionClass collectionClass) throws IllegalArgumentException, ReadValueException {
        UUID uuid = UUID.fromString(value);
        if (collectionClass.getUuidHashSet().contains(uuid)){
            throw new ReadValueException("Передаваемый id не уникален");
        }
        return uuid;
    }
    public static Coordinates coordinatesFromString (String value) throws IllegalArgumentException, ReadValueException {
        String[] str = value.split(" ");
        Coordinates coordinates = new Coordinates(Float.parseFloat(str[0]), Float.parseFloat(str[1]));
        if (coordinates.getY() > -762){
            throw new ReadValueException("Координата Y не может быть больше -762");
        }
        return coordinates;
    }

    private static Double doubleFromString (String value, Field field) throws IllegalArgumentException, ReadValueException {
        Double ans = Double.parseDouble(value);
        if (field.getName().equals("enginePower") || field.getName().equals("distanceTravelled")){
            if (ans <= 0){
                throw new ReadValueException(String.format("Значение поля %s должно быть больше 0", field.getName()));
            }
        }
        return ans;
    }

    private static Long longFromString (String value, Field field) throws IllegalArgumentException, ReadValueException {
        Long ans = Long.parseLong(value);
        if (field.getName().equals("capacity")){
            if (ans <= 0){
                throw new ReadValueException(String.format("Значение поля %s должно быть больше 0", field.getName()));
            }
        }
        return ans;
    }

    public static Object thisType (String value, Field field, CollectionClass collectionClass) throws IllegalArgumentException, ReadValueException {
        if (field.getType().equals(UUID.class)){
            return uuidFromString(value, collectionClass); //6f369f5d-7b26-4d0c-9c35-cb6c980f5f24
        } else if (field.getType().equals(String.class)){
            return value;
        } else if (field.getType().equals(Coordinates.class)){
            return coordinatesFromString(value); //12.5 -8877.9
        } else if (field.getType().equals(ZonedDateTime.class)){
            return ZonedDateTime.parse(value); //2022-05-26T08:15:30+07:00[Asia/Ho_Chi_Minh]
        } else if (field.getType().equals(Double.class) || field.getType().equals(double.class)){
            return doubleFromString(value, field);
        } else if (field.getType().equals(Long.class)){
            return longFromString(value, field);
        } else if (field.getType().equals(VehicleType.class)){
            return VehicleType.valueOf(value);
        }
        return null;
    }

    public static File checkFile(File file) throws ReadValueException {
        if (file.exists() && !file.isDirectory()){
            LoggerForCommands.loggerInfo("Файл успешно получен");
            return file;
        } else {
            throw new ReadValueException("Не существует файла по указанному пути");
        }
    }

}
