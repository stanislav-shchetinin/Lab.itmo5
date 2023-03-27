package commands;

import lombok.extern.java.Log;
import service.command.Command;
@Log
public class Exit implements Command {

    @Override
    public void execute() {
        log.info("Программа завершает выполнение: была вызвана команда exit");
        System.exit(0);
    }
}
