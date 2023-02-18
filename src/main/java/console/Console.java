package console;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Console {
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
                    System.out.println("Успех!");
                    return file;
                } else {
                    System.out.println("Не существует файла по указанному пути");
                }

            } else {
                System.out.println("Не существует такой переменной окружения");
            }
        }
    }
}
