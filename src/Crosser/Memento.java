package Crosser;

import java.util.ArrayList;

// Memento Design Pattern
// Used stores an objects state at a point in time
// so it can be returned to that state later. It
// simply allows you to undo/redo changes on an Object

public class Memento {
	
	// The article stored in memento Object
	
	private ArrayList<ICrosser> lcm = new ArrayList<ICrosser>();

	// Save a new article String to the memento Object
	
	public Memento(ArrayList<ICrosser> articleSave) { lcm = articleSave; }
	
	// Return the value stored in article 
	
	public ArrayList<ICrosser> getSavedCrossers() { return lcm; }
	
}