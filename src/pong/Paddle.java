
package pong;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


public class Paddle {
    public Image i;
    public int x,y,w,h;
    public Paddle() throws SlickException{
        i = new Image("paddle.png");
    }    
}
