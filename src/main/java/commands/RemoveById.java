package commands;

import service.CollectionClass;
import service.command.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;

public class RemoveById extends AbstractCommand {
    private CollectionClass collectionClass;
    private UUID id;
    public RemoveById(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }

    @Override
    public void setParametr(Object parametr) {
        this.id = UUID.fromString((String) parametr);
    }

    @Override
    public String getParametr() {
        return String.valueOf(id);
    }

    @Override
    public void execute() {
        collectionClass.removeById(id);
    }
}
