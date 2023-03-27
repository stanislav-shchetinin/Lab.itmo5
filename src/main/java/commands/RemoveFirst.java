package commands;

import service.CollectionClass;
import service.command.Command;

public class RemoveFirst implements Command {
    private CollectionClass collectionClass;

    public RemoveFirst(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }

    @Override
    public String description() {
        return "remove_first : удалить первый элемент из коллекции";
    }

    @Override
    public String name() {
        return "remove_first";
    }
    @Override
    public void execute() {
        collectionClass.removeFirst();
    }
}
