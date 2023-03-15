package commands;

import base.Vehicle;
import service.CollectionClass;
import service.LoggerForCommands;
import service.command.Command;

import java.util.PriorityQueue;
import java.util.UUID;

public class CountByCapacity implements Command {
    private CollectionClass collectionClass;
    private Long capacity;
    public CountByCapacity(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }

    @Override
    public void setParametr(String longString) {
        try {
            this.capacity = Long.parseLong(longString);
        } catch (IllegalArgumentException e){
            LoggerForCommands.loggerWarning("Неверный тип Long");
        }
    }

    @Override
    public void execute() {
        if (capacity == null){
            LoggerForCommands.loggerWarning("Недостаточно параметров, чтобы выполнить комманду");
        } else {
            collectionClass.countByCapacity(capacity);
        }
    }

    @Override
    public void clearFields() {
        capacity = null;
    }
}
