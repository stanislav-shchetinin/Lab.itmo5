package service;

import base.Vehicle;
import console.Console;

import java.time.ZonedDateTime;
import java.util.*;

public class CollectionClass{
    private Date date;
    private PriorityQueue<Vehicle> collection = new PriorityQueue();
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
        String cs = collection.toString();
        return cs.substring(1, cs.length() - 1);
    }

    public void clear(){
        collection.clear();
    }
    public void removeFirst(){
        collection.poll();
    }
    public void printAscending(){
        PriorityQueue<Vehicle> collectionCopy = new PriorityQueue<>(collection);
        while (!collectionCopy.isEmpty()){
            System.out.println(collectionCopy.poll().toString());
        }
    }
    public void printUniqueEnginePower(){
        PriorityQueue<Vehicle> collectionCopy = new PriorityQueue<>(collection);
        HashSet hashSet = new HashSet<Double>();
        while (!collectionCopy.isEmpty()){
            hashSet.add(collectionCopy.poll().getEnginePower());
        }
        System.out.print(hashSet.toString());
    }
    public void removeById (UUID id){
        PriorityQueue<Vehicle> collectionNew = new PriorityQueue<Vehicle>();
        while (!collection.isEmpty()){
            Vehicle vehicle = (Vehicle) collection.poll();
            if (vehicle.getId() != id){
                collectionNew.add(vehicle);
            }
        }
        collection = collectionNew;
    }

    public void countByCapacity(Long capacity){
        Integer count = 0;
        PriorityQueue <Vehicle> collectionCopy = collection;
        while (!collectionCopy.isEmpty()){
            if (collectionCopy.poll().getCapacity() == capacity){
                count += 1;
            }
        }
        System.out.println(count);
    }

}
