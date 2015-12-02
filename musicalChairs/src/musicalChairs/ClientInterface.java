package musicalChairs;

import java.util.Scanner;
/*
MÅSTE FIXAS, OTRILOGT BUGGIGT
*/
public class ClientInterface {

    /**
     *
     * 
     * @return the client request
     */
    public static String getRequest() {
        int tmpRequest;
        System.out.println("Skriv 1 eller 2");
        Scanner sc = new Scanner(System.in);
        do {
            CheckIfInt(sc);
            tmpRequest = sc.nextInt();

        } while (tmpRequest == 1 || tmpRequest == 2);
        String test = Integer.toString(tmpRequest);
        return test;
    }

    /**
     *
     * @param Input from the player
     * @param length the length of the choice array
     */
    private static void CheckIfInt(Scanner Input) {
        while (!Input.hasNextInt()) {
            Input.next();
        }
    }

    
    /**
     * Print the requests that are available for the client.
     */
    public static String printChoices(String choices) {
        return "1: " + choices + " \n"
                + "2: Leave Game";
    }
}
