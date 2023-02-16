package exceptions;

public class ReadLongException extends Exception{
    public ReadLongException (int numberWord, String word){
        super(
                String.format("Не Long: (%d - номер слова, \"%s\" - слово)", numberWord, word)
        );
    }
}
