import console.Console;
import lombok.extern.slf4j.Slf4j;
import service.CollectionClass;

import java.io.*;

import static console.Console.*;
import static service.FileRead.fromFileVehicle;
import static service.Validate.checkFile;

@Slf4j
public class Main {
    public static void main(String[] args) {

        CollectionClass collectionClass = new CollectionClass();
        File file = getFile(); //NAME_FILE

        fromFileVehicle(collectionClass, file); //Считывание файла и запись его в collectionClass

        Console.inputCommands(collectionClass);

    }
}