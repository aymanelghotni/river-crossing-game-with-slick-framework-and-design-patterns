package Crosser;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Person extends Crosser {
	public Person(int weight)
	{
		this.weight=weight;
		this.label="Person "+weight+"KG";
		this.eatingRank=-1;
		this.canSail=true;
		try
		{
			images=new BufferedImage[] {ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data\\farmer2.png")),ImageIO.read(new File("C:\\Users\\Ayman\\Desktop\\Java workspace\\River Crossing Game\\data\\farmer1.png"))};

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
