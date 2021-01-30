package Crosser;
// Memento Design Pattern Tutorial

import java.util.ArrayList;

class Caretaker {
   
	// Where all mementos are saved
	
	ArrayList<Memento> savedCrossers = new ArrayList<Memento>();

	// Adds memento to the ArrayList
	
	public void addMemento(Memento m) { savedCrossers.add(m); }
   
	// Gets the memento requested from the ArrayList
	
	public Memento getMemento(int index) { return savedCrossers.get(index); }
} 