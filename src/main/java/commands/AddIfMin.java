package commands;

import base.Vehicle;
import service.CollectionClass;
import service.command.Command;

import java.util.Scanner;

import static console.Console.inputVehicle;

public class AddIfMin implements Command {
    private Vehicle vehicle;
    private CollectionClass collectionClass;

    public AddIfMin(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void setElement() {
        this.vehicle = inputVehicle(collectionClass, new Scanner(System.in));
    }

    @Override
    public void execute() {
        collectionClass.addIfMin(vehicle);
    }
}
