package commands;

import service.CollectionClass;
import service.command.Command;

public abstract class AbstractCommand implements Command {
    private CollectionClass collectionClass;
    private Object parametr;

    public void setParametr(Object parametr) {
        this.parametr = parametr;
    }

    public abstract Object getParametr();
}
