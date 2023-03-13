package service.command;

public interface Command {
    void execute();
    default void getParametr(){

    }
}
