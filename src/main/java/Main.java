import base.Coordinates;
import base.Vehicle;
import base.VehicleType;
import commands.ExecuteScript;
import commands.Save;
import console.Console;

import lombok.extern.java.Log;
import service.CollectionClass;
import service.FileRead;
import service.command.Command;

import java.io.*;
import java.util.Scanner;

import static console.Console.*;
import static service.FileRead.fromFileVehicle;
import static service.Parse.parseFromCSVtoString;

/**
 * Это главный класс с методом main
*/
public class Main {

    /**
     * Метод main - стартовая точка проекта
     */
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("files/test_files/test_get_file");
            System.setIn(fileInputStream);

            CollectionClass collectionClass = new CollectionClass(); //Менеджер коллекции
            File file = getFile(new Scanner(System.in)); //NAME_FILE

            fileInputStream.close();

            fromFileVehicle(collectionClass, new Scanner(parseFromCSVtoString(file))); //Считывание файла и запись его в collectionClass

            String nameFile = "test3";

            String nameInputFile = "files/test_files/test_cases/" + nameFile + "/test";
            String nameOutputFile = "files/test_files/test_cases/" + nameFile + "/test_right";

            FileInputStream newFileInputStream = new FileInputStream(nameInputFile);
            PrintStream printStream = new PrintStream(nameOutputFile);
            System.setIn(newFileInputStream);
            System.setOut(printStream);

            //Console.inputCommands(collectionClass, file); //Ввод команд из консоли

            Command command = ExecuteScript.getInstance(collectionClass, file);
            command.setParametr(nameInputFile);
            command.execute();
            command.clearFields();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}   