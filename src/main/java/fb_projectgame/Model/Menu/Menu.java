package fb_projectgame.Model.Menu;

public class Menu {

    private MenuItems selected;
    private boolean choosed;


    public Menu(){
        selected=MenuItems.PLAY;
        choosed = false;
    }

    public void selectNext(){
        if(!choosed) {
            switch (selected) {
                case PLAY -> selected = MenuItems.INSTRUCTIONS;
                case INSTRUCTIONS, EXIT -> selected = MenuItems.EXIT;
            };
        }
    }

    public void selectprevious(){
        if(!choosed) {
            switch (selected) {
                case PLAY -> selected = MenuItems.PLAY;
                case INSTRUCTIONS -> selected = MenuItems.PLAY;
                case EXIT -> selected = MenuItems.INSTRUCTIONS;
            }
        }
    }

    public MenuItems getSelected() {
        return selected;
    }

    public void choose() {
        this.choosed = true;
    }

    public boolean isChoosed() {
        return choosed;
    }
}
