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
        System.out.println("1-2: Skriv!");
        System.out.println(printChoices("Hej linnea"));
        Scanner sc = new Scanner(System.in);
        int tmpRequest = sc.nextInt();
        if (sc.hasNextInt() && (tmpRequest == 1 || tmpRequest == 2)) {
            tmpRequest = sc.nextInt();
        } else {
            System.out.println("GÖR OM");
        }
        String tmpRequesttoString = Integer.toString(tmpRequest);
        return tmpRequesttoString;
    }
    /*
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
     */

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
     *
     * @param choice
     */
    public static String printChoices(String choice) {
        return "1: " + choice + " \n"
                + "2: Leave Game";
    }
}
