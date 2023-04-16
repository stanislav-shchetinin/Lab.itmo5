package service.command;

import base.Vehicle;

import java.io.OutputStream;

/**
 * Интерфейс Command для паттерна Command
 * */
public interface Command {
    void execute();
    default void setElement(Vehicle vehicle){}
    default void setParametr(String parametr){}
    default void clearFields(){}
    String description();

    String name();
}
