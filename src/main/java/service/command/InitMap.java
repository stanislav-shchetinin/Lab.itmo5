package service.command;

import commands.Clear;
import commands.Help;
import commands.Info;
import commands.Show;
import service.CollectionClass;

import java.util.HashMap;

public class InitMap {
    public static HashMap<String, Command> noArgumentCommandHashMap(CollectionClass collectionClass){
        HashMap<String, Command> hashMap = new HashMap<>();
        hashMap.put("info", new Info(collectionClass));
        hashMap.put("help", new Help());
        hashMap.put("show", new Show(collectionClass));
        hashMap.put("clear", new Clear(collectionClass));
        return hashMap;
    }
}
