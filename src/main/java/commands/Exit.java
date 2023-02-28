package commands;

import console.Console;
import service.command.Command;

import java.util.logging.Logger;

public class Exit implements Command {
    private static final Logger logger = Logger.getLogger(Exit.class.getName());
    @Override
    public void execute() {
        logger.info("Программа завершает выполнение: была вызвана команда exit");
        System.exit(0);
    }
}
