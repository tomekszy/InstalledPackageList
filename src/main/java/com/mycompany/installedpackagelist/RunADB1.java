package com.mycompany.installedpackagelist;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunADB1 {

    public static void main(String args[]) {

        try {
            // Execute command
            String command = "cmd /c start cmd.exe";
            Process child = Runtime.getRuntime().exec(command);

            // Get output stream to write from it
//            OutputStream out = child.getOutputStream();
//
//            out.write("cd C:/ /r/n".getBytes());
//            out.flush();
//            out.write("dir /r/n".getBytes());
//            out.close();
        } catch (IOException e) {
        }

    }
}
