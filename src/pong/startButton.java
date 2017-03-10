/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Lan
 */
public class startButton {
    public Image i;
    public int x,y;
    public int w = 300;
    public int h = 150;
    public startButton() throws SlickException{
        i = new Image("startButton.png");
    }
}
