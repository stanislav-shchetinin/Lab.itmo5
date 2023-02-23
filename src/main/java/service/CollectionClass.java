package service;

import java.time.ZonedDateTime;
import java.util.Date;

public class CollectionClass<T>{
    private Date date;
    public T collection;
    public CollectionClass (T collection){
        this.collection = collection;
        date = new Date();
    }

    public Date getTime() {
        return date;
    }

    @Override
    public String toString() {
        return collection.toString();
    }
}
