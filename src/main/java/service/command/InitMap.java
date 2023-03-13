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
        map.put("add", new AddElement());
        return map;
    }

}
