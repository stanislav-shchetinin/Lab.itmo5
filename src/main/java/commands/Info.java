package commands;

import service.CollectionClass;
import service.command.Command;

public class Info implements Command {

    private CollectionClass collectionClass;

    public Info(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void execute() {
        System.out.println(String.format("Тип: %s\nДата инициализации: %s\nКоличество элементов: %d",
                collectionClass.collection.getClass().getSimpleName(),
                collectionClass.getTime(),
                collectionClass.collection.size())
        );
    }
}
