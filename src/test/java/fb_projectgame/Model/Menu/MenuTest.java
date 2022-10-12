package fb_projectgame.Model.Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuTest extends Assertions {

    Menu menu;

    @BeforeEach
    void init(){
        menu = new Menu();
    }

    @Test
    void choose(){
        // when
        boolean choose1 = menu.isChoosed();
        menu.choose();
        boolean choose2 =menu.isChoosed();

        // then
        assertFalse(choose1);
        assertTrue(choose2);
    }

    @Test
    void selectNextChoose(){
        // when
        menu.choose();
        MenuItems select1 = menu.getSelected();
        menu.selectNext();
        MenuItems select2 = menu.getSelected();
        menu.selectNext();
        MenuItems select3 = menu.getSelected();
        menu.selectNext();
        MenuItems select4 = menu.getSelected();
        menu.selectNext();

        // then
        assertEquals(MenuItems.PLAY, select1);
        assertEquals(MenuItems.PLAY, select2);
        assertEquals(MenuItems.PLAY, select3);
        assertEquals(MenuItems.PLAY, select4);
    }

    @Test
    void selectPreviousChoose(){
        // when
        menu.selectNext();
        menu.selectNext();
        menu.selectNext();

        menu.choose();

        MenuItems select1 = menu.getSelected();
        menu.selectprevious();
        MenuItems select2 = menu.getSelected();
        menu.selectprevious();
        MenuItems select3 = menu.getSelected();
        menu.selectprevious();
        MenuItems select4 = menu.getSelected();
        menu.selectprevious();

        //then
        assertEquals(MenuItems.EXIT, select1);
        assertEquals(MenuItems.EXIT, select2);
        assertEquals(MenuItems.EXIT, select3);
        assertEquals(MenuItems.EXIT, select4);
    }

    @Test
    void selectNextNotChoose(){
        // when
        MenuItems select1 = menu.getSelected();
        menu.selectNext();
        MenuItems select2 = menu.getSelected();
        menu.selectNext();
        MenuItems select3 = menu.getSelected();
        menu.selectNext();
        MenuItems select4 = menu.getSelected();
        menu.selectNext();

        // then
        assertEquals(MenuItems.PLAY, select1);
        assertEquals(MenuItems.INSTRUCTIONS, select2);
        assertEquals(MenuItems.EXIT, select3);
    }

    @Test
    void selectPreviousNotChoose(){
        // when
        menu.selectNext();
        menu.selectNext();
        menu.selectNext();

        MenuItems select1 = menu.getSelected();
        menu.selectprevious();
        MenuItems select2 = menu.getSelected();
        menu.selectprevious();
        MenuItems select3 = menu.getSelected();
        menu.selectprevious();
        MenuItems select4 = menu.getSelected();
        menu.selectprevious();

        //then
        assertEquals(MenuItems.EXIT, select1);
        assertEquals(MenuItems.INSTRUCTIONS, select2);
        assertEquals(MenuItems.PLAY, select3);
    }
}
