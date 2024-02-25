package br.com.sgde.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LogGenerator {
    
    public static void generateLog(String message) {
        Logger logger = Logger.getLogger("sgde");
        FileHandler fileHandler;
        
        try{
            fileHandler = new FileHandler("src/main/resources/sgde.txt");
            logger.addHandler(fileHandler);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.info(message);   
            
        }catch(SecurityException | IOException e){
            logger.log(Level.INFO, "Exception: {0}", e.getMessage());
        }
    }
}
