package console;

import base.Vehicle;
import exceptions.ReadValueException;
import service.CollectionClass;
import service.NoInputTypes;
import service.command.Command;
import service.InitGlobalCollections;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;

import static service.InitGlobalCollections.setNoInputTypes;
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
        HashMap<String, Command> mapCommand = InitGlobalCollections.mapCommand(collectionClass);
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

}
