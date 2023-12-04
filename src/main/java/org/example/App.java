package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Piping piping = new Piping("ls -l".split(" "),"head -n5".split(" "));
        piping.start();
    }
}
