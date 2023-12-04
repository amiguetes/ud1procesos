package org.example;

import java.io.*;
import java.util.Scanner;

public class Piping {

    ProcessBuilder pbProcess1;
    ProcessBuilder pbProcess2;
    String outputFile;

    public Piping(String[] command1, String[] command2, String outputFile){

        pbProcess1 = new ProcessBuilder(command1);
        pbProcess2 = new ProcessBuilder(command2);
        this.outputFile = outputFile;

    }
    public Piping(String[] command1, String[] command2) {

        this(command1,command2,"output.txt");

    }

    String getOutputFileName(){
        return outputFile;
    }

    public void start() {

        try {

            Process p1 = pbProcess1.start();
            Process p2 = pbProcess2.start();


            FileWriter fileWriter = new FileWriter(outputFile);


            InputStreamReader p1isr = new InputStreamReader(p1.getInputStream());
            BufferedReader p1br = new BufferedReader(p1isr);

            OutputStreamWriter p2osw = new OutputStreamWriter(p2.getOutputStream());
            BufferedWriter p2bw = new BufferedWriter(p2osw);

            String line = "";
            while ((line = p1br.readLine()) != null){
                p2bw.write(line+"\n");

            }

            p2bw.close();

            p2.waitFor();

            InputStreamReader p2isr = new InputStreamReader(p2.getInputStream());
            BufferedReader p2br = new BufferedReader(p2isr);

            while ((line = p2br.readLine()) != null){
                fileWriter.write(line+"\n");
            }

            fileWriter.close();


        } catch (IOException e) {
            System.err.println(e);
        } catch (InterruptedException e) {
            System.err.println(e);
        }


    }



}
