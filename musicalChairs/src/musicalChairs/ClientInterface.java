package musicalChairs;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.net.Socket;

/*

 MÅSTE FIXAS, OTRILOGT BUGGIGT 
 */

public class ClientInterface {

    /**
     *  Fett buggig mannen
     *  Ge mig SAFE INPUT jag svär jag blir nöjd då
     * @return the client request in form of a String
     */
    public static String getRequest() {

        System.out.println("1 eller 2: Skriv!");
        System.out.println(printChoices("Join Game"));
        Scanner sc = new Scanner(System.in);
        int tmpRequest = 0;
        tmpRequest = sc.nextInt();
        String tmpRequesttoString = Integer.toString(tmpRequest);
        return tmpRequesttoString;
    }
/**
 * 
 * the function that is called when connecting to server
 * choices: JOIN(1) or EXIT(2)
 */
    public static void joinOrExit(Socket clientSocket) throws IOException{
        switch (getRequest()){
            case "1":
                //Join game;
                //out.writeUTF("JOIN");//sending to server that client is joining//tror jag vill ta outputstream som inparameter
                System.out.println("Joined the game");
                return;
            case "2":
                //out.writeUTF("EXIT"); //sending to server that client exits
                System.out.println("Closing Socket");
                clientSocket.close();
                if(clientSocket.isClosed()){
                    System.out.println("SUCCCESSFUL CLOSING OF SOCKET");}
                return;
            default: System.out.println("Try again, 1 or 2");
            
        }
    }

    /**
     *
     * @param Input from the player
     */
    private static void CheckIfInt(Scanner Input) {
        while (!Input.hasNextInt()) {
            Input.next();
        }
    }

    /**
     * Print the requests that are available for the client.
     */
    public static String printChoices(String choice) {
        return "1: " + choice + " \n"
                + "2: Leave Game";
    }
}
