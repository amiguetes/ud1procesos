package org.example;

import java.io.*;

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
    public void start() {
        try {
            // Inicia los procesos según los ProcessBuilders.
            Process p1 = pbProcess1.start();
            Process p2 = pbProcess2.start();

            // FileWriter para escribir en el archivo de salida.
            FileWriter fileWriter = new FileWriter(outputFile);

            // BufferedReader para leer la salida del primer proceso.
            InputStreamReader p1isr = new InputStreamReader(p1.getInputStream());
            BufferedReader p1br = new BufferedReader(p1isr);

            // BufferedWriter para escribir en la entrada del segundo proceso.
            OutputStreamWriter p2osw = new OutputStreamWriter(p2.getOutputStream());
            BufferedWriter p2bw = new BufferedWriter(p2osw);

            // Lee la salida del primer proceso y la escribe en la entrada del segundo.
            String line = "";
            while ((line = p1br.readLine()) != null){
                p2bw.write(line + "\n");
            }

            // Cierra el BufferedWriter para indicar el fin de la entrada del segundo proceso.
            p2bw.close();

            // Espera a que el primer proceso termine.
            p1.waitFor();

            // Espera a que el segundo proceso termine.
            exitValueOfSecondProcess = p2.waitFor();

            // BufferedReader para leer la salida del segundo proceso.
            InputStreamReader p2isr = new InputStreamReader(p2.getInputStream());
            BufferedReader p2br = new BufferedReader(p2isr);

            // Escribe la salida del segundo proceso en el archivo de salida.
            while ((line = p2br.readLine()) != null){
                fileWriter.write(line + "\n");
            }

            // Cierra el FileWriter.
            fileWriter.close();

        } catch (IOException|InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}
