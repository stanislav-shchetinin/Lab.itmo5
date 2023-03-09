package commands;

import base.Vehicle;
import service.CollectionClass;
import service.FileRead;
import service.LoggerForCommands;
import service.command.Command;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

public class Save implements Command {

    private CollectionClass collectionClass;
    private final String head = "name,coordinates.x, coordinates.y,engine_power,capacity,distance_travelled,type";
    private File file;

    public Save(CollectionClass collectionClass, File file){
        this.collectionClass = collectionClass;
        this.file = file;
    }
    @Override
    public void execute() {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

        } catch (FileNotFoundException e){
            LoggerForCommands.loggerWarning(String.format("Файл %s не найден", file.toString()));
        }
    }
}
