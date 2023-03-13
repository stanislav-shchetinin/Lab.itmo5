package commands;

import base.Vehicle;
import service.CollectionClass;

public class AddElement extends AbstractCommand {

    private Vehicle vehicle;
    private CollectionClass collectionClass;
    @Override
    public Object getParametr() {
        return vehicle;
    }

    @Override
    public void execute() {
        collectionClass.add(vehicle);
    }
}
