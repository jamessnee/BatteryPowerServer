package uk.ac.cam.cl.dtg;

public class Main {

    private static UDPReceiver udpReceiver;

    public static void main(String[] args) {
        startExperiment();
    }

    private static boolean startExperiment() {
        Logger.logDebug("Setting up...");
        udpReceiver = new UDPReceiver(Constants.PORT);
        Thread t = new Thread(udpReceiver);
        Logger.logDebug("Starting UDPReceiver");
        t.start();
        return true;
    }

}
