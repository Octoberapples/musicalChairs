package musicalChairs;

import java.util.Scanner;

/*

 MÅSTE FIXAS, OTRILOGT BUGGIGT 
 */
public class ClientInterface {

    /**
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

  public static int input() {
        boolean flag = true;    
        while (flag == true) {
            try {
                Scanner sc = new Scanner(System.in);
                int tmpRequest = sc.nextInt();
                if (tmpRequest == 1) {
                    flag = false;
                    return 1;
                } else if (tmpRequest == 2) {
                    //Client.closeSocket();
                    return 2;
                } else if (tmpRequest != 1 || tmpRequest != 2) {
                    System.out.println("Wrong input");
                }
            } catch (Exception e) {
                System.out.println("Wrong input");
            }
        }
    return -1;
    }
    
    public static Object sitDown() throws Exception{
        
        System.out.println("Sit Down in");
        Thread.sleep(1000);
        System.out.println("3");
        Thread.sleep(1000);
        System.out.println("2");
        Thread.sleep(1000);
        System.out.println("1");
        Thread.sleep(1000);
        long startTimer = System.currentTimeMillis();
        System.out.println("Sit Down!");
        /*TODO - glöm inte att stänga socketen om exit*/
        int clientResponse = input();
        if(clientResponse == 1){
        long stopTimer = System.currentTimeMillis();
        long finalTimer = stopTimer - startTimer;
        finalTimer = finalTimer*1000;
        int finalTid = (int)(long)finalTimer;
        return finalTid;
        }
        else{/*close socket*/};
        return 0;
    }
   
    /*
    Test för getRequest()
    */
    static Object getRequest() {
        Scanner sc = new Scanner(System.in);
        System.out.print("skriv in test värde: ");
        int tmpRequest = sc.nextInt();
        String tmpRequesttoString = Integer.toString(tmpRequest);
        return tmpRequesttoString;
    }
}
