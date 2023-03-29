package commands;

import base.Vehicle;
import service.CollectionClass;
import service.command.Command;
import service.command.ElementArgument;

import java.util.Scanner;

import static console.Console.inputVehicle;

public class AddIfMax implements Command, ElementArgument {
    private Vehicle vehicle;
    private CollectionClass collectionClass;

    public AddIfMax(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public AddIfMax(){}
    @Override
    public void setElement(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String description() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    @Override
    public String name() {
        return "add_if_max";
    }

    @Override
    public void execute() {
        collectionClass.addIfMax(vehicle);
    }
}
