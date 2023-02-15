package base;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class  Vehicle implements Comparable<Vehicle>{
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double enginePower; //Значение поля должно быть больше 0
    private Long capacity; //Поле может быть null, Значение поля должно быть больше 0
    private Double distanceTravelled; //Поле может быть null, Значение поля должно быть больше 0
    private VehicleType type; //Поле не может быть null
    private final double EPS = 1e-8;

    public Vehicle(String name, Coordinates coordinates, double enginePower,
                   Long capacity, Double distanceTravelled, VehicleType type, Integer id){
        this.name = name;
        this.coordinates = coordinates;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.distanceTravelled = distanceTravelled;
        this.type = type;

        this.id = id; //Уникальное, т.к. хеш-код берется по ссылке
        this.creationDate = ZonedDateTime.now(); //текущая дата

        if (this.coordinates.getY() > -769){
            System.out.println("Координата Y должна быть не больще -769, установлено значение -769 в координату Y");
            this.coordinates.setY(-769);
        }

        if (this.enginePower <= 0){
            System.out.println("ebginePower должна быть больше 0, установлено значение 100 в enginePower");
            this.enginePower = 100;
        }

        if (this.capacity <= 0){
            System.out.println("capacity должна быть больше 0, установлено значение 4 в capacity");
            this.capacity = 4L;
        }

        if (this.distanceTravelled <= 0){
            System.out.println("distanceTravelled должна быть больше 0, установлено значение 0.1 в distanceTravelled");
            this.distanceTravelled = 0.1d;
        }

    }

    private int compDouble (double l, double r){
        if (Math.abs(l - r) < EPS){
            return 0;
        } else {
            if (l < r){
                return -1;
            }
            return 1;
        }
    }

    @Override
    public int compareTo(Vehicle o) {

        int arrRes[] = new int[8];
        arrRes[0] = this.type.compareTo(o.type);
        arrRes[1] = compDouble(this.enginePower, o.enginePower);
        arrRes[2] = this.capacity.compareTo(o.capacity);
        arrRes[3] = compDouble(this.distanceTravelled, o.distanceTravelled);
        arrRes[4] = this.coordinates.compareTo(o.coordinates);
        arrRes[5] = this.name.compareTo(o.name);
        arrRes[6] = this.creationDate.compareTo(o.creationDate);
        arrRes[7] = this.id.compareTo(o.id);

        for (int x : arrRes){
            if (x != 0){
                return x;
            }
        }
        return  0;

    }

    @Override
    public String toString() {
        return "******************\nId: " + id + "\nName: " + name + "\nCoordinates: " + coordinates.toString() +
                "\nCreation Date: " + creationDate + "\nEngine Power: " + enginePower +
                "\nCapacity: " + capacity + "\nDistance Travelled: " + distanceTravelled +
                "\nType: " + type + "\n******************";
    }
}