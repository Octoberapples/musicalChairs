package musicalChairs;

import java.net.*;
import java.io.*;

/**
 *
 * @author Nogna
 */
public class ServerRun extends Thread {

    private static int PORT; //Kanske onödig
    private ServerSocket SERVERSOCKET;
    String playerIP;
    Socket server;


    public ServerRun(int port) throws IOException {
        super("ServerRun");
        this.SERVERSOCKET = new ServerSocket(port);
        this.PORT = port;
    }
    
    public void waitForConnection() throws IOException{
                System.out.println("Waiting for client on port " + SERVERSOCKET.getLocalPort() + "...");
                server = SERVERSOCKET.accept();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());
                return;
    }
    
    String getIP(Socket server){
    return server.getRemoteSocketAddress().toString();
    }

    public void run() {
        while (true) {
            try {
                waitForConnection();
                playerIP = getIP(server);
                DataInputStream in = new DataInputStream(server.getInputStream());//skapar connection in
                DataOutputStream out = new DataOutputStream(server.getOutputStream());//skapar connection ut
                System.out.println(in.readUTF()+ " received this from "+ playerIP);
                out.writeUTF(" de här skickade du till mig"+ "\nGoodbye!");
                
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
