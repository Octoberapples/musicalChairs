package musicalChairs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Server {
    static Socket newClient;
    static List<ServerClientThread> PLAYER_LIST = Collections.synchronizedList(new ArrayList<ServerClientThread>());
    

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        int id = 0;

        while (id < 2) { //ökar den här om man vill ha mer spelare just nu är det två stycken
            newClient = waitForConnection(serverSocket);
            setUpConnectionWithClient(newClient, id++);
            
            System.out.println("The size of the list of players: " + PLAYER_LIST.size());         
          
        }
        
    }
    
 
    
    //waits on the clients
    private static Socket waitForConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for the players to connect.");
        return serverSocket.accept();
    }

    private static void setUpConnectionWithClient(Socket newclient, int id) throws IOException {
        ServerClientThread clientThread = new ServerClientThread(newclient, id);
        clientThread.start();
        PLAYER_LIST.add(clientThread);
        System.out.println("Accepted Client : ID - " + clientThread.CLIENT_ID + " : Address - "
                + clientThread.getClientIP() + " : Portnumber - " + clientThread.getClientPort());

    }

   

}
