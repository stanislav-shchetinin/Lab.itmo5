package step;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;

import static console.Console.getFile;

public class MyStepdefs {
    private String string;

    @Дано("запуск метода получения файла")
    public void startGetFile(){
        getFile();
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