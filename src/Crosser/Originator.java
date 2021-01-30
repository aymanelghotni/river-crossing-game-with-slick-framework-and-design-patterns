package Crosser;
// Memento Design Pattern

import java.util.ArrayList;

public class Originator{
	
	private ArrayList<ICrosser> lco = new ArrayList<ICrosser>();

	// Sets the value for the article
	
	public void set(ArrayList<ICrosser> newArticle) { 
		System.out.println("From Originator: Current Version of left crossers list\n"+newArticle+ "\n");
	    this.lco = newArticle; 
	}

	// Creates a new Memento with a new article
	
	public Memento storeInMemento() { 
	    System.out.println("From Originator: Saving to Memento");
	    return new Memento(lco); 
	}
	   
	// Gets the article currently stored in memento
	
	public ArrayList<ICrosser> restoreFromMemento(Memento memento) {
		   
		lco = memento.getSavedCrossers(); 
	       
		System.out.println("From Originator: Previous Article Saved in Memento\n"+lco + "\n");
		
		return lco;
	   
	}
	
}