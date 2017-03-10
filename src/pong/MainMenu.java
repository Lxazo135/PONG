package pong;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.gui.MouseOverArea;

public class MainMenu extends BasicGameState {
    // ID we return to class 'Application'
	public static final int ID = 0;
        private StateBasedGame game;
        public static Image background;
        public startButton start;
        public Input input;
	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
            this.game = sbg;
            background = new Image("bg2.png");
            start = new startButton();
            start.x = 320 - (start.w/2);
            start.y = 240 - (start.h/2);
	}

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
            g.drawImage(background, 0, 0);
            g.fillRoundRect(start.x, start.y, start.w, start.h, 10);
            g.drawImage(start.i, start.x, start.y);
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
                GameState target = game.getState(Game.ID);
                game.enterState(Game.ID);
            }
        }
        
        public void mouseReleased(int button, int x, int y){
            if(x > start.x && x < start.x + start.w && y > start.y && y < start.y + start.h){
                GameState target = game.getState(Game.ID);
                game.enterState(Game.ID);
            }
        }
}
