package commands;

import lombok.extern.java.Log;
import service.command.Command;
@Log
public class Exit implements Command {
    @Override
    public String description() {
        return "exit : завершить программу (без сохранения в файл)";
    }

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public void execute() {
        log.info("Программа завершает выполнение: была вызвана команда exit");
        System.exit(0);
    }
}
