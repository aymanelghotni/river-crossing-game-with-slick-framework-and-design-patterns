package GUI;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.BufferedImageUtil;
import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState{
	public String mouse="No input";
	public Menu(int state) {
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		g.drawString(mouse, 50, 50);
		g.drawString("Choose a level to play", 120, 80);
		Image level1=new Image("data/level1Menu.png");
		Image level2=new Image("data/level2Menu.png");
		level1.draw(50, 150,0.35f);
		level2.draw(400, 150,0.35f);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int xpos=Mouse.getX();
		int ypos=Mouse.getY();
		mouse="Mouse position x: "+xpos+" y: "+ypos;
		Input input=gc.getInput();
		
		if(xpos >50 && xpos <295 && ypos>285 && ypos<450)
			if(input.isMousePressed(0))
				sbg.enterState(1);
		if(xpos >400 && xpos <645 && ypos>285 && ypos<450)
			if(input.isMousePressed(0))
				sbg.enterState(3);
		
	}
	public int getID(){
		return 0;
	}
}
