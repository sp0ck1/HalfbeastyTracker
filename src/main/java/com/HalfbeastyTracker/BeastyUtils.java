package com.HalfbeastyTracker;

import java.io.*;

public class BeastyUtils {

    // Get current path of HalfbeastyTracker.jar
    private static String currentDir = System.getProperty("user.dir");

    public static String getCurrentDir() {
        return currentDir;
    }

    public BeastyUtils() {
        System.out.println("Current directory is: " + currentDir);
    }


    public static String readLine(File fileName) {

        String line;

        if ( !fileName.exists()) { return null; }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            // Read single line from file
            line = br.readLine();

            br.close();

            return line;
        } catch (IOException f) {
            f.printStackTrace();
        }
        return null;
    }

    public static void writeLine(File file, String line) {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter, 8192);

            bufferedWriter.write(line);


            bufferedWriter.flush();
            bufferedWriter.close();

            outputStreamWriter.flush();
            outputStreamWriter.close();

            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (IOException ignored) {}

              }

}






