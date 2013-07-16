package uk.ac.cam.cl.dtg;

import java.io.BufferedWriter;

public class Logger {

    private BufferedWriter writer;
    protected volatile BattPerReading battPerReading;

    public Logger(BufferedWriter writer) {
        this.writer = writer;
    }

    public void writeOut() {
        // Do something clever ^_^
        System.out.println("Writing out!");
    }

    protected synchronized static void logDebug(String message){
        if(Constants.DEBUG)
            System.out.println(message);
    }

    public void setBattPerReading(BattPerReading reading) {
        this.battPerReading = reading   ;

        // We should use this as an indication that we should write out to disk
        this.writeOut();
    }

}
