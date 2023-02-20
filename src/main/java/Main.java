import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import service.FileRead;
import service.Parse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import static console.Console.getFile;

public class Main {

    public static void main(String[] args){

        File file = getFile(); //NAME_FILE

        PriorityQueue priorityQueue = FileRead.fileRead(file);
        System.out.println(priorityQueue.toString());

    }
}