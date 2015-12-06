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
     * Fett buggig mannen Ge mig SAFE INPUT jag svär jag blir nöjd då
     *
     * @return the client request in form of a String
     */
    public static String getRequest(String string) throws IOException {
        boolean flag = true;
        int tmpRequest = 0;

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
                System.out.println("Wrong input");
            }
        }
        
        
        String tmpRequesttoString = Integer.toString(tmpRequest);
        return tmpRequesttoString;
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
