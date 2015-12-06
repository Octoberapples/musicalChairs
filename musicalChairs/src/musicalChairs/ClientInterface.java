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
    public static String getRequest(String string) {

        System.out.println("Press 1 To " + string + " game or 2 to exit");
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
        switch (getRequest("Force Start")){
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
}
