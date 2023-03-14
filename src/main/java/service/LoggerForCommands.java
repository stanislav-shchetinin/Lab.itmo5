package service;

import commands.Exit;

import java.util.logging.Logger;

public class LoggerForCommands {
    private static final Logger logger = Logger.getLogger(LoggerForCommands.class.getName());
    public static void loggerInfo(String messege){
        logger.info(messege);
    }
    public static void loggerWarning(String messege){
        logger.warning(messege);
    }
}
