package console;

import commands.AbstractCommand;
import service.CollectionClass;
import service.command.Command;
import service.command.InitMap;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class Console {

    private static final Logger logger = Logger.getLogger(Console.class.getName());
    public static File getFile(){

        String nameFile = "";
        Map<String, String> mapEnv = System.getenv();
        Scanner in = new Scanner(System.in);

        while (true){
            System.out.print("\nВведите имя переменной среды: ");
            String nameVar = in.nextLine();

            if (mapEnv.containsKey(nameVar)){
                nameFile = mapEnv.get(nameVar);
                File file = new File(nameFile);
                if (file.exists() && !file.isDirectory()){
                    logger.info("Файл успешно получен");
                    return file;
                } else {
                    System.out.print("Не существует файла по указанному пути");
                }
            } else {
                System.out.print("Не существует такой переменной окружения");
            }
        }
    }
    public static void inputCommands(CollectionClass collectionClass){
        HashMap<String, Integer> countArguments = InitMap.countArguments(collectionClass);
        HashMap<String, Command> noArgumentCommands = InitMap.noArgumentCommands(collectionClass);
        HashMap<String, AbstractCommand> withArgumentsCommands = InitMap.withArgumentsCommands(collectionClass);
        Scanner in = new Scanner(System.in);
        while (true){
            try {
                String nameCommand = in.next().trim();
                if (countArguments.get(nameCommand) == 1){
                    noArgumentCommands.get(nameCommand).execute();
                } else if (countArguments.get(nameCommand) == 2){
                    String arg = in.next();
                    AbstractCommand abstractCommand = withArgumentsCommands.get(nameCommand);
                    abstractCommand.setParametr(arg);
                    abstractCommand.execute();
                } else if (countArguments.get(nameCommand) == 3){

                } else {

                }
            } catch (NullPointerException e){
                logger.warning("Неверное имя команды");
            }

        }
    }
}
