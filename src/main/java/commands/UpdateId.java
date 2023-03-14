package commands;

import base.Vehicle;
import service.CollectionClass;
import service.Pair;
import service.command.Command;

import java.util.AbstractMap;
import java.util.UUID;

import static console.Console.inputUUID;
import static console.Console.inputVehicle;

public class UpdateId implements Command {

    private Pair<Vehicle, UUID> pair;
    private CollectionClass collectionClass;

    public UpdateId(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void getParametr() {
        UUID uuid = inputUUID(collectionClass);
        Vehicle vehicle = inputVehicle(collectionClass);
        pair.setL(vehicle);
        pair.setR(uuid);
    }

    @Override
    public void execute() {
        collectionClass.updateById(pair);
    }
}
