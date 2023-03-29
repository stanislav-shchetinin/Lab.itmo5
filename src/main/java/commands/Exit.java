package commands;

import lombok.extern.java.Log;
import service.command.Command;
import service.command.NoArgument;

@Log
public class Exit implements Command, NoArgument {
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
