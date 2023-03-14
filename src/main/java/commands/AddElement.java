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
    public void execute() {
        collectionClass.add(vehicle);
    }

    @Override
    public void getParametr() {
        vehicle = inputVehicle(collectionClass);
    }
}
