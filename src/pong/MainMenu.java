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
        public Image buttonPVP;
        public Image buttonPVAI;
        public startButton start;
        public startButton start2;
        public Input input;
	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
            this.game = sbg;
            background = new Image("bg2.png");
            
            buttonPVP = new Image("pvp.jpg");
            start = new startButton(buttonPVP, 300, 150);
            start.x = 160 - (start.w/2);
            start.y = 240 - (start.h/2);
            
            buttonPVAI = new Image("pvai.png");
            start2 = new startButton(buttonPVAI, 300, 150);
            start2.x = 480 - (start2.w/2);
            start2.y = 240 - (start.h/2);
	}

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
            g.drawImage(background, 0, 0);
            g.fillRoundRect(start.x, start.y, start.w, start.h, 10);
            g.drawImage(start.i, start.x, start.y);
            g.fillRoundRect(start2.x, start2.y, start2.w, start2.h, 10);
            g.drawImage(start2.i, start2.x, start2.y);
            g.drawString("MAIN MENU", 300, 10);
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
                game.enterState(Game.ID);
            }
            if(key == Input.KEY_3){
                game.enterState(Game2.ID);
            }
        }
        
        public void mouseReleased(int button, int x, int y){
            if(x > start.x && x < start.x + start.w && y > start.y && y < start.y + start.h){
                game.enterState(Game.ID);
            }
            
            if(x > start2.x && x < start2.x + start2.w && y > start2.y && y < start2.y + start2.h){
                game.enterState(Game2.ID);
            }
        }
}
