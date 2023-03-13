package commands;

import base.Vehicle;
import service.CollectionClass;

public class AddIfMax extends AbstractCommand{
    private Vehicle vehicle;
    private CollectionClass collectionClass;
    @Override
    public Object getParametr() {
        return vehicle;
    }

    @Override
    public void execute() {
        collectionClass.addIfMax(vehicle);
    }
}
