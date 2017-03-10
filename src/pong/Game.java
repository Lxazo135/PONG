
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

public class Game extends BasicGameState{
    // ID we return to class 'Application'
	public static final int ID = 1;
        private StateBasedGame game;
        public static Image background;
        public Ball ball;
        public Paddle p1,p2;
        public Input input;
        public boolean start = false;
        public boolean left;
        public boolean up;
        public double xSpeed;
        public double ySpeed;
        public double paddleSpeed;
        public int maxHeight;
        public int minHeight;
        public int minWidth;
        public int maxWidth;
        public int ballPos, p1Pos, p2Pos;
        public double theta;
        
	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
            this.game = sbg;
            background = new Image("bg1.png");
            left = false;
            minHeight = 480;
            maxHeight = 0;
            minWidth = 0;
            maxWidth = 640;
            xSpeed = 3;
            paddleSpeed = 15;

            ball = new Ball();
            ball.w = 20;
            ball.h = 20;
            ball.x = 320 - ball.w;
            ball.y = 240 - ball.h;

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
	}

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
            g.drawImage(background, 0, 0);
            g.drawString("PONG", 300, 10);
            g.fillOval(ball.x, ball.y, ball.w, ball.h);
            g.drawImage(ball.i, ball.x, ball.y);
            g.fillRoundRect(p1.x, p1.y, p1.w, p1.h, 10);
            g.drawImage(p1.i, p1.x, p1.y);
            g.fillRoundRect(p2.x, p2.y, p2.w, p2.h, 10);
            g.drawImage(p2.i, p2.x, p2.y);
	}

	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
            ballPos = ball.y + (ball.h / 2);
            p1Pos = p1.y + (p1.h / 2);
            p2Pos = p2.y + (p2.h / 2);
            
            input = gc.getInput();         
            
            if(start){//game start
                if(left){//ball going left
                    ball.x += -xSpeed;
                    ball.y += ySpeed;
                    //left paddle collision
                    if(ball.x <= (p1.x + p1.w) && ball.y <= (p1.y + p1.h) && (ball.y + ball.h) >= (p1.y)){
                        left = false;
                        if(xSpeed < 20){
                            xSpeed += 1;
                        }
                        theta =  atan((ballPos - p1Pos)/((p1.w/2) + (ball.w/2)));
                        ySpeed = tan(theta) * xSpeed;
                      }
                    //left wall collision
                    if(ball.x <= minWidth){
                        start = false;
                        ball.x = 320 - ball.w;
                        ball.y = 240 - ball.h;
                        xSpeed = 3;
                        ySpeed = 0;
                    }
                }//ball going right
                else{
                    ball.x += xSpeed;
                    ball.y += ySpeed;
                    //right paddle collision
                    if(ball.x + ball.w >= (p2.x) && ball.y <= (p2.y + p2.h) && (ball.y + ball.h) >= (p2.y)){
                        left = true;
                        if(xSpeed < 20){
                            xSpeed += 1;
                        }
                        theta =  atan((ballPos - p2Pos)/((p2.w/2) + (ball.w/2)));
                        ySpeed = tan(theta) * xSpeed;
                    }
                    //right wall collision 
                    if(ball.x + ball.w >= maxWidth){
                        start = false;
                        ball.x = 320 - ball.w;
                        ball.y = 240 - ball.h;
                        xSpeed = 3;
                        ySpeed = 0;
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
            }else{
                if(input.isKeyPressed(Input.KEY_SPACE)){
                start = true;
                left = !left;
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
            //Up right paddle
            if(input.isKeyDown(Input.KEY_UP)){
                if(p2.y >= maxHeight){
                    p2.y += -paddleSpeed;
                }
            }
            //Down right paddle
            if(input.isKeyDown(Input.KEY_DOWN)){
                if(p2.y + p2.h <= minHeight){
                    p2.y += paddleSpeed;
                }
            }
            
            
	}

	// Returning 'ID' from class 'MainMenu'
	@Override
	public int getID() {
		return Game.ID;
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
                GameState target = game.getState(MainMenu.ID);
                game.enterState(MainMenu.ID);
            }
        }
}
