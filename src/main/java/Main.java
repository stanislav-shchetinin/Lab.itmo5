import base.Vehicle;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import console.Console;
import service.CollectionClass;
import service.FileRead;
import service.Parse;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static console.Console.getFile;
import static console.Console.inputVehicle;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        /*File file = getFile(); //NAME_FILE

        CollectionClass collectionClass = new CollectionClass(FileRead.fileRead(file));

        Console.inputCommands(collectionClass);*/
        inputVehicle();

    }
}