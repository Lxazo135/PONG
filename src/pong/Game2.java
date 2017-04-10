/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;
import java.lang.Math;
import static java.lang.Math.atan;
import static java.lang.Math.tan;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import java.util.Random;

public class Game2 extends BasicGameState{
    // ID we return to class 'Application'
	public static final int ID = 2;
        private StateBasedGame game;
        public static Image background;
        public Ball ball;
        public Ball2 ball2;//ball 2 is invisible, for AI
        public Paddle p1,p2;
        public Input input;
        public boolean start, start2;
        public boolean left, left2;
        public boolean up;
        public double ballSpeed, ballSpeed2, startSpeed;
        public double xSpeed, ySpeed, xSpeed2, ySpeed2;
        public double paddleSpeed;
        public int maxHeight;
        public int minHeight;
        public int minWidth;
        public int maxWidth;
        public int ballPos, ballPos2, p1Pos, p2Pos;
        public double theta;
        public int score1;
        public int score2;
        
	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
            this.game = sbg;
            background = new Image("bg1.png");
            left = false;
            left2 = false;
            start = false;
            start2 = false;
            minHeight = 480;
            maxHeight = 0;
            minWidth = 0;
            maxWidth = 640;
            
            Random rand = new Random();//do seed
            startSpeed = 5;
            ballSpeed = startSpeed;
            theta = rand.nextDouble()*180 - 89;
            ySpeed = ballSpeed * Math.sin(theta);
            xSpeed = ballSpeed * Math.cos(theta);
            
            ballSpeed2 = ballSpeed * 5;
            ySpeed2 = ballSpeed2 * Math.sin(theta);
            xSpeed2 = ballSpeed2 * Math.cos(theta);
            
            paddleSpeed = 15;

            ball = new Ball();
            ball.w = 20;
            ball.h = 20;
            ball.x = 320 - ball.w;
            ball.y = 240 - ball.h;
            
            ball2 = new Ball2();
            ball2.w = 20;
            ball2.h = 20;
            ball2.x = 320 - ball.w;
            ball2.y = 240 - ball.h;

            p1 = new Paddle();                
            p1.w = 20;
            p1.h = 125;
            p1.x = minWidth;
            p1.y = (minHeight /2) - (p1.h / 2);
            p2 = new Paddle();
            p2.w = 20;
            p2.h = 125;
            p2.x = maxWidth - p2.w;
            p2.y = (minHeight / 2) - (p2.h / 2);    
            
            ballPos = ball.y + (ball.h / 2);
            p1Pos = p1.y + (p1.h / 2);
            p2Pos = p2.y + (p2.h / 2);
            
            score1 = 0;
            score2 = 0;
	}

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
            g.drawImage(background, 0, 0);
            g.drawString("Player VS AI", 300, 10);
            g.fillOval(ball.x, ball.y, ball.w, ball.h);
            g.drawImage(ball.i, ball.x, ball.y);
            //g.fillOval(ball2.x, ball2.y, ball2.w, ball2.h);
            g.drawImage(ball2.i, ball2.x, ball2.y);
            g.fillRoundRect(p1.x, p1.y, p1.w, p1.h, 10);
            g.drawImage(p1.i, p1.x, p1.y);
            g.fillRoundRect(p2.x, p2.y, p2.w, p2.h, 10);
            g.drawImage(p2.i, p2.x, p2.y);
            g.drawString(Integer.toString(score1), 200, 50);
            g.drawString(Integer.toString(score2), 450, 50);
	}

	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
            ballPos = ball.y + (ball.h / 2);
            ballPos2 = ball2.y + (ball2.h/2);
            p1Pos = p1.y + (p1.h / 2);
            p2Pos = p2.y + (p2.h / 2);
            Random rand = new Random();
            input = gc.getInput();         
            
            if(start){//game start
                if(left){//ball going left
                    ball.x += -xSpeed;
                    ball.y += ySpeed;
                    //left paddle collision
                    if(ball.x <= (p1.x + p1.w) && ball.y <= (p1.y + p1.h) && (ball.y + ball.h) >= (p1.y)){
                        start2 = true;
                        left = false;
                        left2 = left;
                        if(ballSpeed < 20){
                            ballSpeed++;
                            ballSpeed2 = ballSpeed * 5;
                        }
                        theta =  atan((ballPos - p1Pos)/((p1.w/2) + (ball.w/2)));
                        ySpeed = ballSpeed * Math.sin(theta);
                        xSpeed = ballSpeed * Math.cos(theta);
                       
                        ball2.x = ball.x;
                        ball2.y = ball.y;
                        ySpeed2 = ballSpeed2 * Math.sin(theta);
                        xSpeed2 = ballSpeed2 * Math.cos(theta);
                      }
                    //left wall collision ball1
                    if(ball.x <= minWidth){
                        start = false;
                        theta = rand.nextDouble()*180 - 89;
                        ball.x = 320 - ball.w;
                        ball.y = 240 - ball.h;
                        ballSpeed = startSpeed;
                        ySpeed = ballSpeed * Math.sin(theta);
                        xSpeed = ballSpeed * Math.cos(theta);
                        
                        start2 = false;
                        ball2.x = ball.x;
                        ball2.y = ball.y;
                        ballSpeed2 = ballSpeed * 5;
                        ySpeed2 = ballSpeed2 * Math.sin(theta);
                        xSpeed2 = ballSpeed2 * Math.cos(theta);
                        score2++;
                        
                    }
                }//ball going right
                else{
                    ball.x += xSpeed;
                    ball.y += ySpeed;
                    //right paddle collision
                    if(ball.x + ball.w >= (p2.x) && ball.y <= (p2.y + p2.h) && (ball.y + ball.h) >= (p2.y)){
                        start2 = true;
                        left = true;
                        left2 = left;
                        if(ballSpeed < 20){
                            ballSpeed++;
                            ballSpeed2 = ballSpeed * 5;
                        }
                        theta =  atan((ballPos - p2Pos)/((p2.w/2) + (ball.w/2)));
                        ySpeed = ballSpeed * Math.sin(theta);
                        xSpeed = ballSpeed * Math.cos(theta);
                        
                        ball2.x = ball.x;
                        ball2.y = ball.y;
                        ySpeed2 = ballSpeed2 * Math.sin(theta);
                        xSpeed2 = ballSpeed2 * Math.cos(theta);
                    }
                    //right wall collision ball1
                    if(ball.x + ball.w >= maxWidth){
                        start = false;
                        theta = rand.nextDouble()*180 - 89;
                        ball.x = 320 - ball.w;
                        ball.y = 240 - ball.h;
                        ballSpeed = startSpeed;
                        ySpeed = ballSpeed * Math.sin(theta);
                        xSpeed = ballSpeed * Math.cos(theta);
                        
                        ball2.x = ball.x;
                        ball2.y = ball.y;
                        ballSpeed2 = ballSpeed * 5;
                        ySpeed2 = ballSpeed2 * Math.sin(theta);
                        xSpeed2 = ballSpeed2 * Math.cos(theta);
                        score1++;
                    }
                    //BALL 2 collision
                    if(ball2.x + ball2.w >= maxWidth){
                        if(p2Pos > ballPos2){
                            p2.y += -3;
                            if(p2Pos <= ballPos2){
                                p2.y = ball2.y + (ball2.h/2) - (p1.h/2);//makes ballPos2 = p2Pos
                            }    
                        }
                        else if(p2Pos < ballPos2){
                            p2.y += 3;
                            if(p2Pos >= ballPos2){
                                p2.y = ball2.y + (ball2.h/2) - (p1.h/2);//same as above
                            }  
                        }
                    }
                }
                //top wall collision
                if(ball.y <= maxHeight){
                    ySpeed = -ySpeed;
                }
                //botton wall collision
                if(ball.y + ball.h >= minHeight){
                    ySpeed = -ySpeed;
                }
                //same for ball 2
                if(ball2.y <= maxHeight){
                    ySpeed2 = -ySpeed2;
                }
                //botton wall collision
                if(ball2.y + ball.h >= minHeight){
                    ySpeed2 = -ySpeed2;
                }
            }else{
                if(input.isKeyPressed(Input.KEY_SPACE)){
                start = true;
                start2 = true;
                left = !left;
                left2 = left;
                }
            }
            // ball 2 
            if(start2){
                
                if(!left2){
                    ball2.x += xSpeed2;
                    ball2.y += ySpeed2;
                    //BALL 2 RIGHT WALL COLLISION 
                    if(ball2.x + ball2.w >= maxWidth){
                        start2 = false;
                        if(p2Pos > ballPos2){
                          //  p2.y += -paddleSpeed;
                        }
                        else{
                          //  p2.y += paddleSpeed;
                        }
                    }
                }
                
                
            }
            //Up left paddle
            if(input.isKeyDown(Input.KEY_Q)){
                if(p1.y >= maxHeight){
                    p1.y += -paddleSpeed;
                }
            }
            //Down left paddle
            if(input.isKeyDown(Input.KEY_A)){
                if(p1.y + p1.h <= minHeight){
                    p1.y += paddleSpeed;
                }
            }
	}

	// Returning 'ID' from class 'MainMenu'
	@Override
	public int getID() {
		return Game2.ID;
	}
        public void keyReleased(int key, char c){
            if(key == Input.KEY_1){          
                start = false;
                ball.x = 320 - ball.w;
                ball.y = 240 - ball.h;
                xSpeed = 3;
                ySpeed = 0;
                p1.x = minWidth;
                p1.y = (minHeight /2) - (p1.h / 2);
                p2.x = maxWidth - p2.w;
                p2.y = (minHeight / 2) - (p2.h / 2); 
                score1 = 0;
                score2 = 0;
                game.enterState(MainMenu.ID);
            }
        }
}
