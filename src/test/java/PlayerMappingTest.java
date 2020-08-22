import org.junit.Before;
import org.junit.Test;
import player.Player;

import static org.junit.Assert.assertEquals;

public class PlayerMappingTest {

    private Application application;

    @Before
    public void before(){
        application = new Application();
    }

    @Test
    public void playerBuilderMappingTest(){
        String [] playerArray = {"David", "De Gea", "Man United", "Goalkeeper"};
        Player player = application.getPlayer(playerArray);
        assertEquals(playerArray[0], player.getFirstname());
        assertEquals(playerArray[1], player.getLastname());
        assertEquals(playerArray[2], player.getPosition());
        assertEquals(playerArray[3], player.getTeam());
    }
}
