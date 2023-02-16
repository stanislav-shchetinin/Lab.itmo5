import com.opencsv.exceptions.CsvValidationException;
import service.FileRead;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {

    public static void main(String[] args){

        Scanner in = new Scanner("files/input.csv");
        PriorityQueue priorityQueue = FileRead.fileRead(in);

        System.out.println(priorityQueue.toString());

    }
}