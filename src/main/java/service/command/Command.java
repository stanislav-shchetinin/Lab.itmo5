package service.command;

public interface Command {
    void execute();
    default void setElement(){}
    default void setParametr(String parametr){}
    default void clearFields(){}
}
