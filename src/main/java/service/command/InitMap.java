package service.command;

import commands.*;
import service.CollectionClass;

import java.util.HashMap;

public class InitMap {
    public static HashMap<String, Command> noArgumentCommandHashMap(CollectionClass collectionClass){
        HashMap<String, Command> hashMap = new HashMap<>();
        hashMap.put("info", new Info(collectionClass));
        hashMap.put("help", new Help());
        hashMap.put("show", new Show(collectionClass));
        hashMap.put("clear", new Clear(collectionClass));
        hashMap.put("exit", new Exit());
        hashMap.put("remove_first", new RemoveFirst(collectionClass));
        hashMap.put("print_ascending", new PrintAscending(collectionClass));
        return hashMap;
    }
}
