package musicalChairs;



public class ServerResponseRepository {

    public static String getResponseChoices(String state) {
        switch (state) {
            case "WINNER":
                return getChoicesWinner();
            case "LOSER":
                return getChoicesLoser();
            case "IN_PLAYER_QUEUE":
                return getChoicesInPlayerQueue();
            case "ADVANCED":
                return getChoicesAdvanced();
            case "SIT_DOWN":
                return getChoicesSitDown();
            case "GET_READY":
                return getChoicesGetReady();
        }
        return "";
    }


    private static String getChoicesInPlayerQueue() {
        return "FORCE START";
    }

    private static String getChoicesWinner() {
        return "WINNER";
    }

    private static String getChoicesLoser() {
        return "LOSER";
    }

    private static String getChoicesAdvanced() {
        return "ADVANCED";
    }

    private static String getChoicesSitDown() {
        return "SIT DOWM";
    }

    private static String getChoicesGetReady() {
        return "GET READY";
    }

}
