/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;
import java.lang.Math;
import static java.lang.Math.atan;
import static java.lang.Math.tan;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game5 extends BasicGameState{
    // ID we return to class 'Application'
    public static final int ID = 5;
    private StateBasedGame game;
    public static Image background;
    public Ball ball;
    public Paddle p1,p2;
    public Input input;
    public boolean start = false;
    public double ballSpeed;
    public double xSpeed;
    public double ySpeed;
    public double startSpeed;
    public double paddleSpeed;
    public int maxHeight;
    public int minHeight;
    public int minWidth;
    public int maxWidth;
    public double ballPos, p1Pos, p2Pos;
    public double theta;
    public int score1;
    public int score2;
    public Sound hit, bounce, splat;
    public DemoPower power;
    public boolean show, stop, invert, hide;
    public Timer timer;
    public float powerW, powerH;
    public int powerX, powerY;


    // init-method for initializing all resources
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        background = new Image("bg1.png");
        minHeight = 480;
        maxHeight = 0;
        minWidth = 0;
        maxWidth = 640;
        timer = new Timer();

        hit = new Sound("ballhit.wav");
        bounce = new Sound("bounce.wav");
        splat = new Sound("splat.wav");

        Image i = new Image("ball2.png");
        ball = new Ball(i);
        ball.w = 20;
        ball.h = 20;
        ball.x = 320 - ball.w;
        ball.y = 240 - ball.h;

        startSpeed = 5;
        setTheta();
        ball.setSpeed(theta, startSpeed);

        /*ballSpeed = startSpeed;
        ySpeed = ballSpeed * Math.sin(theta);
        xSpeed = ballSpeed * Math.cos(theta);*/

        paddleSpeed = 15;

        Image k = new Image("paddle.png");    
        p1 = new Paddle(k);                
        p1.w = 20;
        p1.h = 125;
        p1.x = minWidth;
        p1.y = (minHeight /2) - (p1.h / 2);

        p2 = new Paddle(k);
        p2.w = 20;
        p2.h = 125;
        p2.x = maxWidth - p2.w;
        p2.y = (minHeight / 2) - (p2.h / 2);    

        ballPos = ball.y + (ball.h / 2);
        p1Pos = p1.y + (p1.h / 2);
        p2Pos = p2.y + (p2.h / 2);

        score1 = 0;
        score2 = 0;

        Image q = new Image("DemoPowerBox1.png");
        Image w = new Image("DemoPowerBox2.png");
        Image e = new Image("DemoPowerBox3.png");
        Image r = new Image("DemoPowerBox4.png");
        powerW = 50;
        powerH = 480;
        powerX = 200;
        powerY = 0;
        power = DemoPower.getInstance();
        power.setImages(q,w,e,r);
        power.setPower(powerX, powerY, powerW, powerH);
        show = true;
        stop = false;
        invert = false;
        hide = false;
    }

    // render-method for all the things happening on-screen
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background, 0, 0);
        g.drawString("Player VS Player", 250, 10);
        g.drawString("Press 1 to return to Main Menu", 10, 450);
        if(hide){
            if(ball.x >= 150 && ball.x <=490){
                g.drawImage(ball.i, ball.x, ball.y);
            }
        }
        else{
            g.drawImage(ball.i, ball.x, ball.y);
        }
        g.drawImage(p1.i, p1.x, p1.y);
        g.drawImage(p2.i, p2.x, p2.y);
        g.drawString(Integer.toString(score1), 200, 50);
        g.drawString(Integer.toString(score2), 450, 50);
        if(show){
            //g.fillRect(power.x, power.y, power.w, power.h);
            g.drawImage(power.i, power.x, power.y);
        }    
    }

    // update-method with all the magic happening in it
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
        ballPos = ball.y + (ball.h / 2);
        p1Pos = p1.y + (p1.h / 2);
        p2Pos = p2.y + (p2.h / 2);
        Random rand = new Random();
        input = gc.getInput();         

        if(start){//game start

            if(!stop){
                ball.x += ball.xSpeed;
                ball.y += ball.ySpeed;
            }    
            //left paddle collision
            if(ball.x <= (p1.x + p1.w) && ball.y <= (p1.y + p1.h) && (ball.y + ball.h) >= (p1.y)){
                hit.play();
                if(ball.speed < 20){
                    ball.speed++;
                }
                theta =  getBounceTheta(ballPos, p1Pos, ball.w, p1.w);
                ball.setSpeed(theta, ball.speed);
              }
            //left wall collision
            if(ball.x <= minWidth){
                splat.play();
                start = false;
                resetBall(ball);
                setTheta();
                ball.setSpeed(theta, startSpeed);
                score2++;
            }

            //right paddle collision
            if(ball.x + ball.w >= (p2.x) && ball.y <= (p2.y + p2.h) && (ball.y + ball.h) >= (p2.y)){
                hit.play();
                if(ball.speed < 20){
                    ball.speed++;
                }
                theta =  getBounceTheta(ballPos, p2Pos, ball.w, p2.w);
                ball.setSpeed(theta, ball.speed);
                ball.xSpeed = -ball.xSpeed;
            }
            //right wall collision 
            if(ball.x + ball.w >= maxWidth){
                splat.play();
                start = false;
                resetBall(ball);
                setTheta();
                ball.setSpeed(theta, startSpeed);
                score1++;
            }

            //top wall collision
            if(ball.y <= maxHeight){
                bounce.play(1,0.05F);
                ball.ySpeed = -ball.ySpeed;
            }
            //botton wall collision
            if(ball.y + ball.h >= minHeight){
                bounce.play(1,0.05F);
                ball.ySpeed = -ball.ySpeed;
            }
            //power collision and long logic
            if(show){
                if(ball.x + ball.w >= power.x && ball.x <= power.x + power.w && ball.y + ball.h >= power.y && ball.y <= power.y + power.h){
                   revertPower();
                    try {
                        doPower();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game4.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   power.setPower(powerX, powerY, powerW, powerH);
                   show = false;
                   timer.schedule(new TimerTask(){
                        @Override
                        public void run(){
                           show = true; 
                        }
                    },4*1000);
                }
            }    


        }else{
            if(input.isKeyPressed(Input.KEY_SPACE)){
            start = true;
            }
        }

        if(invert){//INVERTED
            //Up left paddle
            if(input.isKeyDown(Input.KEY_A)){
                if(p1.y >= maxHeight){
                    p1.y += -paddleSpeed;
                }
            }
            //Down left paddle
            if(input.isKeyDown(Input.KEY_Q)){
                if(p1.y + p1.h <= minHeight){
                    p1.y += paddleSpeed;
                }
            }
            //Up right paddle
            if(input.isKeyDown(Input.KEY_DOWN)){
                if(p2.y >= maxHeight){
                    p2.y += -paddleSpeed;
                }
            }
            //Down right paddle
            if(input.isKeyDown(Input.KEY_UP)){
                if(p2.y + p2.h <= minHeight){
                    p2.y += paddleSpeed;
                }
            }
        }
        else{//NORMAL
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

    }

    public void resetBall(Ball ball){
        ball.x = 320 - ball.w;
        ball.y = 240 - ball.h;
    }

    public void setTheta(){
        Random rand = new Random();
        this.theta = rand.nextDouble() * 360;
        //make sure ball doesnt go straight up and down
        if((theta >= 70 && theta <= 110) || (theta >= 250 && theta <= 290)){
            theta = 0;
        }
        theta  = Math.toRadians(theta);
    }

    public double getBounceTheta(double position1, double position2,  double radius1, double radius2){
        double bounceTheta;
        bounceTheta =  atan((position1 - position2)/((radius1/2) + (radius2/2)));
        return bounceTheta;
    }

    public void doPower() throws SlickException, InterruptedException{
        Image k = new Image("smallPaddle.png");
        switch(power.id){
            //smaller paddles
            case 1: float tempX = p1.x;
                    float tempY = p1.y - (p1.h/2);
                    p1 = new Paddle(k);                
                    p1.w = 20;
                    p1.h = 70;
                    p1.x = tempX;
                    p1.y = tempY + (p1.h/2);

                    tempX = p2.x;
                    tempY = p2.y - (p2.h/2);
                    p2 = new Paddle(k);
                    p2.w = 20;
                    p2.h = 70;
                    p2.x = tempX;
                    p2.y = tempY + (p2.h/2);
                    break;
            //stop ball and reset theta    
            case 2: stop = true;
                    timer.schedule(new TimerTask(){
                        @Override
                        public void run(){
                           stop = false; 
                        }
                    },2*1000);
                    setTheta();
                    ball.setSpeed(theta, ball.speed);
                    break;
            //invert controls    
            case 3: invert = true;
                    break; 
            case 4: hide = true;
                    break;   
        }
    }

    public void revertPower() throws SlickException{
        Image k = new Image("paddle.png");
        switch(power.past){
            case 1: float tempX = p1.x;
                    float tempY = p1.y - (p1.h/2);
                    p1 = new Paddle(k);                
                    p1.w = 20;
                    p1.h = 125;
                    p1.x = tempX;
                    p1.y = tempY + (p1.h/2);

                    tempX = p2.x;
                    tempY = p2.y - (p2.h/2);
                    p2 = new Paddle(k);
                    p2.w = 20;
                    p2.h = 125;
                    p2.x = tempX;
                    p2.y = tempY + (p2.h/2);
                    break;

            case 2: break;
            case 3: invert = false;
                    break;
            case 4: hide = false;
                    break;   
        }
    }    

    // Returning 'ID' from class 'MainMenu'
    @Override
    public int getID() {
            return Game5.ID;
    }

    public void keyReleased(int key, char c){
        if(key == Input.KEY_1){          
            start = false;
            ball.x = 320 - ball.w;
            ball.y = 240 - ball.h;
            setTheta();
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