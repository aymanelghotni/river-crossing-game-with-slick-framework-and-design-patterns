package Crosser;

import java.util.ArrayList;

public class Omar {
	Caretaker caretaker = new Caretaker();

	   // The originator sets the value for the article,
	   // creates a new memento with a new article, and 
	   // gets the article stored in the current memento
	   
	   Originator originator = new Originator();
	   
	   int saveFiles = 0, currentArticle = 0;
	   ICrossingStrategy level = new Scene1();
	   GameController controller = new GameController(level);
	   
	   public void save() {
		   ArrayList<ICrosser> leftBank = new ArrayList<ICrosser>();
			leftBank = (ArrayList<ICrosser>) controller.getCrossersOnLeftBank(); 
			// Set the value for the current memento
			
			originator.set(leftBank);
			
			// Add new article to the ArrayList
			
			caretaker.addMemento( originator.storeInMemento() );
			
			// saveFiles monitors how many articles are saved
			// currentArticle monitors the current article displayed
			
			saveFiles++;
			currentArticle++;
			
			System.out.println("Save Files " + saveFiles);
			
			// Make undo clickable
	   }
	   public void undo() {
		   if(currentArticle >= 1){
				
				// Decrement to the current article displayed
				
				currentArticle--;
				
				// Get the older article saved and display it in JTextArea
			
				ArrayList<ICrosser> textBoxString = originator.restoreFromMemento( caretaker.getMemento(currentArticle) );
				
				//theArticle.setText(textBoxString.toString());
				
				// Make Redo clickable
controller.setLeftCrossers(textBoxString);
				
				ArrayList<ICrosser> rc = new ArrayList<ICrosser>();
				rc.addAll(level.getInitialCrossers());
				for(int i =3 ; i>-1;i++)
				{
					if(textBoxString.get(i) instanceof Cabbage)
					{
						rc.remove(3);
					}
					if(textBoxString.get(i) instanceof Sheep)
					{
						rc.remove(2);
					}
					if(textBoxString.get(i) instanceof Wolf)
					{
						rc.remove(1);
					}
					if(textBoxString.get(i) instanceof Farmer)
					{
						rc.remove(0);
					}
				}
				controller.setRightCrossers(rc);
	   }
	   }
	   public void redo() {
		   if((saveFiles - 1) > currentArticle){
				
				// Increment to the current article displayed
				
				currentArticle++;
				
				// Get the newer article saved and display it in JTextArea
			
				ArrayList<ICrosser> textBoxString = originator.restoreFromMemento( caretaker.getMemento(currentArticle) );
				controller.setLeftCrossers(textBoxString);
				
				ArrayList<ICrosser> rc = new ArrayList<ICrosser>();
				rc.addAll(level.getInitialCrossers());
				for(int i =3 ; i>-1;i++)
				{
					if(textBoxString.get(i) instanceof Cabbage)
					{
						rc.remove(3);
					}
					if(textBoxString.get(i) instanceof Sheep)
					{
						rc.remove(2);
					}
					if(textBoxString.get(i) instanceof Wolf)
					{
						rc.remove(1);
					}
					if(textBoxString.get(i) instanceof Farmer)
					{
						rc.remove(0);
					}
				}
				controller.setRightCrossers(rc);
				//theArticle.setText(textBoxString.toString());
				
				// Make undo clickable
				
	   }
}
}
