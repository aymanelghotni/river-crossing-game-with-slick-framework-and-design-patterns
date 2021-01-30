package Crosser;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Animal extends Crosser {
	public Animal(int weight)
	{
		this.weight=weight;
		this.label="Animal "+weight+"KG";
		this.canSail=false;
		this.eatingRank=-1;
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
