package step;

import commands.Info;
import io.cucumber.java.PendingException;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import service.CollectionClass;
import service.command.Command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static console.Console.getFile;
import static service.FileRead.fromFileVehicle;
import static service.Parse.parseFromCSVtoString;

public class MyStepdefs {
    private File file;
    private CollectionClass collectionClass = new CollectionClass();

    @Дано("запуск метода получения файла с переменными окружения из {string}")
    public void startGetFile(String arg) throws IOException {
        File fileWithTests = new File(arg);
        Scanner scanner = new Scanner(fileWithTests);
        try {
            while (scanner.hasNext()){
                this.file = getFile(scanner);
            }
        } catch (Throwable e){
            throw new PendingException();
        }

    }
    @Тогда("получение коллекции из файла")
    public void setCollectionFromFile(){
        fromFileVehicle(collectionClass, new Scanner(parseFromCSVtoString(file)));
    }

    @Пусть("вводится команда info")
    public void testInfo(){
        Command command = new Info(this.collectionClass);
        command.execute();
    }

}