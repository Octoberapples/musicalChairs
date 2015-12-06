package musicalChairs;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.net.Socket;

/*

<<<<<<< HEAD
 MÅSTE FIXAS, OTRILOGT BUGGIGT 
 */
=======
MÅSTE FIXAS, OTRILOGT BUGGIGT
*/

>>>>>>> refs/remotes/origin/master
public class ClientInterface {
    
    /**
     * Fett buggig mannen Ge mig SAFE INPUT jag svär jag blir nöjd då
     *
     * @return the client request in form of a String
     */
    public static String getRequest(String string) throws IOException {
        boolean flag = true;
        int tmpRequest = 0;
<<<<<<< HEAD
        while (flag == true) {
            try {
                System.out.println("Press 1 To " + string + " game or 2 to exit");
                Scanner sc = new Scanner(System.in);

                tmpRequest = sc.nextInt();
                if (tmpRequest == 1 || tmpRequest == 2) {
                    flag = false;
                } else if (tmpRequest != 1 || tmpRequest != 2) {
                    System.out.println("Wrong input");
                }
            } catch (Exception e) {
=======
        while (flag == true){
            try{
                System.out.println("Press 1 To " + string + " game or 2 to exit");
                Scanner sc = new Scanner(System.in);
                
                tmpRequest = sc.nextInt();
                if (tmpRequest == 1){
                    flag = false;
                }
                else if (tmpRequest == 2){
                flag = false;
                //Client.closeSocket();
                }
                else if (tmpRequest != 1 || tmpRequest != 2){
                    System.out.println("Wrong input");
                }
            } catch (Exception e){
>>>>>>> refs/remotes/origin/master
                System.out.println("Wrong input");
            }
        }
        
        
        String tmpRequesttoString = Integer.toString(tmpRequest);
        return tmpRequesttoString;
    }

    /**
     *
<<<<<<< HEAD
     * the function that is called when connecting to server choices: JOIN(1) or
     * EXIT(2)
     */
    /*
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
     */
=======
     * the function that is called when connecting to server
     * choices: JOIN(1) or EXIT(2)
     */
    /*
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
    */
>>>>>>> refs/remotes/origin/master
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
