package commands;

import base.Vehicle;
import service.CollectionClass;

public class AddIfMin extends AbstractCommand{
    private Vehicle vehicle;
    private CollectionClass collectionClass;

    public AddIfMin(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public Object getParametr() {
        return vehicle;
    }

    @Override
    public void execute() {
        collectionClass.addIfMin(vehicle);
    }
}
