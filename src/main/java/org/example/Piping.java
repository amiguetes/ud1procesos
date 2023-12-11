package org.example;

import java.io.*;
import java.util.Scanner;

public class Piping {

    // Dos ProcessBuilders para gestionar los comandos del proceso.
    ProcessBuilder pbProcess1;
    ProcessBuilder pbProcess2;

    // Nombre del archivo de salida donde se guardará la salida del segundo proceso.
    String outputFile;

    int exitValueOfSecondProcess;

    /**
     * Constructor para crear un objeto Piping con dos comandos y un archivo de salida.
     * @param command1 El primer comando a ejecutar como array de String.
     * @param command2 El segundo comando a ejecutar como array de String.
     * @param outputFile El nombre del archivo donde se guardará la salida del segundo comando.
     */
    public Piping(String[] command1, String[] command2, String outputFile){
        pbProcess1 = new ProcessBuilder(command1);
        pbProcess2 = new ProcessBuilder(command2);
        pbProcess2.redirectOutput(new File(outputFile));
        this.outputFile = outputFile;
    }

    /**
     * Constructor sobrecargado para crear un objeto Piping con dos comandos.
     * Por defecto, el archivo de salida será "output.txt".
     * @param command1 El primer comando a ejecutar.
     * @param command2 El segundo comando a ejecutar.
     */
    public Piping(String[] command1, String[] command2) {
        this(command1, command2, "output.txt");
    }

    /**
     * Método para obtener el nombre del archivo de salida.
     * @return El nombre del archivo de salida.
     */
    String getOutputFileName(){
        return outputFile;
    }

    /**
     * Método para obtener el valor de salida del segundo proceso.
     * @return El valor de salida del segundo proceso.
     */
    @Override
    public String toString() {
        return "El valor de salida de la ejecución es: <" + exitValueOfSecondProcess + ">";
    }

    /**
     * Método para iniciar el proceso de piping entre los dos comandos.
     */
    public void start() throws IOException, InterruptedException{

        // Inicia los procesos según los ProcessBuilders.
        Process p1 = pbProcess1.start();
        Process p2 = pbProcess2.start();

        try (


             // Scanner para leer la salida del primer proceso.
             Scanner sc = new Scanner(p1.getInputStream());

             // PrintWriter para escribir en la entrada del segundo proceso.
             PrintWriter p2w = new PrintWriter(p2.getOutputStream(),true);
        ){

            // Lee la salida del primer proceso y la escribe en la entrada del segundo.

            while (sc.hasNextLine()){
                p2w.println(sc.nextLine());
            }

            // Cierra el BufferedWriter para indicar el fin de la entrada del segundo proceso.


            // Espera a que el primer proceso termine.
            p1.waitFor();

            // Espera a que el segundo proceso termine.
            exitValueOfSecondProcess = p2.waitFor();


        } catch (InterruptedException e) {
            throw e;
        }

    }
}
