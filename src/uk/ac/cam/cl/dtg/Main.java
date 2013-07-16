package uk.ac.cam.cl.dtg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static UDPReceiver udpReceiver;
    private static PowerConsumptionReader powerConsumptionReader;

    public static void main(String[] args) {
        startExperiment();
    }

    private static boolean startExperiment() {
        Logger.logDebug("Setting up...");

        Logger logger = null;
        try {
            logger = new Logger(new BufferedWriter(new FileWriter(new File(Constants.LOG_FILENAME))));
        } catch(IOException e) {
            e.printStackTrace();
            Logger.logDebug("Bailing out!");
            System.exit(1);
        }

        /*
        // UDPReceiver setup
        udpReceiver = new UDPReceiver(Constants.BATT_PORT, logger);
        Thread t = new Thread(udpReceiver);
        Logger.logDebug("Starting UDPReceiver");
        t.start();
        */

        // PowerConsumptionReader setup
        powerConsumptionReader = new PowerConsumptionReader(logger);
        if(powerConsumptionReader.setupServerSocket()){
            Thread pT = new Thread(powerConsumptionReader);
            Logger.logDebug("Starting PowerConsumptionReader");
            pT.start();
        } else {
            Logger.logDebug("There was an error setting up the server socket. Bailing out...");
            System.exit(1);
        }

        return true;
    }

}
