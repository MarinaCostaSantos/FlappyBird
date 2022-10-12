package fb_projectgame.Model.Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenuItemsTest extends Assertions {

    @Test
    public void toStringTest  (){

        //when
        String play = MenuItems.PLAY.toString();
        String leaderboard = MenuItems.INSTRUCTIONS.toString();
        String exit = MenuItems.EXIT.toString();

        //then
        assertEquals(play, "Play");
        assertEquals(leaderboard, "Instructions");
        assertEquals(exit, "Exit");
    }

}
