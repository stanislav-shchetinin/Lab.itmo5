package commands;

import lombok.extern.java.Log;
import service.CollectionClass;
import service.command.Command;

import java.io.File;
import java.io.FileNotFoundException;

import static service.Validate.checkFile;
@Log
public class ExecuteScript implements Command {

    private CollectionClass collectionClass;
    private File file;

    public ExecuteScript(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void execute() {
        if (file == null){
            log.warning("Недостаточно параметров, чтобы выполнить комманду");
        } else {

        }
    }

    @Override
    public void setParametr(String nameFile) {
        try {
            this.file = checkFile(new File(nameFile));
        } catch (FileNotFoundException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public void clearFields() {
        file = null;
    }
}
