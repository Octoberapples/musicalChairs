package musicalChairs;

//Klassen där vi hanterar det vi får från klienten och skickar tillbaka
//ett serverResponse
class ServerGameProtocol extends Thread {

    public static Object handleClientInput(Object clientResponse) {
        Object serverResponse = null;
        if (clientResponse instanceof String) {
            switch ((String) clientResponse) {
                case ("PLAY"):
                    serverResponse = "In Player Queue";
                    break;
                case ("GET READY"):
                    serverResponse = "Music is playing!";
                    break;
            }
        }
        if (clientResponse instanceof Long) {
            System.out.println("Fick timestampen");
            serverResponse = "WINNER";
        }

        return serverResponse;
    }

}
