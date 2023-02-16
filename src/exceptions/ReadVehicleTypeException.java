package exceptions;

public class ReadVehicleTypeException extends Exception{
    public ReadVehicleTypeException (int numberWord, String word){
        super(
                String.format("Не VehicleType: (%d - номер слова, \"%s\" - слово)", numberWord, word)
        );
    }
}