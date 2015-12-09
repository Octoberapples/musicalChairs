package musicalChairs;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author albin
 */
public class Server {

    static Socket newClient;
    static List<ServerClientThread> PLAYER_LIST = Collections.synchronizedList(new ArrayList<ServerClientThread>());

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        ServerPlayerList playerList = new ServerPlayerList();
        playerList.start();
        int id = 0;

        while (true) {
            newClient = waitForConnection(serverSocket);
            setUpConnectionWithClient(newClient, id++);
            /*for (int i = 0; i < PLAYER_LIST.size(); i++) {
                System.out.println(PLAYER_LIST.get(i).getClientID());

            }*/
            System.out.println(PLAYER_LIST.toString()); //Bara här för debugg!
        }
    }

    public List getPlayerList() {
        return PLAYER_LIST;
    }

    private static Socket waitForConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for client on port: " + serverSocket.getLocalPort()
                + " and IP: " + InetAddress.getLocalHost());
        return serverSocket.accept();
    }

    private static void setUpConnectionWithClient(Socket newclient, int id) throws IOException {
        ServerClientThread clientThread = new ServerClientThread(newclient, id);
        clientThread.start();
        PLAYER_LIST.add(clientThread);

    }

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

    /*
    Skicka ut att någon är vinnaren typ, eller att spelet börjar
     */
    private void broadcast(Object thing_to_send) {

        for (int i = 0; i < PLAYER_LIST.size(); i++) {
            //PLAYER_LIST.get(i)
        }
    }

}
