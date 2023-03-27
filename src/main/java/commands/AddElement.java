package commands;

import base.Vehicle;
import service.CollectionClass;
import service.command.Command;

import static console.Console.inputVehicle;

public class AddElement implements Command {

    private Vehicle vehicle;
    private CollectionClass collectionClass;

    public AddElement(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }

    @Override
    public void setElement() {
        this.vehicle = inputVehicle(collectionClass);
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
