package musicalChairs;

import java.util.Scanner;

/*

 MÅSTE FIXAS, OTRILOGT BUGGIGT 
 */
public class ClientInterface {

    /**
     * Fett buggig mannen Ge mig SAFE INPUT jag svär jag blir nöjd då
     *
     * @param string 
     * @return the client request in form of a String
     */
    public static Object getRequest(String string) {
        boolean flag = true;
        long startTimer = System.currentTimeMillis();
        
        while (flag == true) {
            try {
                System.out.println("Press 1 To " + string + " or 2 to exit");
                Scanner sc = new Scanner(System.in);

                int tmpRequest = sc.nextInt();
                if (tmpRequest == 1) {
                    flag = false;
                } else if (tmpRequest == 2) {
                    return "EXIT";
                    //Client.closeSocket();
                } else if (tmpRequest != 1 || tmpRequest != 2) {
                    System.out.println("Wrong input");
                }
            } catch (Exception e) {
                System.out.println("Wrong input");
            }
        }
        long stopTimer = System.currentTimeMillis();
        long finalTimer = stopTimer - startTimer;
        return finalTimer;
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
/*
    test skit bara
    */
    static Object getRequest() {
        Scanner sc = new Scanner(System.in);
        System.out.print("skriv in test värde: ");
        int tmpRequest = sc.nextInt();
        String tmpRequesttoString = Integer.toString(tmpRequest);
        return tmpRequesttoString;
    }
}
