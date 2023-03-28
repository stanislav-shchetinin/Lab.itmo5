package service;

import base.Vehicle;
import lombok.extern.java.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static service.Validate.readCheckFile;
@Log
public class CollectionClass{
    private Date date;
    private PriorityQueue<Vehicle> collection = new PriorityQueue();
    private HashSet<UUID> uuidHashSet = new HashSet<>();
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

    public HashSet<UUID> getUuidHashSet() {
        return uuidHashSet;
    }

    @Override
    public String toString() {
        String cs = collection.toString();
        return cs.substring(1, cs.length() - 1);
    }

    public void clear(){
        collection.clear();
        uuidHashSet.clear();
    }
    public void removeFirst(){
        UUID id = collection.poll().getId();
        uuidHashSet.remove(id);
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
        PriorityQueue<Vehicle> collectionNew = new PriorityQueue<>();
        while (!collection.isEmpty()){
            Vehicle vehicle = (Vehicle) collection.poll();
            if (!vehicle.getId().equals(id)){
                collectionNew.add(vehicle);
            }
        }
        uuidHashSet.remove(id);
        this.collection = new PriorityQueue<>(collectionNew);
    }

    public void countByCapacity(Long capacity){
        Integer count = 0;
        PriorityQueue<Vehicle> collectionCopy = new PriorityQueue<>(collection);
        while (!collectionCopy.isEmpty()){
            if (collectionCopy.poll().getCapacity().equals(capacity)){
                count += 1;
            }
        }
        System.out.println(count);
    }

    public void add(Vehicle vehicle){
        collection.add(vehicle);
        uuidHashSet.add(vehicle.getId());
    }

    public void updateById(Pair<Vehicle, UUID> pair){
        Vehicle vehicleNew = pair.getL();
        UUID id = pair.getR();

        PriorityQueue<Vehicle> collectionNew = new PriorityQueue<>();
        while (!collection.isEmpty()){
            Vehicle vehicleOld = collection.poll();
            if (vehicleOld.getId().equals(id)){
                uuidHashSet.remove(vehicleOld.getId());
                uuidHashSet.add(vehicleNew.getId());
                collectionNew.add(vehicleNew);
            } else {
                collectionNew.add(vehicleOld);
            }
        }

        collection = collectionNew;

    }

    public void addIfMax(Vehicle vehicle){
        if (vehicle.compareTo(collection.peek()) > 0){
            collection.add(vehicle);
            uuidHashSet.add(vehicle.getId());
        }
    }

    public void addIfMin(Vehicle vehicle){
        if (vehicle.compareTo(collection.peek()) < 0){
            collection.add(vehicle);
            uuidHashSet.add(vehicle.getId());
        }
    }

    public void executeScript(File file){
        try {
            Scanner in = new Scanner(file);
            while (in.hasNext()){

            }
        } catch (FileNotFoundException e) {
            log.warning(e.getMessage());
        }

    }

}
