package commands;

import base.Coordinates;
import base.Vehicle;
import exceptions.ReadValueException;
import lombok.extern.java.Log;
import service.CollectionClass;
import service.NoInputTypes;
import service.command.Command;
import service.command.ElementArgument;
import service.command.OneArgument;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import static service.InitGlobalCollections.mapCommand;
import static service.InitGlobalCollections.setNoInputTypes;
import static service.Validate.readCheckFile;
import static service.Validate.thisType;

@Log
public class ExecuteScript implements Command, OneArgument {

    private CollectionClass collectionClass;
    private File file;
    private File fileSave;
    private HashSet<String> nameFiles = new HashSet<>();
    private static ExecuteScript Instance = null;
    private HashMap<String, Command> mapCommand;
    HashSet<String> setNoInputTypes = setNoInputTypes(NoInputTypes.values());
    public static ExecuteScript getInstance(CollectionClass collectionClass, File fileSave) {
        if (Instance == null){
            Instance = new ExecuteScript(collectionClass, fileSave);
        }
        return Instance;
    }
    private ExecuteScript(CollectionClass collectionClass, File fileSave){
        this.collectionClass = collectionClass;
        this.fileSave = fileSave;
    }
    public ExecuteScript(){}

    @Override
    public void setParametr(String nameFile) {
        try {
            this.file = readCheckFile(new File(nameFile));
        } catch (FileNotFoundException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public void clearFields() {
        file = null;
    }

    @Override
    public String description() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    @Override
    public String name() {
        return "execute_script";
    }
    @Override
    public void execute() {
        if (file == null){
            log.warning("Недостаточно параметров, чтобы выполнить комманду");
            return;
        }
        if (nameFiles.contains(file.getName())){
            log.warning("Вызов файлов зациклился");
            return;
        }
        nameFiles.add(file.getName());
        mapCommand = mapCommand(collectionClass, fileSave);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String[] line = scanner.nextLine().trim().split(" ");
                Command command = mapCommand.get(line[0]);
                if (command == null){
                    log.warning("Не существует команды с указанным названием");
                    continue;
                }
                commandSetParametr(command, line[1]);
                commandSetElement(command, line);
                command.execute();
                command.clearFields();
            }
        } catch (FileNotFoundException e) {
            log.warning("Файл не найден");
        } catch (ReadValueException e) {
            log.warning(e.getMessage());
        } catch (IllegalAccessException e) {
            log.warning(e.getMessage());
        }
    }

    private void commandSetParametr(Command command, String parametr){
        if (command instanceof OneArgument){
            command.setParametr(parametr);
        }
    }

    private void commandSetElement(Command command, String[] line) throws ReadValueException, IllegalAccessException {
        if (command instanceof ElementArgument){
            //+1 т.к. line[0] - имя команды, +1 т.к. Coordinates имеет два поля
            if (line.length == Vehicle.class.getDeclaredFields().length + 1 - setNoInputTypes(NoInputTypes.values()).size() + 1){
                command.setElement(vehicleFromArray(line));
            } else {
                command.setElement(vehicleFromArray(Arrays.copyOfRange(line, 1, line.length - 1))); //у update_id еще один аргумент
            }
        }
    }

    private Vehicle vehicleFromArray (String[] line) throws ReadValueException, IllegalAccessException {
        if (line.length - 1 < Vehicle.class.getDeclaredFields().length - setNoInputTypes.size()){ //-1 т.к. line[0] - имя команды
            throw new ReadValueException("Недостаточно аргументов для записи Vehicle");
        }
        Vehicle vehicle = new Vehicle();
        int num = 1; //line[0] - название команды
        for (Field field : Vehicle.class.getDeclaredFields()){
            field.setAccessible(true);
            if (setNoInputTypes.contains(field.getType().getSimpleName())){ //Если ID или DATA
                continue;
            }
            line[num] = line[num].trim();
            String str = line[num];
            if (field.getType().equals(Coordinates.class)){
                ++num;
                str += " " + line[num].trim();
            }
            field.set(vehicle, thisType(str, field, collectionClass));
            ++num;
        }
        return  vehicle;
    }

}
