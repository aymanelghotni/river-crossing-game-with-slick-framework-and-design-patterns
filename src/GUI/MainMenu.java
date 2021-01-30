package GUI;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.BufferedImageUtil;

import Crosser.GameController;

import org.lwjgl.input.Mouse;
public class MainMenu extends BasicGameState {

	
	Image background;
	Image newgame;
	Image loadgame;
	String mouse="No input";
	GameController game=new GameController(null);
	
	public MainMenu(int state) {
		
	}
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		background=new Image("data/backgroundWhite.png");
		newgame= new Image("data/btnNewGame.jpg");
		loadgame=new Image("data/btnLoadGame.jpg");
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw();
		newgame.draw(150,100,1f);
		loadgame.draw(150,350,1f);
		
		
	}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		int xpos=Mouse.getX();
		int ypos=Mouse.getY();
		Input input=gc.getInput();
		mouse="Mouse pos: "+xpos+", "+ypos;
		if(xpos>150 && xpos<630 && ypos>320 && ypos<495)
		{
			if(input.isMousePressed(0))
			{
				sbg.enterState(0);
			}
		}
		if(xpos>150 && xpos<630 && ypos>70 && ypos<245)
		{
			if(input.isMouseButtonDown(0))
			{
				game.loadGame();
				if(game.getLevel()==1)
				{
					sbg.addState(new PlayLoaded(4));
					sbg.getState(4).init(gc, sbg);
					sbg.enterState(4);
					
				}
				
				if(game.getLevel()==2)
				{
					sbg.addState(new Play2Loaded(5));
					sbg.getState(5).init(gc, sbg);
					sbg.enterState(5);
				}
			}
		}
	}

	
	public int getID() {
		return 2;
	}

}
