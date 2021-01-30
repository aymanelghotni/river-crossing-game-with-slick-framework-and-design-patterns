package Crosser;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Cabbage extends Crosser{

	public Cabbage() {
		this.weight=0;
		this.label="Cabbage";
		this.eatingRank=1;
		this.canSail=false;
		try
		{
			images=new BufferedImage[] {ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data\\cabbage2.png")),ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data\\cabbage1.png"))};

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
