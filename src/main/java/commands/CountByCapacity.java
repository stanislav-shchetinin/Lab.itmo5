package commands;

import base.Vehicle;
import service.CollectionClass;

import java.util.PriorityQueue;
import java.util.UUID;

public class CountByCapacity extends AbstractCommand{
    private CollectionClass collectionClass;
    private Long capacity;
    public CountByCapacity(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }

    @Override
    public void setParametr(Object parametr) {
        this.capacity = Long.parseLong((String) parametr);
    }

    @Override
    public String getParametr() {
        return String.valueOf(capacity);
    }

    @Override
    public void execute() {
        collectionClass.countByCapacity(capacity);
    }
}
