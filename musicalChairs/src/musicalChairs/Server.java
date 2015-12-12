package musicalChairs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Behöver göra så att en client kan disconnecta på ett fint sätt utan servern
// dör. 
// Den här klassen intisierar alla trådar, servern lyssnar på porten angiven.
public class Server {
    //The port the server is listening to
    private static final int PORT = 8080;
    static Socket newClient;
    //The list containing all of the players
    static List<ServerClientThread> PLAYER_LIST = Collections.synchronizedList(new ArrayList<ServerClientThread>());
    static int MAX_AMOUNT_OF_PLAYERS = 0; //här skriver vi hur många spelare som är max

        //Klass som tar bort döda trådar
       private static class ServerPlayerList extends Thread {

        public void run() {
            while (true) {
                setPlayerList();
            }
        }

        private static void setPlayerList() {

            for (int i = 0; i < PLAYER_LIST.size(); i++) {

                if (!PLAYER_LIST.get(i).isAlive()) {
                    PLAYER_LIST.remove(i);
                }
            }
        }
    }  
       
       
    
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        int id = 0;
        ServerPlayerList playerList = new ServerPlayerList();

        //The application main method, which just listens on a port and
        //spawns ServerClientThread threads.
        try {
            while (true) {
                newClient = waitForConnection(serverSocket);
                System.out.println("new client är" + newClient);
                setUpConnectionWithClient(newClient, id++);
            }
        } finally {
            System.out.println("Stängs nåt?");
            newClient.close();
        } 
       
    }
      
   //Gör som funktionen säger, väntar på en connection och
   //accepter sedan serverSocket 
    private static Socket waitForConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("\n" + "Waiting for the players to connect.");
        return serverSocket.accept();
    }

    //Startat en tråd för varje spelare i ServerClientThread samt lägger till spelaren i PLAYER_LIST
    private static void setUpConnectionWithClient(Socket newclient, int id) throws IOException {
        ServerClientThread clientThread = new ServerClientThread(newclient, id);
        clientThread.start();
        PLAYER_LIST.add(clientThread);
        System.out.println("\n" +"Accepted Client : ID - " + clientThread.CLIENT_ID + " : Address - "
                + clientThread.getClientIP() + " : Portnumber - " + clientThread.getClientPort());
    }

}
  