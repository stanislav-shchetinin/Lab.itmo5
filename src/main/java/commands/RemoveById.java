package commands;

import service.CollectionClass;
import service.command.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;

public class RemoveById implements Command {
    private CollectionClass collectionClass;
    private UUID id;
    public RemoveById(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }

    @Override
    public void getParametr() {

    }

    @Override
    public void execute() {
        collectionClass.removeById(id);
    }
}
