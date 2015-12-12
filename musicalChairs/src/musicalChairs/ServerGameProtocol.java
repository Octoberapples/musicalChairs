package musicalChairs;

//Klassen där vi hanterar det vi får från klienten och skickar tillbaka
//ett serverResponse
class ServerGameProtocol {

    public static Object handleClientInput(Object clientResponse) {
        String serverResponse;
        if (clientResponse instanceof String) {

            switch ((String) clientResponse) {
                case "PLAY":
                    System.out.println("A client wants to play");
                    serverResponse = "GET READY";
                    return serverResponse;
                case "HEJ":
                    System.out.println("A client wants to say hello (Adele-stye)");
                    serverResponse = "SIT DOWN";
                    return serverResponse;
                default:
                    System.out.println("A client joined the game");
                    serverResponse = "FORCE START";
                    return serverResponse;
            }

        } else {
            return "CLIENT_CORRUPTED";
        }
    }

}
