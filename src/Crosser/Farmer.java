package Crosser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Farmer extends Crosser {
	public Farmer() {
		this.weight=0;
		this.label="Farmer";
		this.eatingRank=0;
		this.canSail=true;
		try
		{
			images=new BufferedImage[] {ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data\\farmer2.png")),ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data\\farmer1.png"))};
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
}
