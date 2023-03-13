package commands;

import base.Vehicle;
import service.CollectionClass;
import service.Pair;
import service.command.Command;

import java.util.AbstractMap;
import java.util.UUID;

public class UpdateId implements Command {

    private Pair<Vehicle, UUID> pair;
    private CollectionClass collectionClass;

    public UpdateId(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void getParametr() {

    }

    @Override
    public void execute() {
        collectionClass.updateVyId(pair);
    }
}
