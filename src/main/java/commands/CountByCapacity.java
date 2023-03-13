package commands;

import base.Vehicle;
import service.CollectionClass;
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
    public void getParametr() {}

    @Override
    public void execute() {
        collectionClass.countByCapacity(capacity);
    }
}
