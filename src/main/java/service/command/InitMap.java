package service.command;

import commands.*;
import service.CollectionClass;

import java.io.File;
import java.util.HashMap;

public class InitMap {

    public static HashMap<String, Command> mapCommand(CollectionClass collectionClass){
        HashMap<String, Command> map = new HashMap<>();
        map.put("help", new Help());
        map.put("info", new Info(collectionClass));
        map.put("show", new Show(collectionClass));
        map.put("add", new AddElement(collectionClass));
        map.put("update_id", new UpdateId(collectionClass));
        map.put("remove_by_id", new RemoveById(collectionClass));
        map.put("clear", new Clear(collectionClass));
        map.put("save", new Save(collectionClass));
        map.put("execute_script", new ExecuteScript(collectionClass));
        map.put("exit", new Exit());
        map.put("remove_first", new RemoveFirst(collectionClass));
        map.put("add_if_max", new AddIfMax(collectionClass));
        map.put("add_if_min", new AddIfMin(collectionClass));
        map.put("count_by_capacity", new CountByCapacity(collectionClass));
        map.put("print_ascending", new PrintAscending(collectionClass));
        map.put("print_unique_engine_power", new PrintUniqueEnginePower(collectionClass));
        return map;
    }

}
