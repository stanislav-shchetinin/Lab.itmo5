package service;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.PriorityQueue;

public class CollectionClass{
    private Date date;
    private PriorityQueue collection = new PriorityQueue();
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

    public PriorityQueue getCollection() {
        return collection;
    }

    @Override
    public String toString() {
        return collection.toString();
    }

    public void clear(){
        collection.clear();
    }
    public void removeFirst(){
        collection.poll();
    }
}
