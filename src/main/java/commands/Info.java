package commands;

import service.CollectionClass;
import service.command.Command;

public class Info implements Command {

    private CollectionClass collectionClass;

    public Info(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }

    @Override
    public String description() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }


    @Override
    public String name() {
        return "info";
    }
    @Override
    public void execute() {
        System.out.println(String.format("Тип: %s\nДата инициализации: %s\nКоличество элементов: %d",
                collectionClass.getCollection().getClass().getSimpleName(),
                collectionClass.getTime(),
                collectionClass.getCollection().size())
        );
    }
}
