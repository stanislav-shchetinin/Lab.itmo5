package commands;

import base.Vehicle;
import service.CollectionClass;
import service.Pair;

import java.util.AbstractMap;
import java.util.UUID;

public class UpdateId extends AbstractCommand{

    private Pair<Vehicle, UUID> pair;
    private CollectionClass collectionClass;

    public UpdateId(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public Object getParametr() {
        return pair;
    }

    @Override
    public void execute() {
        collectionClass.updateVyId(pair);
    }
}
