package commands;

import base.Vehicle;
import service.CollectionClass;
import service.command.Command;

import static console.Console.inputVehicle;

public class AddIfMin implements Command {
    private Vehicle vehicle;
    private CollectionClass collectionClass;

    public AddIfMin(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public AddIfMin(){}
    @Override
    public void setElement() {
        this.vehicle = inputVehicle(collectionClass);
    }

    @Override
    public String description() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    @Override
    public String name() {
        return "add_if_min";
    }
    @Override
    public void execute() {
        collectionClass.addIfMin(vehicle);
    }
}
