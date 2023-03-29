package service.command;

import base.Vehicle;

public interface Command {
    void execute();
    default void setElement(Vehicle vehicle){}
    default void setParametr(String parametr){}
    default void clearFields(){}
    String description();

    String name();
}
