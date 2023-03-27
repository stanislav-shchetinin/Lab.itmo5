package commands;

import lombok.extern.java.Log;
import service.CollectionClass;
import service.command.Command;

import java.util.UUID;
@Log
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
            log.warning("Неверный тип id");
        }
    }

    @Override
    public void execute() {
        if (id == null){
            log.warning("Недостаточно параметров, чтобы выполнить комманду");
        } else {
            collectionClass.removeById(id);
        }
    }

    @Override
    public void clearFields() {
        id = null;
    }
}
