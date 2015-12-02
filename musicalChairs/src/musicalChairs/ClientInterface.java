package musicalChairs;

import java.util.InputMismatchException;
import java.util.Scanner;
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

        System.out.println("1-2: Skriv!");
        System.out.println(printChoices("Join Game"));
        Scanner sc = new Scanner(System.in);
        int tmpRequest = 0;
        tmpRequest = sc.nextInt();
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

    /**
     * Print the requests that are available for the client.
     */
    public static String printChoices(String choice) {
        return "1: " + choice + " \n"
                + "2: Leave Game";
    }
}
