
package pong;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Pong extends StateBasedGame {

    // Game state identifiers
    public static final int MAINMENU     = 0;
    public static final int GAME         = 1; //player vs player
    public static final int GAME2        = 2; //plaver vs AI
    public static final int GAME3        = 3; //4 player
    public static final int GAME4        = 4; //pvp powerups

    // Application Properties
    public static final int WIDTH   = 640;
    public static final int HEIGHT  = 480;
    public static final int FPS     = 60;
    public static final double VERSION = 1.0;

    // Class Constructor
    public Pong(String appName) {
        super(appName);
    }

    // Initialize your game states (calls init method of each gamestate, and set's the state ID)
    public void initStatesList(GameContainer gc) throws SlickException {
        // The first state added will be the one that is loaded first, when the application is launched       
        this.addState(new MainMenu());
        this.addState(new Game());
        this.addState(new Game2());
        this.addState(new Game3());
        this.addState(new Game4());
    }

    // Main Method
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Pong("My Game v" + VERSION));
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(false);
            app.start();
            
        } 
        catch(SlickException e) {
            e.printStackTrace();
        }
    }
}
