package console;

import base.Coordinates;
import base.Vehicle;
import exceptions.ReadTypeException;
import exceptions.ReadValueException;
import service.CollectionClass;
import service.FileRead;
import service.Validate;
import service.command.Command;
import service.command.InitMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;

import static service.Parse.parseFromCSVtoString;
import static service.Validate.*;

public class Console {

    private static final Logger logger = Logger.getLogger(Console.class.getName());
    public static File getFile(){

        String nameFile = "";
        Map<String, String> mapEnv = System.getenv();
        Scanner in = new Scanner(System.in);

        while (true){
            System.out.print("\nВведите имя переменной среды: ");
            try {
                String nameVar = in.nextLine().trim();
                if (mapEnv.containsKey(nameVar)){
                    nameFile = mapEnv.get(nameVar);
                    return checkFile(new File(nameFile));
                } else {
                    System.out.print("Не существует такой переменной окружения");
                }
            } catch (FileNotFoundException e){
                logger.warning(e.getMessage());
            }

        }
    }

    public static void inputCommands(CollectionClass collectionClass) {
        HashMap<String, Command> mapCommand = InitMap.mapCommand(collectionClass);
        Scanner in = new Scanner(System.in);
        while (true){
            String[] arrayString = in.nextLine().trim().split(" ");
            if (arrayString.length == 0){
                continue;
            }
            String nameCommand = arrayString[0];
            Command command = mapCommand.get(nameCommand);
            if (command == null){
                logger.warning("Не существует команды с указанным названием");
                continue;
            }
            if (arrayString.length != 1){
                command.setParametr(arrayString[1]);
            }
            command.setElement();
            command.execute();
            command.clearFields();
        }

    }

    public static Vehicle inputVehicle(CollectionClass collectionClass, Scanner in) {
        Vehicle vehicle = new Vehicle();
        for (Field field : vehicle.getClass().getDeclaredFields()){
            field.setAccessible(true);
            boolean isCorrectValue = false;
            while (!isCorrectValue){
                try {
                    isCorrectValue = true;
                    System.out.println(String.format("\nВведите %s: ", field.getName()));
                    String value = in.nextLine();
                    field.set(vehicle, thisType(value, field, collectionClass));
                } catch (IllegalArgumentException e) {
                    isCorrectValue = false;
                    logger.warning(String.format("Неверный тип %s", field.getName()));
                } catch (IllegalAccessException e){
                    logger.fine("Запись в поле запрещена");
                } catch (ReadValueException e){
                    isCorrectValue = false;
                    logger.warning(e.getMessage());
                }
            }
        }
        return vehicle;
    }

    public static void fromFileVehicle(CollectionClass collectionClass, File file) {
        Scanner in = new Scanner(parseFromCSVtoString(file));
        String data = parseFromCSVtoString(file);
        data = data.substring(0, data.length() - 1); //Добавляется null значение в конец, поэтому обрезаем его
        System.out.println(data);
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

    public static UUID inputUUID (CollectionClass collectionClass){
        Scanner in = new Scanner(System.in);
        while (true){
            String value = in.next();
            try {
                UUID uuid = uuidFromString(value, collectionClass);
                return uuid;
            } catch (ReadValueException e) {
                logger.warning(e.getMessage());
            } catch (IllegalArgumentException e) {
                logger.warning("Неверный тип id");
            }
        }
    }

}
