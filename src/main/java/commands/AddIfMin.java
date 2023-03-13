package commands;

import base.Vehicle;
import service.CollectionClass;
import service.command.Command;

public class AddIfMin implements Command {
    private Vehicle vehicle;
    private CollectionClass collectionClass;

    public AddIfMin(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void getParametr() {

    }

    @Override
    public void execute() {
        collectionClass.addIfMin(vehicle);
    }
}
