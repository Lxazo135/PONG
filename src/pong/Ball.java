

package pong;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

public class Ball {
    public Image i;
    public int x,y,w,h;
    public Ball() throws SlickException{
        i = new Image("ball2.png");
    }
}
