/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import java.util.Random;
import org.newdawn.slick.Image;

/**
 *
 * @author Lan
 */
public class DemoPower {
    
    public float x, y, w, h;
    public int id, past;
    private static final DemoPower instance = new DemoPower();
    public Image power1, power2, power3, power4;
    public Image[] set = new Image[4];
    public Image i;
    
    private DemoPower(){}
    
    public static DemoPower getInstance(){
        return instance; 
    }
    
    public void setPower(int maxX, int maxY, float newW, float newH){
        Random rand = new Random();
        x = maxX;
        y = maxY;
        w = newW;
        h = newH;
        int temp = id;
        id = rand.nextInt(4) + 1;
        //id = ((id + 1)%2) +1;
        past = temp;
        i = set[id - 1];
    }
    
    public void setImages(Image a, Image b, Image c, Image d){
        power1 = a;
        set[0] = power1;
        power2 = b;
        set[1] = power2;
        power3 = c;
        set[2] = power3;
        power4 = d;
        set[3] = power4;
        
    }
    
}
