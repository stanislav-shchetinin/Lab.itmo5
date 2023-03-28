import base.Coordinates;
import base.Vehicle;
import base.VehicleType;
import commands.Save;
import console.Console;

import lombok.extern.java.Log;
import service.CollectionClass;

import java.io.*;
import java.util.Scanner;

import static console.Console.*;
import static service.FileRead.fromFileVehicle;
import static service.Parse.parseFromCSVtoString;
public class Main {
    public static void main(String[] args) {

        CollectionClass collectionClass = new CollectionClass();
        File file = getFile(); //NAME_FILE

        fromFileVehicle(collectionClass, new Scanner(parseFromCSVtoString(file))); //Считывание файла и запись его в collectionClass

        Console.inputCommands(collectionClass, file);

    }
}   