/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import java.util.Random;

/**
 *
 * @author Lan
 */
public class Power {
    
    public float x, y, w, h;
    public int id, past;
    private static final Power instance = new Power();
    
    private Power(){}
    
    public static Power getInstance(){
        return instance; 
    }
    
    public void setPower(int maxX, int maxY, float newW, float newH){
        Random rand = new Random();
        x = rand.nextInt(maxX);
        y = rand.nextInt(maxY);
        w = newW;
        h = newH;
        int temp = id;
        id = rand.nextInt(3) + 1;
        //id = ((id + 1)%2) +1;
        past = temp;
    }
    
}
