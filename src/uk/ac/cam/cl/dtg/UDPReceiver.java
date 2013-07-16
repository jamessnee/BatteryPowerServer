package uk.ac.cam.cl.dtg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class UDPReceiver implements Runnable {

    private int port;

    public UDPReceiver(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        Logger.logDebug("UDPReceviver Started");
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
//            ByteBuffer bb = ByteBuffer.allocate(2);
//            bb.order(ByteOrder.LITTLE_ENDIAN);
//            bb.put(packet.getData()[0]);
//            bb.put(packet.getData()[1]);
//            short battPercentage = bb.getShort();

//            Logger.logDebug("Reported Percentage: "+battPercentage);
        }
    }
}
