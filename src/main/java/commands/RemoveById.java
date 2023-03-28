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
    public RemoveById(){}
    @Override
    public void setParametr(String uuidString) {
        try {
            this.id = UUID.fromString(uuidString);
        } catch (IllegalArgumentException e){
            log.warning("Неверный тип id");
        }
    }

    @Override
    public void clearFields() {
        id = null;
    }

    @Override
    public String description() {
        return "remove_by_id: удалить элемент из коллекции по его id";
    }

    @Override
    public String name() {
        return "remove_by_id";
    }

    @Override
    public void execute() {
        if (id == null){
            log.warning("Недостаточно параметров, чтобы выполнить команду");
        } else {
            collectionClass.removeById(id);
        }
    }

}
