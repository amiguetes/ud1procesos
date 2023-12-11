package org.example;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Piping piping = new Piping("ls -l".split(" "),"head -n5".split(" "));
        try {
            piping.start();
        } catch (IOException|InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
