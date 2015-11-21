package musicalChairs;

public class ServerResponseRepository {

    public String getResponseChoices(String state) {
        switch (state) {

            case "InPlayerQueue":
                return getChoicesInPlayerQueue();
                
            case "InGame":
                return getChoicesInGame();
        }
        return "";
    }

    private String getChoicesInGame() {
        return "Sit down";
    }

    private String getChoicesInPlayerQueue() {
        return "Force game";
    }

}
