import console.Console;
import service.CollectionClass;

import java.io.*;
import java.util.logging.Logger;

import static console.Console.*;
import static service.FileRead.fromFileVehicle;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        CollectionClass collectionClass = new CollectionClass();
        File file = getFile(); //NAME_FILE

        fromFileVehicle(collectionClass, file); //Считывание файла и запись его в collectionClass

        Console.inputCommands(collectionClass);

    }
}