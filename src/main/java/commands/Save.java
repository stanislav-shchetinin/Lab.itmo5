package commands;

import lombok.extern.slf4j.Slf4j;
import service.CollectionClass;
import service.Parse;
import service.command.Command;

import java.io.*;

import static service.Validate.writeCheckFile;

@Slf4j
public class Save implements Command {

    private CollectionClass collectionClass;
    private final String HEAD = "id,name,coordinates.x,coordinates.y,date,engine_power,capacity,distance_travelled,type\n";
    private File file = new File("files/output.csv");

    public Save(CollectionClass collectionClass){
        this.collectionClass = collectionClass;
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
            log.error(String.format("Проблемы с файлом %s", file.toString()));
        }
    }
}
