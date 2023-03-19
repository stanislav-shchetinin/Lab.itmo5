import console.Console;
import service.CollectionClass;

import java.io.*;

import static console.Console.*;
import static service.FileRead.fromFileVehicle;

public class Main {
    public static void main(String[] args) {

        CollectionClass collectionClass = new CollectionClass();
        File file = getFile(); //NAME_FILE

        fromFileVehicle(collectionClass, file); //Считывание файла и запись его в collectionClass

        Console.inputCommands(collectionClass);

    }
}