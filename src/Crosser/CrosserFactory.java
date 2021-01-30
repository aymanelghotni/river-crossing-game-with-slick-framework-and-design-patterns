package Crosser;

public class CrosserFactory {
	Crosser makeCrosser(String name, int weight)
	{
		if(name.equalsIgnoreCase("Farmer"))
			return new Farmer();
		else if(name.equalsIgnoreCase("Wolf"))
			return new Wolf();
		else if(name.equalsIgnoreCase("Sheep"))
			return new Sheep();
		else if(name.equalsIgnoreCase("Cabbage"))
			return new Cabbage();
		else if(name.equalsIgnoreCase("Person"))
			return new Person(weight);
		else if(name.equalsIgnoreCase("Animal"))
			return new Animal(weight);
		else
			return null;
	}
}
