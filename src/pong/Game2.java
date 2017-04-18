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
import org.newdawn.slick.Sound;

public class Game2 extends BasicGameState{
    // ID we return to class 'Application'
	public static final int ID = 2;
        private StateBasedGame game;
        public static Image background;
        public Ball ball, ball2;
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
        public double ballPos, ballPos2, p1Pos, p2Pos;
        public double theta;
        public int score1;
        public int score2;
        public Sound hit;
        public Sound bounce;
        public Sound splat;
        
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
            
            hit = new Sound("ballhit.wav");
            bounce = new Sound("bounce.wav");
            splat = new Sound("splat.wav");
            
            Image i = new Image("ball2.png");
            ball = new Ball(i);
            ball.w = 20;
            ball.h = 20;
            ball.x = 320 - ball.w;
            ball.y = 240 - ball.h;
            
            Image j = new Image("ball3.png");
            ball2 = new Ball(j);
            ball2.w = 20;
            ball2.h = 20;
            ball2.x = ball.x;
            ball2.y = ball.y;
            
            startSpeed = 5;
            setTheta();
            ball.setSpeed(theta, startSpeed);
            
            ball2.setSpeed(theta, startSpeed * 5);
            
            paddleSpeed = 15;
            
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
            g.fillOval(ball2.x, ball2.y, ball2.w, ball2.h);
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
            input = gc.getInput();         
            
            if(start){//game start
                ball.x += ball.xSpeed;
                ball.y += ball.ySpeed;
                //left paddle collision
                if(ball.x <= (p1.x + p1.w) && ball.y <= (p1.y + p1.h) && (ball.y + ball.h) >= (p1.y)){
                    hit.play();
                    start2 = true;
                    if(ball.speed < 20){
                        ball.speed++;
                        ball2.speed = ball.speed * 5;
                    }
                    theta = getBounceTheta(ballPos, p1Pos, ball.w, p1.w);
                    ball.setSpeed(theta, ball.speed);
                    
                    ball2.x = ball.x;
                    ball2.y = ball.y;
                    
                    ball2.setSpeed(theta, ball2.speed);
                  }
                //left wall collision ball1
                if(ball.x <= minWidth){
                    splat.play();
                    start = false;
                    setTheta();
                    ball.x = 320 - ball.w;
                    ball.y = 240 - ball.h;
                    ball.setSpeed(theta, startSpeed);

                    start2 = false;
                    ball2.x = ball.x;
                    ball2.y = ball.y;
                    ball.setSpeed(theta, startSpeed * 5);
                    score2++;

                }

                //right paddle collision
                if(ball.x + ball.w >= (p2.x) && ball.y <= (p2.y + p2.h) && (ball.y + ball.h) >= (p2.y)){
                    hit.play();
                    start2 = false;
                    if(ball.speed < 20){
                        ball.speed++;
                        ball2.speed = ball.speed * 5;
                    }
                    //theta =  atan((ballPos - p2Pos)/((p2.w/2) + (ball.w/2)));
                    theta = getBounceTheta(ballPos, p2Pos, ball.w, p2.w);
                    ball.setSpeed(theta, ball.speed);
                    ball.xSpeed = -ball.xSpeed;

                    ball2.x = ball.x;
                    ball2.y = ball.y;
                    ball2.setSpeed(theta, ball2.speed);
                }
                //right wall collision ball1
                if(ball.x + ball.w >= maxWidth){
                    splat.play();
                    start = false;
                    start2 = false;
                    setTheta();
                    ball.x = 320 - ball.w;
                    ball.y = 240 - ball.h;
                    ball.setSpeed(theta, startSpeed);

                    ball2.x = ball.x;
                    ball2.y = ball.y;
                    ball2.setSpeed(theta, startSpeed * 5);
                    score1++;
                }
                //BALL 2 collision right wall collision
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
                
                //top wall collision
                if(ball.y <= maxHeight){
                    bounce.play(1,0.1F);
                    ball.ySpeed = -ball.ySpeed;
                }
                //botton wall collision
                if(ball.y + ball.h >= minHeight){
                    bounce.play(1,0.1F);
                    ball.ySpeed = -ball.ySpeed;
                }
                //same for ball 2
                if(ball2.y <= maxHeight){
                    ball2.ySpeed = -ball2.ySpeed;
                }
                //botton wall collision
                if(ball2.y + ball.h >= minHeight){
                    ball2.ySpeed = -ball2.ySpeed;
                }
            }
            else{
                //start
                if(input.isKeyPressed(Input.KEY_SPACE)){
                    start = true;
                    start2 = true;
                }
            }
                
            
            // ball 2 
            if(start2){
                ball2.x += ball2.xSpeed;
                ball2.y += ball2.ySpeed;
                //BALL 2 RIGHT WALL COLLISION 
                if(ball2.x + ball2.w >= maxWidth){
                    start2 = false;
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
        
        public void setTheta(){
            Random rand = new Random();
            this.theta = rand.nextDouble() * 360;
            //make sure ball doesnt go straight up and down
            if((theta >= 70 && theta <= 110) || (theta >= 250 && theta <= 290)){
                theta = 0;
            }
            theta = Math.toRadians(theta);
        }
        
        public double getBounceTheta(double ballPosition, double paddlePosition,  double ballWidth, double paddleWidth){
            double bounceTheta;
            bounceTheta =  atan((ballPosition - paddlePosition)/((paddleWidth/2) + (ballWidth/2)));
            return bounceTheta;
        }

	// Returning 'ID' from class 'MainMenu'
	@Override
	public int getID() {
		return Game2.ID;
	}
        public void keyReleased(int key, char c){
            if(key == Input.KEY_1){          
                start = false;
                start2 = false;
                ball.x = 320 - ball.w;
                ball.y = 240 - ball.h;
                setTheta();
                ball.setSpeed(theta, startSpeed);
                ball2.setSpeed(theta, startSpeed * 5);
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
