package commands;

import lombok.extern.slf4j.Slf4j;
import service.command.Command;
@Slf4j
public class Exit implements Command {

    @Override
    public void execute() {
        log.info("Программа завершает выполнение: была вызвана команда exit");
        System.exit(0);
    }
}
