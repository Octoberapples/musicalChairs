package musicalChairs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Server {
    static int players = 0;
    static Socket newClient;
    static List<ServerClientThread> PLAYER_LIST = Collections.synchronizedList(new ArrayList<ServerClientThread>());
    

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        int id = 0;

        while (players < 2) {
            newClient = waitForConnection(serverSocket);
            setUpConnectionWithClient(newClient, id++);
            for (int i = 0; i < PLAYER_LIST.size(); i++) {
             System.out.println(PLAYER_LIST.get(i).getClientID());
             }
            
            System.out.println("bla" + PLAYER_LIST.toString()); //Bara här för debugg!
            
              
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
