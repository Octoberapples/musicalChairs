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

    /**
     * helt orimligt fel. hatar livet
     */
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
     *
     * updates state. Can be: WINNER, LOSER, IN_PLAYER_QUEUE, ADVANCED,
     * SIT_DOWN, GET_READY
     */
    public void updateState(Socket SERVER, String state) {

    }

    public String sendResult(String state) throws IOException {
        String serverResponse = test.getResponseChoices(state);
        return serverResponse;
    }

    /**
     * gets a String from client and uses it
     */
    public static String handleClientInput(String clientResponse) {
        String serverResponse;
        switch (clientResponse) {
            case "":

            case "SIT":

            default:
                System.out.println("A client joined the game");
                serverResponse = "FORCE START";
                System.out.println(serverResponse);
                return serverResponse;

        }
    }

}
