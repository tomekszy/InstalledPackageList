package com.mycompany.installedpackagelist;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunADB {

    static String output() {

        String s = null;
        

        try {
            
	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
//            Process p = Runtime.getRuntime().exec("adb shell 'pm list packages -f'");
            
            Process p = Runtime.getRuntime().exec("dir");
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                FileWriter fw = new FileWriter("in.dat");
                fw.write(s);
                fw.close();
            }
            
            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        
        return s;
    }
}