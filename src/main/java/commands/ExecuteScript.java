package commands;

import lombok.extern.slf4j.Slf4j;
import service.CollectionClass;
import service.command.Command;

import java.io.File;
import java.io.FileNotFoundException;

import static service.Validate.checkFile;
@Slf4j
public class ExecuteScript implements Command {

    private CollectionClass collectionClass;
    private File file;

    public ExecuteScript(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
    }
    @Override
    public void execute() {
        if (file == null){
            log.error("Недостаточно параметров, чтобы выполнить комманду");
        } else {

        }
    }

    @Override
    public void setParametr(String nameFile) {
        try {
            this.file = checkFile(new File(nameFile));
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void clearFields() {
        file = null;
    }
}
