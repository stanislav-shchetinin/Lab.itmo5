package commands;

import lombok.extern.slf4j.Slf4j;
import service.CollectionClass;
import service.command.Command;
@Slf4j
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
            log.error("Неверный тип Long");
        }
    }

    @Override
    public void execute() {
        if (capacity == null){
            log.error("Недостаточно параметров, чтобы выполнить комманду");
        } else {
            collectionClass.countByCapacity(capacity);
        }
    }

    @Override
    public void clearFields() {
        capacity = null;
    }
}
