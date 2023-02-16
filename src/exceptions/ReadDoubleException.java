package exceptions;

public class ReadDoubleException extends Exception{
    public ReadDoubleException (int numberWord, String word){
        super(
                String.format("Не Double: (%d - номер слова, \"%s\" - слово)", numberWord, word)
        );
    }
}
