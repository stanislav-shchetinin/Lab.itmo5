package exceptions;

public class ReadFloatException extends Exception{
    public ReadFloatException (int numberWord, String word){
        super(
                String.format("Не Float: (%d - номер слова, \"%s\" - слово)", numberWord, word)
        );
    }
}
