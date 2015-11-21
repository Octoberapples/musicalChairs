package musicalChairs;

import java.util.Scanner;

public class ClientInterface {

    /**
     * eyeyeyeyye  ändrarrrr
     *
     * @param choices we get from the server
     * @return the client request
     */
    public static String getRequest(String[] choices) {
        int tmp_req;
        System.out.println("Plz a number between 1 and " + choices.length + ": ");
        Scanner sc = new Scanner(System.in);
        do {
            CheckIfInt(sc);
            tmp_req = sc.nextInt();

        } while (validNumber(tmp_req, choices.length));

        return choices[(tmp_req - 1)];
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

    private static boolean validNumber(int input, int length) {
        return (input <= 1 && input >= length);
    }

    /*
    Print the requests that are availiblefor the client.
     */
    public static void printChoices(String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i + ": " + choices[i].toString()); //Kanske måste göra egen toString
        }
    }
}
