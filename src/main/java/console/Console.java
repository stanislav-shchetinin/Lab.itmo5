package console;

import service.FileRead;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Console {

    private static final Logger logger = Logger.getLogger(Console.class.getName());
    public static File getFile(){

        String nameFile = "";
        Map<String, String> mapEnv = System.getenv();
        Scanner in = new Scanner(System.in);

        while (true){
            System.out.print("Введите имя переменной среды: ");
            String nameVar = in.nextLine();

            if (mapEnv.containsKey(nameVar)){
                nameFile = mapEnv.get(nameVar);
                File file = new File(nameFile);
                if (file.exists() && !file.isDirectory()){
                    logger.info("Файл успешно получен");
                    in.close();
                    return file;
                } else {
                    System.out.print("Не существует файла по указанному пути");
                }
            } else {
                System.out.print("Не существует такой переменной окружения");
            }
        }
    }
    public static void inputCommands(){
        Scanner in = new Scanner(System.in);

    }
}
