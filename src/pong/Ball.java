

package pong;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

public class Ball {
    public Image i;
    public float x,y,w,h;
    public double speed, xSpeed, ySpeed;
    
    public Ball(Image j) throws SlickException{
        i = j;
    }
    
    public void setBall(float newX, float newY, float newW, float newH){
        x = newX;
        y = newY;
        w = newW;
        h = newH;
    }
    
    public void setSpeed(double theta, double speedSet){
        speed = speedSet;
        xSpeed = speed * Math.cos(theta);
        ySpeed = speed * Math.sin(theta);
    }
}
