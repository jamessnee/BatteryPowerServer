package uk.ac.cam.cl.dtg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceiver implements Runnable {

    private int port;
    private Logger logger;

    public UDPReceiver(int port, Logger logger) {
        this.port = port;
        this.logger = logger;
    }

    @Override
    public void run() {
        Logger.logDebug("UDPReceiver Started");
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        Logger.logDebug("Socket opened");
        DatagramPacket packet = new DatagramPacket(new byte[2], 2);

        Logger.logDebug("Waiting for packets...");
        while(true) {
            try {
                socket.receive(packet);
                parsePacket(packet);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parsePacket(DatagramPacket packet) {
        if(Constants.DEBUG) {
            Logger.logDebug("Packet received. Length: "+packet.getData());

            Logger.logDebug("Part 1:"+packet.getData()[0]);
            Logger.logDebug("Part 2:"+packet.getData()[1]);
        }

        short percentage = (short)packet.getData()[0];
        long timestamp = System.currentTimeMillis();
        BattPerReading reading = new BattPerReading();
        reading.perReading = percentage;
        reading.timestamp = timestamp;
        logger.battPerReading = reading;
    }
}
