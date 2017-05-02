
package pong;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class GameOver extends BasicGameState {
    // ID we return to class 'Application'
	public static final int ID = 1;
        private StateBasedGame game;
        public static Image background;
        public Image menuImage, againImage;
        public startButton menu, again; 
        public Input input;
        public static String winner;
        public static int currentID;
        
	// init-method for initializing all resources
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
            this.game = sbg;
            background = new Image("GameOverScreen.png");
            
            menuImage = new Image("main.png");
            menu = new startButton(menuImage, 300, 150);
            menu.setXY(320 - (menu.w/2), 480 - menu.h);
            
            againImage = new Image("again.png");
            again = new startButton(againImage, 300, 150);
            again.setXY(320 - (again.w/2), 480 - (2*again.h)); 
            
	}

	// render-method for all the things happening on-screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
            g.drawImage(background, 0, 0);
            g.drawString(winner + " Wins!", 275, 150);
            g.drawImage(menu.i, menu.x, menu.y);
            g.drawImage(again.i, again.x, again.y);
            
	}

	// update-method with all the magic happening in it
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
            
	}

	// Returning 'ID' from class 'MainMenu'
	@Override
	public int getID() {
		return GameOver.ID;
	}
        
        public void keyReleased(int key, char c){
            if(key == Input.KEY_1){
                game.enterState(MainMenu.ID);
            }
        }
        
        public void mouseReleased(int button, int x, int y){
            if(x > menu.x && x < menu.x + menu.w && y > menu.y && y < menu.y + menu.h){
                game.enterState(MainMenu.ID);
            }
            if(x > again.x && x < again.x + again.w && y > again.y && y < again.y + again.h){
                game.enterState(currentID);
            }
        }
}
