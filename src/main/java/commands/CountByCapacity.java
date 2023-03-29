package commands;

import lombok.extern.java.Log;
import service.CollectionClass;
import service.command.Command;
import service.command.OneArgument;

@Log
public class CountByCapacity implements Command, OneArgument {
    private CollectionClass collectionClass;
    private Long capacity;
    public CountByCapacity(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public CountByCapacity(){}

    @Override
    public void setParametr(String longString) {
        try {
            this.capacity = Long.parseLong(longString);
        } catch (IllegalArgumentException e){
            log.warning("Неверный тип Long");
        }
    }

    @Override
    public String description() {
        return "count_by_capacity : очистить коллекцию";
    }

    @Override
    public String name() {
        return "count_by_capacity";
    }

    @Override
    public void execute() {
        if (capacity == null){
            log.warning("Недостаточно параметров, чтобы выполнить комманду");
        } else {
            collectionClass.countByCapacity(capacity);
        }
    }

    @Override
    public void clearFields() {
        capacity = null;
    }
}
