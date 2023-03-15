package commands;

import service.CollectionClass;
import service.LoggerForCommands;
import service.command.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;

import static console.Console.inputUUID;

public class RemoveById implements Command {
    private CollectionClass collectionClass;
    private UUID id;
    public RemoveById(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }

    @Override
    public void setParametr(String uuidString) {
        try {
            this.id = UUID.fromString(uuidString);
        } catch (IllegalArgumentException e){
            LoggerForCommands.loggerWarning("Неверный тип id");
        }
    }

    @Override
    public void execute() {
        if (id == null){
            LoggerForCommands.loggerWarning("Недостаточно параметров, чтобы выполнить комманду");
        } else {
            collectionClass.removeById(id);
        }
    }

    @Override
    public void clearFields() {
        id = null;
    }
}
