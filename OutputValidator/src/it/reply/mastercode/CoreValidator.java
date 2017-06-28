package it.reply.mastercode;

import it.reply.mastercode.utilities.IOUtils;

import java.util.List;

/**
 * Created by Jacopo Moscioni on 27/06/17.
 */
public class CoreValidator {
    public static void main(String [] args){
       int customersNumber;
       int replyNumber;
       int problemsNumber;
       List<String> header = IOUtils.readFileHeader("/Users/TheAnonymous/hashcode/replycalls/input.txt");
       if (header != null && header.size() == 3) {
           customersNumber = Integer.parseInt(header.get(0));
           replyNumber =  Integer.parseInt(header.get(1));
           problemsNumber = Integer.parseInt(header.get(2));
       }
       else {
           System.out.println("ERROR OCCURRED. INPUT FILE CORRUPTED ON FIRST LINE");
           return;
       }
      // List<>



        System.out.println(file.toArray().toString());
    }
}
