package commands;

import base.Vehicle;
import lombok.extern.java.Log;
import service.CollectionClass;
import service.InitGlobalCollections;
import service.Parse;
import service.command.Command;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.PriorityQueue;

import static service.Validate.writeCheckFile;

@Log
public class Save implements Command {

    private CollectionClass collectionClass;
    private final String HEAD = getHead(Vehicle.class);
    private File file = null;
    public Save(CollectionClass collectionClass, File file){
        this.collectionClass = collectionClass;
        this.file = file;
    }
    public Save(){}

    private String getHead(Class clazz){
        HashSet<Class> primitiveTypes = InitGlobalCollections.primitiveTypes();
        String ans = "";
        for (Field field : clazz.getDeclaredFields()){
            ans += String.format("%s", getStringFields(field, primitiveTypes));
        }
        return String.format("%s\n", ans.substring(0, ans.length() - 1)); //убрал последнюю запятую
    }
    private String getStringFields(Field field, HashSet<Class> primitiveTypes){
        String ans = "";

        if (primitiveTypes.contains(field.getType())){
            return String.format("%s,", field.getName());
        }
        for (Field simpleField : field.getType().getDeclaredFields()){
            ans += String.format("%s", getStringFields(simpleField, primitiveTypes));
        }

        return ans;
    }

    @Override
    public String description() {
        return "save : сохранить коллекцию в файл";
    }

    @Override
    public String name() {
        return "save";
    }
    @Override
    public void execute() {
        try (FileOutputStream fos = new FileOutputStream(writeCheckFile(file));
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] bufferHead = HEAD.getBytes();
            bos.write(bufferHead, 0, bufferHead.length);
            String queueString = Parse.queueToString(new PriorityQueue<>(collectionClass.getCollection()));
            byte[] buffer = queueString.getBytes();
            bos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            log.warning(String.format("Проблемы с файлом %s", file.toString()));
        }
    }
}
