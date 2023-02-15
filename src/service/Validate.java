package service;

import base.VehicleType;

import java.util.Scanner;

public class Validate { //все методы видны в пределах классов service

    static String readString(Scanner in, int numberWord){
        return in.next();
    }
    static float readFloat(Scanner in, int numberWord){
        if (in.hasNextFloat()){
            return in.nextFloat(); //Должна быть запятая
        } else {
            System.out.println("Слово #" + numberWord + ": \"" + in.next() + "\" не типа float, оно было пропущено" +
                    "\nДобавлено значение по умолчанию для типа float (0)");
            return 0f;
        }
    }
    static Long readLong(Scanner in, int numberWord){
        if (in.hasNextLong()){
            return in.nextLong();
        } else {
            System.out.println("Слово #" + numberWord + ": \"" + in.next() + "\" не типа Long, оно было пропущено" +
                    "\nДобавлено значение по умолчанию для типа Long (4)");
            return 4L;
        }
    }
    static double readDouble(Scanner in, int numberWord){
        if (in.hasNextDouble()){
            return in.nextDouble();
        } else {
            System.out.println("Слово #" + numberWord + ": \"" + in.next() + "\" не типа double, оно было пропущено" +
                    "\nДобавлено значение по умолчанию для типа double (100)");
            return 100d;
        }
    }

    static VehicleType readVehicleType(Scanner in, int numberWord){
        String string = in.next();
        try {
            return VehicleType.valueOf(string); //Возвращает тип VehicleType с именем string
        } catch (IllegalArgumentException e){
            System.out.println("Нет такого типа VehicleType, как: \"" + string + "\", слово #" + numberWord + " было пропущено" +
                    "\nДобавлено значение по умолчанию для типа VehicleType (CAR)");
            return VehicleType.CAR;
        }

    }
}
