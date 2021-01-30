package Crosser;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Sheep extends Crosser{
	public Sheep() {
		this.weight=0;
		this.label="Sheep";
		this.eatingRank=2;
		this.canSail=false;
		try
		{
			images=new BufferedImage[] {ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data\\sheep2.png")),ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data\\sheep1.png"))};

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
