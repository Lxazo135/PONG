package pong;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.GameState;

public class MainMenu extends BasicGameState {
    // ID we return to class 'Application'
	public static final int ID = 0;
        private StateBasedGame game;
        public static Image background;
        public Image buttonPVP, buttonPVAI, buttonPower,button4Player, pongScreen; 
        public startButton start, start2, start3, start4;
        public Input input;
	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
            this.game = sbg;
            background = new Image("bg2.png");
            
            buttonPVP = new Image("pvp.png");
            start = new startButton(buttonPVP, 300, 150);
            start.setXY(0, 0);
            
            buttonPVAI = new Image("pvai.png");
            start2 = new startButton(buttonPVAI, 300, 150);
            start2.setXY(640 - start2.w, 0);
            
            buttonPower = new Image("4player.png");
            start3 = new startButton(buttonPower, 300, 150);
            start3.setXY(0, 480 - start3.h);
            
            button4Player = new Image("power.png");
            start4 = new startButton(button4Player, 300, 150);
            start4.setXY(640 - start4.w, 480 - start4.h);
            
            pongScreen = new Image("PongScreen.png");
	}

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
            g.drawImage(background, 0, 0);
            g.drawImage(start.i, start.x, start.y);
            g.drawImage(start2.i, start2.x, start2.y);
            g.drawImage(start3.i, start3.x, start3.y);
            g.drawImage(start4.i, start4.x, start4.y);
            g.drawImage(pongScreen, 170, 160);
	}

	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
            
	}

	// Returning 'ID' from class 'MainMenu'
	@Override
	public int getID() {
		return MainMenu.ID;
	}
        
        public void keyReleased(int key, char c){
            if(key == Input.KEY_2){
                game.enterState(PlayerVsPlayer.ID);
            }
            if(key == Input.KEY_3){
                game.enterState(PlayerVsAI.ID);
            }
            if(key == Input.KEY_4){
                game.enterState(FourPlayer.ID);
            }
            if(key == Input.KEY_5){
                game.enterState(PowerUps.ID);
            }
            if(key == Input.KEY_6){
                game.enterState(PowerUpsDemo.ID);
            }
            if(key == Input.KEY_7){
                game.enterState(PlayerVsAIDemo.ID);
            }
            if(key == Input.KEY_0){
                game.enterState(GameOver.ID);
            }
        }
        
        public void mouseReleased(int button, int x, int y){
            if(x > start.x && x < start.x + start.w && y > start.y && y < start.y + start.h){
                game.enterState(PlayerVsPlayer.ID);
            }
            
            if(x > start2.x && x < start2.x + start2.w && y > start2.y && y < start2.y + start2.h){
                game.enterState(PlayerVsAI.ID);
            }
            
            if(x > start3.x && x < start3.x + start3.w && y > start3.y && y < start3.y + start3.h){
                game.enterState(FourPlayer.ID);
            }
            
            if(x > start4.x && x < start4.x + start4.w && y > start4.y && y < start4.y + start4.h){
                game.enterState(PowerUps.ID);
            }
        }
}
