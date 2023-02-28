package service;

import base.Vehicle;

import java.time.ZonedDateTime;
import java.util.*;

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
    public void printAscending(){
        PriorityQueue collectionCopy = collection;
        while (!collectionCopy.isEmpty()){
            System.out.println(collectionCopy.poll().toString());
        }
    }
    public void printUniqueEnginePower(){
        PriorityQueue<Vehicle> collectionCopy = collection;
        HashSet hashSet = new HashSet<Double>();
        while (!collectionCopy.isEmpty()){
            hashSet.add(collectionCopy.poll().getEnginePower());
        }
        System.out.print(hashSet.toString());
    }
}
