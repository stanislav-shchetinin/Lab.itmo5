package commands;

import base.Vehicle;
import service.CollectionClass;
import service.command.Command;
import service.command.ElementArgument;

import java.util.Scanner;

import static console.Console.inputVehicle;

public class AddElement implements Command, ElementArgument {

    private Vehicle vehicle;
    private CollectionClass collectionClass;

    public AddElement(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public AddElement(){
    }

    @Override
    public void setElement(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String description() {
        return "add {element} : добавить новый элемент в коллекцию";
    }


    @Override
    public String name() {
        return "add";
    }

    @Override
    public void execute() {
        collectionClass.add(vehicle);
    }
}
