package service.command;

import commands.*;
import service.CollectionClass;

import java.util.HashMap;

public class InitMap {
    public static HashMap<String, Integer> countArguments(CollectionClass collectionClass){
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("info", 1);
        hashMap.put("help", 1);
        hashMap.put("show", 1);
        hashMap.put("clear", 1);
        hashMap.put("exit", 1);
        hashMap.put("remove_first", 1);
        hashMap.put("print_ascending", 1);
        hashMap.put("print_unique_engine_power", 1);
        hashMap.put("save", 1);
        hashMap.put("remove_by_id", 2);
        hashMap.put("execute_script", 2);
        hashMap.put("count_by_capacity", 2);
        hashMap.put("add", 3);
        hashMap.put("add_if_max", 3);
        hashMap.put("add_if_min", 3);
        hashMap.put("update", 4);
        return hashMap;
    }

    public static HashMap<String, Command> noArgumentCommands (CollectionClass collectionClass){
        HashMap<String, Command> commandHashMap = new HashMap<>();
        commandHashMap.put("info", new Info(collectionClass));
        commandHashMap.put("help", new Help());
        commandHashMap.put("show", new Show(collectionClass));
        commandHashMap.put("clear", new Show(collectionClass));
        commandHashMap.put("exit", new Exit());
        commandHashMap.put("remove_first", new RemoveFirst(collectionClass));
        commandHashMap.put("print_ascending", new PrintAscending(collectionClass));
        commandHashMap.put("print_unique_engine_power", new PrintUniqueEnginePower(collectionClass));
        //commandHashMap.put("save", new Save());
        return commandHashMap;
    }
    public static HashMap<String, AbstractCommand> withArgumentsCommands (CollectionClass collectionClass){
        HashMap<String, AbstractCommand> commandHashMap = new HashMap<>();
        commandHashMap.put("remove_by_id", new RemoveById(collectionClass));
        commandHashMap.put("count_by_capacity", new CountByCapacity(collectionClass));
        return commandHashMap;
    }

    /*public static HashMap<String, Command> oneArgumentCommandHashMap(CollectionClass collectionClass){
        HashMap<String, Command> hashMap = new HashMap<>();
        hashMap.put("remove_by_id", new RemoveById(collectionClass,));
        return hashMap;
    }*/
}
