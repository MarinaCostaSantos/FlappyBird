package fb_projectgame.Model.Menu;

public enum MenuItems {
    PLAY ("Play"),
    INSTRUCTIONS("Instructions"),
    EXIT ("Exit");

    private final String name;
    MenuItems(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}