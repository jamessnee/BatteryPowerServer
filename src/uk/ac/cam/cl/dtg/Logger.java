package uk.ac.cam.cl.dtg;

import java.io.BufferedWriter;

public class Logger {

    private BufferedWriter writer;

    public Logger(BufferedWriter writer) {
        this.writer = writer;
    }

    public void write(short batteryPercentage, long powerConsumed) {
        // Do something clever ^_^
    }

    protected static void logDebug(String message){
        if(Constants.DEBUG)
            System.out.println(message);
    }

}
