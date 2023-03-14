package commands;

import base.Vehicle;
import service.CollectionClass;
import service.command.Command;

import static console.Console.inputVehicle;

public class AddIfMax implements Command {
    private Vehicle vehicle;
    private CollectionClass collectionClass;

    public AddIfMax(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void getParametr() {
        vehicle = inputVehicle(collectionClass);
    }

    @Override
    public void execute() {
        collectionClass.addIfMax(vehicle);
    }
}
