package GUI;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

	public static final String gamename="River Crossing Puzzle";
	public static final int menu=0;
	public static final int play=1;
	public static final int mainmenu=2;
	public static final int play2=3;
	public static final int playLoaded=4;
	public static final int play2Loaded=5;
	
	public Game(String gamename) {
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new MainMenu(mainmenu));
		this.addState(new Play2(play2));
		
		//this.addState(new PlayLoaded(playLoaded));
		//this.addState(new Play2Loaded(play2Loaded));
	}
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(mainmenu).init(gc, this);
		this.getState(play2).init(gc, this);
		
		
		this.enterState(mainmenu);
	}
	public static void main(String[] args) {
		
		AppGameContainer appgc;
		try
		{
			appgc=new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(900, 600, false);
			appgc.start();
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}

}
