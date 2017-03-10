

package pong;
import org.newdawn.slick.state.BasicGameState;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.GameState;

public class Ball {
    public Image i;
    public int x,y,w,h;
    public Ball() throws SlickException{
        i = new Image("ball2.png");
    }
}
