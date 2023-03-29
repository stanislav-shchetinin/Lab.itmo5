package commands;

import base.Vehicle;
import lombok.extern.java.Log;
import service.CollectionClass;
import service.Pair;
import service.command.Command;
import service.command.ElementArgument;
import service.command.OneArgument;

import java.util.Scanner;
import java.util.UUID;

import static console.Console.inputVehicle;
@Log
public class UpdateId implements Command, ElementArgument, OneArgument {

    private Pair<Vehicle, UUID> pair;
    private CollectionClass collectionClass;

    public UpdateId(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public UpdateId(){}
    @Override
    public void setElement(Vehicle vehicle) {
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
    public void clearFields() {
        pair = null;
    }

    @Override
    public String description() {
        return "update id:обновить элемент с заданным id";
    }

    @Override
    public String name() {
        return "update";
    }

    @Override
    public void execute() {
        if (pair.getR() == null){
            log.warning("Недостаточно параметров, чтобы выполнить комманду");
        } else {
            collectionClass.updateById(pair);
        }
    }
}
