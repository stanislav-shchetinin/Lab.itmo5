package service;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.PriorityQueue;

public class CollectionClass{
    private Date date;
    public PriorityQueue collection = new PriorityQueue();
    public CollectionClass (PriorityQueue collection){
        this.collection = collection;
        date = new Date();
    }
    public CollectionClass(){
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
