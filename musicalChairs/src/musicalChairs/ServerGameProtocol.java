package musicalChairs;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.io.*;

/**
 * Själva protokollet för spelet. Jämför resultat och skapar semaphorer osv..
 *
 * @author Nognaaaaaaa
 */
class ServerGameProtocol {

    ServerResponseRepository test = new ServerResponseRepository();

    /**
     * Servers local clock. The time in the while loop is the maximum time
     * before round automatically ends. KANSKE TAR BORT DENNA O HAR KODEN UTAN
     * FUNKTION, MÅSTE UPPDATERA EMPTY CHAIRS O DET ÄR NOG LÄTTARE UTAN
     * FUNKTIONEN
     */
    public void checkIfRoundIsOver(int emptyChairs) {
        long timeElapsed = 0;
        long startTimer = System.nanoTime();
        while (timeElapsed < (20 * (10 ^ 9)) || emptyChairs == 0) {
            timeElapsed = System.nanoTime() - startTimer;
            //round over
        }

    }
    public static void runGame() throws Exception{
        Object tid = ServerClientThread.STREAM_IN_FROM_CLIENT.readObject();
        System.out.println(tid + " TTITTTA HÄR HALLÅÅÅ");
        System.out.println("KOMMER VI HIT DÅ?");
        

        
}
            
            
            
    public static void playRound(DataOutputStream out) throws IOException {
        //out.writeUTF("SIT DOWN NOW!");
        int emptyChairs = 0;
        long timeElapsed = 0;
        long startTimer = System.nanoTime();
        System.out.println("HALLIIII TIMEELAPSED HER" + timeElapsed);
        while ((timeElapsed < (20 * (10 ^ 9)))) {
            timeElapsed = System.nanoTime() - startTimer;
            System.out.println("ETT VARV");
            //updateStates()
            //sendResult()
        }
        System.out.println("TIMERN SLUTAR PÅ: " + timeElapsed);
        System.out.println("ROUND DONE");
    }

    /**
     * updates semaphore when server gets timestamp from client
     */
    public void checkChairs() {

    }

    /**
     * gets a String from client and uses it
     */
    public static Object handleClientInput(Object clientResponse) {
        String serverResponse;
        if (clientResponse instanceof String) {

            switch ((String) clientResponse) {
                case "FORCE START":
                    System.out.println("A client forcestarted the game");
                    serverResponse = "A client forcestarted the game";
                    return serverResponse;
                default:
                    System.out.println("A client joined the game");
                    serverResponse = "FORCE START";
                    return serverResponse;
            }

        } else if (clientResponse instanceof Long) {
            return (Long) clientResponse;

        } else {
            return "CLIENT_CORRUPTED";
        }
    }

}
