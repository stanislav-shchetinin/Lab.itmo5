package service;

import base.Coordinates;
import base.Vehicle;
import com.opencsv.exceptions.CsvValidationException;
import exceptions.ReadTypeException;
import exceptions.ReadValueException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static service.InitGlobalCollections.setNoInputTypes;
import static service.Parse.parseFromCSVtoString;
import static service.Validate.*;

public class FileRead {

    private static final Logger logger = Logger.getLogger(FileRead.class.getName());

    public static void fromFileVehicle(CollectionClass collectionClass, File file) {
        Scanner in = new Scanner(parseFromCSVtoString(file));
        String data = parseFromCSVtoString(file);
        data = data.substring(0, data.length() - 1); //Добавляется null значение в конец, поэтому обрезаем его
        while (in.hasNext()){
            Vehicle vehicle = new Vehicle();
            boolean isCorrectVehicle = true;
            for (Field field : vehicle.getClass().getDeclaredFields()){
                field.setAccessible(true);
                if (in.hasNext()){
                    try {
                        String value = in.nextLine();
                        if (field.getType() == Coordinates.class && in.hasNext()){
                            value += " " + in.nextLine();
                            value.replaceAll(",", ".");
                        }
                        field.set(vehicle, thisType(value, field, collectionClass));
                    } catch (IllegalArgumentException e) {
                        isCorrectVehicle = false;
                        logger.warning(String.format("Неверный тип %s", field.getName()));
                        break;
                    } catch (ReadValueException e) {
                        isCorrectVehicle = false;
                        logger.warning(e.getMessage());
                    } catch (IllegalAccessException e) {
                        logger.fine("Нет доступа к полю");
                    }
                }
            }
            if (isCorrectVehicle){
                collectionClass.add(vehicle);
            } else {
                break;
            }
        }
    }

}
