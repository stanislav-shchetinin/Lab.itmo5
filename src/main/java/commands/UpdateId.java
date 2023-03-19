package commands;

import base.Vehicle;
import service.CollectionClass;
import service.LoggerForCommands;
import service.Pair;
import service.command.Command;

import java.util.AbstractMap;
import java.util.Scanner;
import java.util.UUID;

import static console.Console.inputVehicle;

public class UpdateId implements Command {

    private Pair<Vehicle, UUID> pair;
    private CollectionClass collectionClass;

    public UpdateId(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void setElement() {
        Vehicle vehicle = inputVehicle(collectionClass, new Scanner(System.in));
        pair.setL(vehicle);
    }

    @Override
    public void setParametr(String uuidString){
        try {
            this.pair.setR(UUID.fromString(uuidString));
        } catch (IllegalArgumentException e){
            LoggerForCommands.loggerWarning("Неверный тип id");
        }
    }

    @Override
    public void execute() {
        if (pair.getR() == null){
            LoggerForCommands.loggerWarning("Недостаточно параметров, чтобы выполнить комманду");
        } else {
            collectionClass.updateById(pair);
        }
    }

    @Override
    public void clearFields() {
        pair = null;
    }
}
