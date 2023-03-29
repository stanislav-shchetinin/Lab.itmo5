package commands;

import service.CollectionClass;
import service.command.Command;
import service.command.NoArgument;

public class RemoveFirst implements Command, NoArgument {
    private CollectionClass collectionClass;

    public RemoveFirst(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public RemoveFirst(){}
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
