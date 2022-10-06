package fb_projectgame.Model.Menu;

public class Menu {

    private MENU_Items selected;
    enum MENU_Items {PlAY, INSTRUCTIONS, EXIT};
    private boolean choosed;


    public Menu(){
        selected=MENU_Items.PlAY;
        choosed = false;
    }

    public void selectNext(){
        if(!choosed) {
            switch (selected) {
                case PlAY -> selected = MENU_Items.INSTRUCTIONS;
                case INSTRUCTIONS, EXIT -> selected = MENU_Items.EXIT;
            };
        }
    }

    public void selectprevious(){
        if(!choosed) {
            switch (selected) {
                case PlAY -> selected = MENU_Items.PlAY;
                case INSTRUCTIONS -> selected = MENU_Items.PlAY;
                case EXIT -> selected = MENU_Items.INSTRUCTIONS;
            }
        }
    }

    public MENU_Items getSelected() {
        return selected;
    }

    public void choose() {
        this.choosed = true;
    }

    public boolean isChoosed() {
        return choosed;
    }
}
