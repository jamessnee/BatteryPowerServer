package uk.ac.cam.cl.dtg;

public class Main {

    private final int PORT = 7000;

    // iVars
    private UDPReceiver udpReceiver;

    public static void main(String[] args) {
	// write your code here
    }

    private Boolean setupExperiment() {
        udpReceiver = new UDPReceiver(PORT);

        return true;
    }


}
