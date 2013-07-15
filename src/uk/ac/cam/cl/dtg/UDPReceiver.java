package uk.ac.cam.cl.dtg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceiver implements Runnable {

    private int port;

    public UDPReceiver(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        DatagramPacket packet = new DatagramPacket(new byte[3], 3);

        while(true) {
            try {
                socket.receive(packet);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
