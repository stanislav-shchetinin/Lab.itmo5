package commands;

import console.Console;
import service.LoggerForCommands;
import service.command.Command;

import java.util.logging.Logger;

import static service.LoggerForCommands.loggerInfo;

public class Exit implements Command {

    @Override
    public void execute() {
        loggerInfo("Программа завершает выполнение: была вызвана команда exit");
        System.exit(0);
    }
}
