package console;

import base.Vehicle;
import exceptions.ReadValueException;

import lombok.extern.java.Log;
import service.CollectionClass;
import service.NoInputTypes;
import service.command.Command;
import service.InitGlobalCollections;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.*;

import static service.InitGlobalCollections.setNoInputTypes;
import static service.Parse.formatInput;
import static service.Validate.*;

@Log
public class Console {

    public static File getFile(){

        String nameFile = "";
        Map<String, String> mapEnv = System.getenv();
        Scanner in = new Scanner(System.in);

        while (true){
            System.out.print("\nВведите имя переменной среды:\n");
            try {
                String nameVar = in.nextLine().trim();
                if (mapEnv.containsKey(nameVar)){
                    nameFile = mapEnv.get(nameVar);
                    File file = readCheckFile(new File(nameFile));
                    log.info("Файл успешно получен");
                    return file;
                } else {
                    System.out.print("Не существует такой переменной окружения");
                }
            } catch (FileNotFoundException e){
                log.warning(e.getMessage());
            } catch (NoSuchElementException e){
                log.warning("Не введены значения");
                System.exit(1); //1 - означает ошибку no line из-за которой произошло завершение
            }

        }
    }

    public static void inputCommands(CollectionClass collectionClass, File file) {
        HashMap<String, Command> mapCommand = InitGlobalCollections.mapCommand(collectionClass, file);
        Scanner in = new Scanner(System.in);
        while (true){
            try {
                String[] arrayString = in.nextLine().trim().split(" ");
                if (arrayString.length == 0){
                    continue;
                }
                String nameCommand = arrayString[0];
                Command command = mapCommand.get(nameCommand);
                if (command == null){
                    log.warning("Не существует команды с указанным названием");
                    continue;
                }
                if (arrayString.length != 1){
                    command.setParametr(arrayString[1]);
                }
                command.setElement();
                command.execute();
                command.clearFields();
            } catch (NoSuchElementException e){
                log.warning("Не введены значения");
                break;
            }
        }

    }

    public static Vehicle inputVehicle(CollectionClass collectionClass) {
        Scanner in = new Scanner(System.in);
        Vehicle vehicle = new Vehicle();
        HashSet<String> setNoInputTypes = setNoInputTypes(NoInputTypes.values());
        for (Field field : vehicle.getClass().getDeclaredFields()){
            if (setNoInputTypes.contains(field.getType().getSimpleName())){ //Если ID или DATA
                continue;
            }
            field.setAccessible(true);
            boolean isCorrectValue = false;
            while (!isCorrectValue){
                try {
                    isCorrectValue = true;
                    System.out.println(String.format("\n%sВведите %s: ", formatInput(field.getType()), field.getName()));
                    String value = in.nextLine();
                    field.set(vehicle, thisType(value, field, collectionClass));
                } catch (IllegalArgumentException e) {
                    isCorrectValue = false;
                    log.warning(String.format("Неверный тип %s", field.getName()));
                } catch (IllegalAccessException e){
                    log.warning("Запись в поле запрещена");
                } catch (ReadValueException e){
                    isCorrectValue = false;
                    log.warning(e.getMessage());
                }
            }
        }
        return vehicle;
    }

}
