package musicalChairs;

import java.net.*;
import java.io.*;

/**
 *
 * @author Nogna och lite tim
 */
public class ServerRun extends Thread {
    private static int PORT; //Kanske onödig
    private ServerSocket SERVERSOCKET;
    Socket server;
    
    
    
    public ServerRun(int port) throws IOException {
        super("ServerRun");
        this.SERVERSOCKET = new ServerSocket(port);
        this.PORT = port;
    }
    /**
     * gets player IP and Port
     */
    public void getPlayerInfo(){
        InetAddress clientIP = server.getInetAddress();
        int clientPort = server.getPort();
        System.out.println("TEST BA IP " + clientIP + "PORTEN OCKSÅ " + clientPort);
    }
    
    /**
     * Servers local clock. The time in the while loop is the maximum time before round automatically ends.
     * KANSKE TAR BORT DENNA O HAR KODEN UTAN FUNKTION, MÅSTE UPPDATERA EMPTY CHAIRS O DET ÄR NOG LÄTTARE UTAN FUNKTIONEN
     */
    
    public void timer(int emptyChairs){
        long timeElapsed = 0;
        long startTimer = System.nanoTime();
        while(timeElapsed<(20*(10^9)) || emptyChairs == 0){
            timeElapsed = System.nanoTime() - startTimer;
        }
        
    }
    /**
     * 
     * funkar inte riktigt 
     */
    public void detectClosedClientSocket(DataInputStream in, DataOutputStream out, Socket server) throws IOException{
        System.out.println(in.read());
        if (in.read() == -1){
            System.out.println("THE CLIENT HAS DISCONNECTED");
            out.close();
            server.close();
        }
    }
    
    /**
     * Sets up connection with client
     * @throws IOException
     */
    public void waitForConnection() throws IOException{
        InetAddress IP=InetAddress.getLocalHost();
        System.out.println("Waiting for client on port " + SERVERSOCKET.getLocalPort() + " and IP "
                + IP.getHostAddress());
        server = SERVERSOCKET.accept();
        System.out.println("Just connected to client with IP " + server.getInetAddress().getHostAddress() + " on port " + server.getPort());
        return;
    }
    
    /**
     * Gets connection with clients,
     * Opens data streams, in and out,
     */
    public void run() {
        while (true) {
            try {
                waitForConnection();
                DataInputStream in = new DataInputStream(server.getInputStream());//skapar connection in
                DataOutputStream out = new DataOutputStream(server.getOutputStream());//skapar connection ut
                System.out.println(in.readUTF()+ " received this from "+ server.getRemoteSocketAddress());
                out.writeUTF(" de här skickade du till mig"+ "\nGoodbye!");
                detectClosedClientSocket(in, out, server);
                server.close();
                
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    
}
