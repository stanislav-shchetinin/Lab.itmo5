package step;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static console.Console.getFile;

public class MyStepdefs {
    private String string;
    private File file;

    @Дано("запуск метода получения файла")
    public void startGetFile() throws FileNotFoundException {
        this.file = getFile(new FileInputStream("files/test_files/test_get_file"));
    }
    @Дано("на вход подается переменная окружения {string}")
    public void getEnvironmentVariable(String arg1){
        System.out.println(arg1);
        string = arg1;
    }
    @Когда("выдать сравнить строки с {string}")
    public void equalsStrings(String arg1){
        System.out.println(string.equals(arg1));
    }
}