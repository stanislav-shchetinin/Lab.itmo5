package service;

import base.VehicleType;
import exceptions.ReadTypeException;

import java.util.Scanner;

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
}
