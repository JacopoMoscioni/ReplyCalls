package it.reply.mastercode.components.misc;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class IOUtils {
    public static List<String> readFile(final String filename) {
        List<String> lines = new ArrayList<>();
        try(Stream<String> stream = Files.lines(Paths.get(filename))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            IOUtils.systemOut("error reading file "+filename);
            lines = null;
        }
        return lines;
    }
    public static void createLogFile(){
        try {
            //ripulisco/creo il file di log
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            writer.println(new Date().toString()+"\r\n");
            writer.close();
        }catch (IOException e) {
            System.out.println("impossibile creare il file di log, di seguito l'errore riscontrato:"+e.getMessage());
        }
    }
    public static void systemOut(String text){
        try{
            Files.write(Paths.get("log.txt"), (text+"\r\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("impossibile scrivere il file di log, di seguito l'errore riscontrato");
            System.out.println(text);
        }
    }
}
