package commands;

import service.CollectionClass;
import service.LoggerForCommands;
import service.command.Command;

import java.io.File;

public class ExecuteScript implements Command {

    private CollectionClass collectionClass;
    private File file;

    public ExecuteScript(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void execute() {
        if (file == null){
            LoggerForCommands.loggerWarning("Недостаточно параметров, чтобы выполнить комманду");
        } else {

        }
    }

    @Override
    public void setParametr(String nameFile) {

    }

    @Override
    public void clearFields() {
        file = null;
    }
}
