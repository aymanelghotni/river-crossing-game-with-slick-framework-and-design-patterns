package Crosser;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Wolf extends Crosser {

	public Wolf() {
		this.label="Wolf";
		this.eatingRank=3;
		this.weight=0;
		this.canSail=false;
		try
		{
			images=new BufferedImage[] {ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data\\wolf2.png")),ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data\\wolf1.png"))};

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
}
