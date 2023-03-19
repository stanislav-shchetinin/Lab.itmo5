package console;

import base.Vehicle;
import exceptions.ReadValueException;

import lombok.extern.slf4j.Slf4j;
import service.CollectionClass;
import service.NoInputTypes;
import service.command.Command;
import service.InitGlobalCollections;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.*;

import static service.InitGlobalCollections.setNoInputTypes;
import static service.Validate.*;

@Slf4j
public class Console {

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
                    return readCheckFile(new File(nameFile));
                } else {
                    System.out.print("Не существует такой переменной окружения");
                }
            } catch (FileNotFoundException e){
                log.error(e.getMessage());
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
                log.error("Не существует команды с указанным названием");
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
                    log.error(String.format("Неверный тип %s", field.getName()));
                } catch (IllegalAccessException e){
                    log.error("Запись в поле запрещена");
                } catch (ReadValueException e){
                    isCorrectValue = false;
                    log.error(e.getMessage());
                }
            }
        }
        return vehicle;
    }

}
