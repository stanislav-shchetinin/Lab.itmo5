package commands;

import lombok.extern.java.Log;
import service.CollectionClass;
import service.command.Command;

import java.io.File;
import java.io.FileNotFoundException;

import static service.Validate.readCheckFile;

@Log
public class ExecuteScript implements Command {

    private CollectionClass collectionClass;
    private File file;

    public ExecuteScript(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    public ExecuteScript(){}

    @Override
    public void setParametr(String nameFile) {
        try {
            this.file = readCheckFile(new File(nameFile));
        } catch (FileNotFoundException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public void clearFields() {
        file = null;
    }

    @Override
    public String description() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    @Override
    public String name() {
        return "execute_script";
    }
    @Override
    public void execute() {
        if (file == null){
            log.warning("Недостаточно параметров, чтобы выполнить комманду");
        } else {

        }
    }
}
