
package pong;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


public class Paddle {
    public Image i;
    public float x,y,w,h;
    public Paddle(Image j) throws SlickException{
        i = j;
    }    
}
