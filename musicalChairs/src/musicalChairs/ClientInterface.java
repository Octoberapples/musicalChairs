package musicalChairs;

import java.util.Scanner;

public class ClientInterface {

    /**
     *
     * @param choices we get from the server
     * @return the client request
     */
    public static int getRequest() {
        int tmp_req;
        Scanner sc = new Scanner(System.in);
        do {
            CheckIfInt(sc);
            tmp_req = sc.nextInt();

        } while (tmp_req == 1 || tmp_req == 2);

        return tmp_req;
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
