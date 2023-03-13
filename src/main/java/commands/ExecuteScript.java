package commands;

import service.CollectionClass;
import service.command.Command;

public class ExecuteScript implements Command {

    private CollectionClass collectionClass;

    public ExecuteScript(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void execute() {

    }

    @Override
    public void getParametr() {

    }
}
