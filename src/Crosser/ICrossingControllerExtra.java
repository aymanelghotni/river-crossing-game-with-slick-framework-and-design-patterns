package Crosser;

import java.util.List;

public interface ICrossingControllerExtra {
	public void addToLeft(ICrosser crosser);
	public void removeFromLeft(ICrosser crosser);
	public void addToRight(ICrosser crosser);
	public void removeFromRight(ICrosser crosser);
	public List<ICrosser> getBoatCrossers();
	//public int getLevel();
}
