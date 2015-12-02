package musicalChairs;

import java.util.Scanner;
/*
MÅSTE FIXAS, OTRILOGT BUGGIGT jihihihi
*/
public class ClientInterface {

    
    public static int getRequest() {
        int tmpRequest;
        Scanner sc = new Scanner(System.in);
        do {
            CheckIfInt(sc);
            tmpRequest = sc.nextInt();

        } while (tmpRequest == 1 || tmpRequest == 2);

        return tmpRequest;
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
     * @param choices
     */
    public static String printChoices(String choices) {
        return "1: " + choices + " \n"
                + "2: Leave Game";
    }
}
