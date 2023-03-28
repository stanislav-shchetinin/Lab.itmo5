package commands;

import service.CollectionClass;
import service.command.Command;

public class PrintUniqueEnginePower implements Command {
    private CollectionClass collectionClass;

    public PrintUniqueEnginePower(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public PrintUniqueEnginePower(){}
    @Override
    public String description() {
        return "print_unique_engine_power : вывести уникальные значения поля enginePower всех элементов в коллекции";
    }


    @Override
    public String name() {
        return "print_unique_engine_power";
    }
    @Override
    public void execute() {
        collectionClass.printUniqueEnginePower();
    }
}
