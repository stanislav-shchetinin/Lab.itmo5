package service;

import base.Coordinates;
import base.Vehicle;
import exceptions.ReadValueException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import java.lang.reflect.Field;
import java.util.*;

import static service.Parse.parseFromCSVtoString;
import static service.Validate.*;
@Slf4j
public class FileRead {

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
                        log.error(String.format("Неверный тип %s", field.getName()));
                        break;
                    } catch (ReadValueException e) {
                        isCorrectVehicle = false;
                        log.error(e.getMessage());
                    } catch (IllegalAccessException e) {
                        log.warn("Нет доступа к полю");
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
