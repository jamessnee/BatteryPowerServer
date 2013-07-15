package uk.ac.cam.cl.dtg;

public class Main {

    private final int PORT = 7000;

    // iVars
    private UDPReceiver udpReceiver;

    public static void main(String[] args) {
	// write your code here
    }

    private boolean setupExperiment() {
        udpReceiver = new UDPReceiver(PORT);
        Thread t = new Thread(udpReceiver);
        t.start();

        return true;
    }


}
