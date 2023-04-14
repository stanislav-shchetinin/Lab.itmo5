package step;

import commands.AddElement;
import commands.ExecuteScript;
import commands.Info;
import io.cucumber.java.PendingException;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.checkerframework.checker.units.qual.C;
import org.junit.rules.ErrorCollector;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import service.CollectionClass;
import service.command.Command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static console.Console.getFile;
import static console.Console.inputVehicle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static service.FileRead.fromFileVehicle;
import static service.Parse.parseFromCSVtoString;

public class MyStepdefs {
    private File file;
    private CollectionClass collectionClass = new CollectionClass();

    @Дано("запуск метода получения файла с переменными окружения из {string}")
    public void startGetFile(String arg) {
        try {
            File fileWithTests = new File(arg);
            Scanner scanner = new Scanner(fileWithTests);
            while (scanner.hasNext()){
                this.file = getFile(scanner);
            }
        } catch (Throwable e){
            throw new PendingException("Ошибка при попытке получить файл из переменной окружения");
        }

    }
    @Тогда("получение коллекции из файла")
    public void setCollectionFromFile(){
        try {
            fromFileVehicle(collectionClass, new Scanner(parseFromCSVtoString(file)));
        } catch (Throwable e){
            throw new PendingException("Ошибка при попытке преобразовать файл в коллекцию");
        }

    }

    @Пусть("тестируется случай {string}")
    public void testCase(String nameFile){
        nameFile = "files/test_files/test_cases/" + nameFile;
        CollectionClass oldCollectionClass = new CollectionClass(collectionClass);
        Command command = ExecuteScript.getInstance(oldCollectionClass, file);
        command.setParametr(nameFile);
        command.execute();
    }

}