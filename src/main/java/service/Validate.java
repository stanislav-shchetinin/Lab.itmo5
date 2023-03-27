package service;

import base.Coordinates;
import base.VehicleType;
import exceptions.ReadValueException;
import lombok.extern.java.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.UUID;
@Log
public class Validate {

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

    public static File checkFile(File file) throws FileNotFoundException {
        if (file.exists() && !file.isDirectory()){
            log.info("Файл успешно получен");
            return file;
        } else {
            throw new FileNotFoundException("Не существует файла по указанному пути");
        }
    }

    public static File readCheckFile(File file)throws FileNotFoundException {
        file = checkFile(file);
        if (!file.canRead()){
            throw new FileNotFoundException("Отказано в доступе");
        }
        return file;
    }
    public static File writeCheckFile(File file) throws FileNotFoundException {
        file = checkFile(file);
        if (!file.canWrite()){
            throw new FileNotFoundException("Отказано в доступе");
        }
        return file;
    }

}
