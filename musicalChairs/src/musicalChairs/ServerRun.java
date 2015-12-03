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
    Socket SERVER;
    
    
    
    public ServerRun(int port) throws IOException {
        super("ServerRun");
        this.SERVERSOCKET = new ServerSocket(port);
        this.PORT = port;
    }
    /**
     * gets player IP and Port
     */
    public void getPlayerInfo(){
        InetAddress clientIP = SERVER.getInetAddress();
        int clientPort = SERVER.getPort();
        System.out.println("TEST BA IP " + clientIP + "PORTEN OCKSÅ " + clientPort);
    }
    

    
    /**
     * 
     * funkar inte riktigt 
     */
    public void detectClosedClientSocket(DataInputStream in, DataOutputStream out, Socket SERVER) throws IOException{
        System.out.println(in.read());
        if (in.read() == -1){
            System.out.println("THE CLIENT HAS DISCONNECTED");
            in.close();
            out.close();
            SERVER.close();
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
        SERVER = SERVERSOCKET.accept();
        System.out.println("Just connected to client with IP " + SERVER.getInetAddress().getHostAddress() + " on port " + SERVER.getPort());
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
                DataInputStream in = new DataInputStream(SERVER.getInputStream());//skapar connection in
                DataOutputStream out = new DataOutputStream(SERVER.getOutputStream());//skapar connection ut
                //ServerGameProtocol.playRound(out);
                System.out.println(in.readUTF()+ " received this from "+ SERVER.getRemoteSocketAddress());
                out.writeUTF(" de här skickade du till mig"+ "\nGoodbye!");
                detectClosedClientSocket(in, out, SERVER);
                SERVER.close();
                
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
