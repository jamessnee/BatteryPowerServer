package uk.ac.cam.cl.dtg;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/*
This class is responsible for reading the power consumed by the device.
 */
public class PowerConsumptionReader implements Runnable{

    private Logger logger;
    private ServerSocket serverSocket;
    public boolean running = false;

    public PowerConsumptionReader(Logger logger) {
        this.logger = logger;
    }

    public boolean setupServerSocket() {
        try {
            serverSocket = new ServerSocket();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }

        if(Constants.DEBUG)
            Logger.logDebug("Server socket setup");

        try {
            serverSocket.setReceiveBufferSize(1024 * 1024);
        } catch(SocketException e) {
            e.printStackTrace();
            return false;
        }

        if(Constants.DEBUG)
            Logger.logDebug("Socket buffer size setup");

        try {
            serverSocket.bind(new InetSocketAddress(Constants.POWER_PORT));
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }

        if(Constants.DEBUG)
            Logger.logDebug("Server socket bound");

        return true;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024 * 4];
        running = true;

        try {
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();

            if(Constants.DEBUG)
                Logger.logDebug("PowerConsumptionReader running...");

            // The read loop
            while(running) {
                int read = is.read(buffer);

                if(read == -1) {
                    Logger.logDebug("There was an error reading from the power kit!");
                }
                if(read % 4 != 0) {
                    Logger.logDebug("Error, we assume there are no fragmented samples");
                }

                if(Constants.DEBUG)
                    System.out.println("READ: "+buffer);
            }
        } catch(IOException e){
            e.printStackTrace();
        }


        // Clean up
        try {
            serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
