package commands;

import base.Vehicle;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import service.CollectionClass;
import service.Pair;
import service.command.Command;

import java.util.UUID;

import static console.Console.inputVehicle;
@Log
public class UpdateId implements Command {

    private Pair<Vehicle, UUID> pair;
    private CollectionClass collectionClass;

    public UpdateId(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void setElement() {
        Vehicle vehicle = inputVehicle(collectionClass);
        pair.setL(vehicle);
    }

    @Override
    public void setParametr(String uuidString){
        try {
            this.pair.setR(UUID.fromString(uuidString));
        } catch (IllegalArgumentException e){
            log.warning("Неверный тип id");
        }
    }

    @Override
    public void execute() {
        if (pair.getR() == null){
            log.warning("Недостаточно параметров, чтобы выполнить комманду");
        } else {
            collectionClass.updateById(pair);
        }
    }

    @Override
    public void clearFields() {
        pair = null;
    }
}
