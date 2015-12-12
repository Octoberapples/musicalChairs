package musicalChairs;

import java.util.Scanner;

public class ClientInterface {

    // Just nu gör den här funktionen att den skriver ut en rad när man har blivit connectad
    // Skriv HEJ eller PLAY för att få ett svar från servern som sedan processeras av funktionen
    // nedanför
    public static Object getRequest(String stringFromClient) {

        System.out.println("\n" + "Welcome to a game of " + stringFromClient + ". Please wait until all of the"
                + " players have arrived." + "\n" + " Meanwhile write PLAY or SIT DOWN in the terminal.");
        Scanner sc = new Scanner(System.in);
        String PLAY = sc.nextLine();
        return PLAY;
    }
    
    public static int input() {
        boolean flag = true;    
        while (flag == true) {
            try {
                System.out.println("Press 1 to Sit down:");
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
    
    public static String inputString() {
        boolean flag = true;    
        while (flag == true) {
            try {
                System.out.println("Write PLAY to begin game or SIT DOWN to sit:");
                Scanner sc = new Scanner(System.in);
                String tmpRequest = sc.nextLine();
                System.out.println("kom vi hit");
                if (tmpRequest.equals("PLAY")) {
                    System.out.println("Här då?");
                    return "PLAY";
                } else if (tmpRequest.equals("SIT DOWN")) {
                    //Client.closeSocket();
                    return "SIT DOWN";
                }
            } catch (Exception e) {
                System.out.println("Wrong input");
            }
        }
    return "";
    }

    //Processerar server response
    public static void processMessageFromServer(Object serverResponse) {
        if (serverResponse instanceof String) {

            switch ((String) serverResponse) {
                case "GET READY":
                    System.out.println("\n"
                            + "                            ┈┏━┓┈┈┈┈┈┏╯┈┈┈┈┏╯┈" + "\n"
                            + " The music is playing...    ┈┣━┫┈┈┈┈┈┣╯┈┈┈┈┣╯┈" + "\n"
                            + "                            ╭┫╭┫┈┈┃┈╭┫┈┈┃┈╭┫┈┈" + "\n"
                            + "                            ╰╯╰╯┈╭┫┈╰╯┈╭┫┈╰╯┈┈" + "\n"
                            + "                            ┈┈┈┈┈╰╯┈┈┈┈╰╯┈┈┈┈┈");
                    break;
                case "SIT DOWN":
                    System.out.println(
                              "\t" + "            i______i" + "\n"
                            + "\t" + "            I______I" + "\n"
                            + "\t" + "            I      I" + "\n"
                            + "\t" + " SIT DOWN!  I______I" + "\n"
                            + "\t" + "           /      /I" + "\n"
                            + "\t" + "          (______( I" + "\n"
                            + "\t" + "          I I    I I" + "\n"
                            + "\t" + "          I      I  ");
                    
                    break;
                default:
                    System.out.println("Something from the server came back");
                    break;
            }
        }

    }

public static Object timer() {
        long finalTimer = 0;
        long startTimer = System.currentTimeMillis();
        int clientResponse = input();
        if (clientResponse == 1) {
            long stopTimer = System.currentTimeMillis();
            finalTimer = stopTimer - startTimer;

        }
        return finalTimer;
    }
}
