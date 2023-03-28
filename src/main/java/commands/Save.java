package commands;

import lombok.extern.java.Log;
import service.CollectionClass;
import service.Parse;
import service.command.Command;

import java.io.*;

import static service.Validate.writeCheckFile;

@Log
public class Save implements Command {

    private CollectionClass collectionClass;
    private final String HEAD = "id,name,coordinates.x,coordinates.y,date,engine_power,capacity,distance_travelled,type\n";
    private File file = null;

    public Save(CollectionClass collectionClass, File file){
        this.collectionClass = collectionClass;
        this.file = file;
    }
    public Save(){}

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
            String queueString = Parse.queueToString(collectionClass.getCollection());
            byte[] buffer = queueString.getBytes();
            bos.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            log.warning(String.format("Проблемы с файлом %s", file.toString()));
        }
    }
}
