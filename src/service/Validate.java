package service;

import base.VehicleType;
import exceptions.ReadDoubleException;
import exceptions.ReadFloatException;
import exceptions.ReadLongException;
import exceptions.ReadVehicleTypeException;

import java.util.Scanner;

public class Validate { //все методы видны в пределах классов service

    static String readString(Scanner in, int numberWord){
        return in.next();
    }
    static float readFloat(Scanner in, int numberWord) throws ReadFloatException {
        if (in.hasNextFloat()){
            return in.nextFloat(); //Должна быть запятая
        } else {
            throw new ReadFloatException(numberWord, in.next());
        }
    }
    static Long readLong(Scanner in, int numberWord) throws ReadLongException {
        if (in.hasNextLong()){
            return in.nextLong();
        } else {
            throw new ReadLongException(numberWord, in.next());
        }
    }
    static double readDouble(Scanner in, int numberWord) throws ReadDoubleException {
        if (in.hasNextDouble()){
            return in.nextDouble();
        } else {
            throw new ReadDoubleException(numberWord, in.next());
        }
    }

    static VehicleType readVehicleType(Scanner in, int numberWord) throws ReadVehicleTypeException {
        String string = in.next();
        try {
            return VehicleType.valueOf(string); //Возвращает тип VehicleType с именем string
        } catch (IllegalArgumentException e){
            throw new ReadVehicleTypeException(numberWord, in.next());
        }

    }
}
