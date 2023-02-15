package base;

public class Coordinates implements Comparable<Coordinates>{
    private Float x; //Поле не может быть null
    private float y; //Значение поля должно быть больше -762

    public Coordinates(Float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public int compareTo(Coordinates o) {
        int res = this.x.compareTo(o.x);
        if (res == 0){
            return Float.valueOf(this.y).compareTo(Float.valueOf(o.y));
        }
        return res;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}