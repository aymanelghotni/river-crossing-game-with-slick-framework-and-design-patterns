package Crosser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.UnaryOperator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class GameController implements ICrossingController {

	private ICrossingStrategy currentLevel=null;
	private LinkedList<Command> undoableCommands=new LinkedList<Command>();
	private LinkedList<Command> redoableCommands=new LinkedList<Command>();
	private List<ICrosser> boatCrossers = new ArrayList<ICrosser>();
	private List<ICrosser> rightCrossers = new ArrayList<ICrosser>();
	private List<ICrosser> leftCrossers = new ArrayList<ICrosser>();

	private boolean isBoatOnTheLeft=false;
	private int moves=0;
	//CareTaker careTaker=new CareTaker();
	//Originator originator=new Originator();
	//State model=new State();
	private int flag=0;
	
	
	
	Caretaker caretaker = new Caretaker();

	   // The originator sets the value for the article,
	   // creates a new memento with a new article, and 
	   // gets the article stored in the current memento
	   
	   Originator originator = new Originator();
	   
	   int saveFiles = 0, currentArticle = 0;
	
	
	
	
	
	
	public GameController(ICrossingStrategy level) {
		currentLevel=level;
	}
	public void newGame(ICrossingStrategy gameStrategy) {
	
		leftCrossers=currentLevel.getInitialCrossers();
		isBoatOnTheLeft=true;
		moves=0;
	}

	@Override
	public void resetGame() {
		leftCrossers=currentLevel.getInitialCrossers();
		rightCrossers.clear();
		isBoatOnTheLeft=true;
		moves=0;
	}

	@Override
	public String[] getInstructions() {
		
		return currentLevel.getInstructions();
	}

	@Override
	public List<ICrosser> getCrossersOnRightBank() {
		return rightCrossers;
	}

	@Override
	public List<ICrosser> getCrossersOnLeftBank() {
		
		return leftCrossers;
	}

	@Override
	public boolean isBoatOnTheLeftBank() {
		return isBoatOnTheLeft;
	}

	@Override
	public int getNumberOfSails() {
		
		return moves;
	}

	@Override
	public boolean canMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
		
		return currentLevel.isValid(rightCrossers, leftCrossers, crossers);
	}

	@Override
	public void doMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
		
		if(canMove(crossers,fromLeftToRightBank))
		{
			/*model.setLeftBankCrossers(leftCrossers);
			model.setRightBankCrossers(rightCrossers);
			model.setBoatOnTheLeftBank(isBoatOnTheLeftBank());
			originator.setGame(model);
			careTaker.addToUndo(originator.saveGameMemento());*/			
			if(fromLeftToRightBank)
			{
					leftCrossers.removeAll(crossers);
					rightCrossers.addAll(crossers);	
			}
			else
			{
				
					leftCrossers.addAll(crossers);
					rightCrossers.removeAll(crossers);		
			}
			isBoatOnTheLeft=!isBoatOnTheLeft;
			boatCrossers=null;
			moves++;
			
		}
			
		
		
		
		
		
		 ArrayList<ICrosser> leftBank = new ArrayList<ICrosser>();
			leftBank = (ArrayList<ICrosser>) getCrossersOnLeftBank(); 
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

	@Override
	public boolean canUndo() {
		if (currentArticle<1){
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean canRedo() {
		 if((saveFiles - 1) > currentArticle) {
			return true;
		} else {
			return false;
		}
	}
	

	//@Override
	public void undo() {
	
		if(currentArticle >= 1){
			
			// Decrement to the current article displayed
			
			currentArticle--;
			
			// Get the older article saved and display it in JTextArea
		
			ArrayList<ICrosser> textBoxString = originator.restoreFromMemento( caretaker.getMemento(currentArticle) );
			
			//theArticle.setText(textBoxString.toString());
			
			// Make Redo clickable
setLeftCrossers(textBoxString);
			
			ArrayList<ICrosser> rc = new ArrayList<ICrosser>();
			rc=(ArrayList<ICrosser>) currentLevel.getInitialCrossers();
			for(int i =3 ; i>-1;i--)
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
			setRightCrossers(rc);
   }
		
		
	}

	@Override
	public void redo() {
		
		if((saveFiles - 1) > currentArticle){
			
			// Increment to the current article displayed
			
			currentArticle++;
			
			// Get the newer article saved and display it in JTextArea
		
			ArrayList<ICrosser> textBoxString = originator.restoreFromMemento( caretaker.getMemento(currentArticle) );
			setLeftCrossers(textBoxString);
			
			ArrayList<ICrosser> rc = new ArrayList<ICrosser>();
			rc.addAll(currentLevel.getInitialCrossers());
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
			setRightCrossers(rc);
			//theArticle.setText(textBoxString.toString());
			
			// Make undo clickable
			
   }
	
	}

	@Override
	public void saveGame() {
		try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("crossers");
            doc.appendChild(rootElement);
            // supercars element
            Element left = doc.createElement("CrossersOnLeft");
            rootElement.appendChild(left);
            Element right = doc.createElement("CrossersOnRight");
            rootElement.appendChild(right);
            Element noOfSails = doc.createElement("NumberOfDoneSails");
            rootElement.appendChild(noOfSails);
            Element posOfBoat = doc.createElement("PositionOfBoat");
            rootElement.appendChild(posOfBoat);
            Element level = doc.createElement("currentLevel");
            rootElement.appendChild(level);
            // setting attribute to element
            for (int i = 0; i < getCrossersOnLeftBank().size(); i++) {
                Element cname = doc.createElement("Crosser");
                Attr attrType = doc.createAttribute("type");
               //Element attr=null; 
                if (getCrossersOnLeftBank().get(i) instanceof Wolf) {
                    attrType.setValue("Wolf");
                } else if (getCrossersOnLeftBank().get(i) instanceof Sheep) {
                    attrType.setValue("Sheep");
                }  else if (getCrossersOnLeftBank().get(i) instanceof Cabbage) {
                    attrType.setValue("Cabbage");
                }
                else if (getCrossersOnLeftBank().get(i) instanceof Farmer) {
                    attrType.setValue("Farmer");
                } else if (getCrossersOnLeftBank().get(i) instanceof Person) {
                    attrType.setValue(Double.toString(getCrossersOnLeftBank().get(i).getWeight()));
                }
                  else if (getCrossersOnLeftBank().get(i) instanceof Animal) {
                    attrType.setValue("20.0");
                }
                
                  
                  /*else if (getCrossersOnLeftBank().get(i) instanceof Farmer4) {
                    attrType.setValue("Farmer4");
                }
                 else if (getCrossersOnLeftBank().get(i) instanceof Farmer5) {
                    attrType.setValue("Farmer5");
                }*/
                
                 
                cname.setAttributeNode(attrType);
//                cname.appendChild(doc.createTextNode("Ferrari 101"));
                left.appendChild(cname);
            }
           
              for (int i = 0; i < getCrossersOnRightBank().size(); i++) {
                Element cname = doc.createElement("Crosser");
                Attr attrType = doc.createAttribute("type");
              // Element attr=null; 
                if (getCrossersOnRightBank().get(i) instanceof Wolf) {
                    attrType.setValue("Wolf");
                }
               //else if (getCrossersOnRightBank().get(i) instanceof Carnivore) {
                 //   attrType.setValue("Carnivore");} 
                 else if (getCrossersOnRightBank().get(i) instanceof Sheep) {
                    attrType.setValue("Sheep");
                } else if (getCrossersOnRightBank().get(i) instanceof Farmer) {
                    attrType.setValue("Farmer");
                } else if (getCrossersOnRightBank().get(i) instanceof Cabbage) {
                    attrType.setValue("Cabbage");
                }
                else if (getCrossersOnRightBank().get(i) instanceof Person) {
                    attrType.setValue(Double.toString(getCrossersOnRightBank().get(i).getWeight()));
                }
                  else if (getCrossersOnRightBank().get(i) instanceof Animal) {
                    attrType.setValue("20.0");
                }
                
                
                /* else if (getCrossersOnRightBank().get(i) instanceof Farmer4) {
                    attrType.setValue("Farmer4");
                }
                 else if (getCrossersOnRightBank().get(i) instanceof Farmer5) {
                    attrType.setValue("Farmer5");
                }*/
                
                
                cname.setAttributeNode(attrType);
//                cname.appendChild(doc.createTextNode("Ferrari 101"));
                right.appendChild(cname);
            }
              
              
              Element cname = doc.createElement("NoOfBoatSails");
              Attr attrType = doc.createAttribute("number");
              attrType.setValue(Integer.toString(getNumberOfSails()));
              cname.setAttributeNode(attrType);
              noOfSails.appendChild(cname);
              
              
              Element cname1 = doc.createElement("PosOfBoat");
              Attr attrType1 = doc.createAttribute("position");
              if(isBoatOnTheLeftBank())
            	  attrType1.setValue("Left");
              else attrType1.setValue("Right");
              cname1.setAttributeNode(attrType1);
              posOfBoat.appendChild(cname1);
              
              Element cname2 = doc.createElement("Level");
              Attr attrType2 = doc.createAttribute("number");
              int flag=0;
              if(currentLevel instanceof Scene1)
              {
            	  flag=1;
              }
              else if(currentLevel instanceof Scene2)
              {
            	  flag=2;
              }
              attrType2.setValue(Integer.toString(flag));
              cname2.setAttributeNode(attrType2);
              level.appendChild(cname2);
            
            /*    for (int i = 0; i < .size(); i++) {
                Element cname = doc.createElement("Crosser");
                Attr attrType = doc.createAttribute("type");
//                Element attr=null; 
                if (boatriders.get(i) instanceof Carnivore) {
                    attrType.setValue("Carnivore");
                } else if (boatriders.get(i) instanceof Harbivore) {
                    attrType.setValue("Harbivore");
                } else if (boatriders.get(i) instanceof Farmer) {
                    attrType.setValue("Farmer");
                } else if (boatriders.get(i) instanceof Plant) {
                    attrType.setValue("Plant");
                }
                else if (boatriders.get(i) instanceof Farmer3) {
                    attrType.setValue("Farmer3");
                }
                  else if (boatriders.get(i) instanceof Farmer2) {
                    attrType.setValue("Farmer2");
                }
                 else if (boatriders.get(i) instanceof Farmer4) {
                    attrType.setValue("Farmer4");
                }
                 else if (boatriders.get(i) instanceof Farmer5) {
                    attrType.setValue("Farmer5");
                }
                cname.setAttributeNode(attrType);
//                cname.appendChild(doc.createTextNode("Ferrari 101"));
                riders.appendChild(cname);
            }
            
            */
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
           // attr.setValue("Ferrari");
            // supercar.setAttributeNode(attr);
            // carname element
//            Element carname = doc.createElement("carname");
//            Attr attrType = doc.createAttribute("type");
//            attrType.setValue("formula one");
//            carname.setAttributeNode(attrType);
//            carname.appendChild(doc.createTextNode("Ferrari 101"));
//            supercar.appendChild(carname);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("crossriver.xml"));
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void loadGame() {
		ArrayList<String> Lcrossers = new ArrayList<String>();
		ArrayList<String> Rcrossers = new ArrayList<String>();
		String boatPosition ;
		int BoatSails;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("crossriver.xml");
			NodeList CrossersOnLeftList = doc.getElementsByTagName("CrossersOnLeft");
			NodeList CrossersOnRightList = doc.getElementsByTagName("CrossersOnRight");

			
			NodeList NumberOfDoneSails = doc.getElementsByTagName("NumberOfDoneSails");
			Node sails= NumberOfDoneSails.item(0); 
			if(sails.getNodeType()==Node.ELEMENT_NODE) {
				Element s = (Element) sails;
				//String crosserType = crosser.getAttribute("type");
				NodeList number = s.getChildNodes();
				Node no = number.item(0);
				if(no.getNodeType()==Node.ELEMENT_NODE) {
					Element num = (Element) no;
					moves = Integer.parseInt( num.getAttribute("number"));
					System.out.println("boat sails " + moves);
					
			}
				
				
				NodeList levelNum = doc.getElementsByTagName("currentLevel");
				Node lev= levelNum.item(0); 
				if(sails.getNodeType()==Node.ELEMENT_NODE) {
					Element k = (Element) lev;
					//String crosserType = crosser.getAttribute("type");
					NodeList levNumber = k.getChildNodes();
					Node levelNumber = levNumber.item(0);
					if(levelNumber.getNodeType()==Node.ELEMENT_NODE) {
						Element nom = (Element) levelNumber;
						if(Integer.parseInt( nom.getAttribute("number"))==1)
						{
							currentLevel=new Scene1();
						}
						else if(Integer.parseInt( nom.getAttribute("number"))==2)
						{
							currentLevel=new Scene2();
						}
						System.out.println("current level " + moves);
						
				}
			}
				
				
				
				
				NodeList pob = doc.getElementsByTagName("PositionOfBoat");
				Node p= pob.item(0); 
				if(p.getNodeType()==Node.ELEMENT_NODE) {
					Element po = (Element) p;;
					//String crosserType = crosser.getAttribute("type");
					NodeList pos = po.getChildNodes();
					Node position = pos.item(0);
					if(position.getNodeType()==Node.ELEMENT_NODE) {
						Element poss = (Element) position;
						boatPosition = poss.getAttribute("position");
						if(boatPosition.equals("Left"))
						{
							isBoatOnTheLeft=true;
						}else isBoatOnTheLeft=false;
						System.out.println("boat position =" + boatPosition);
				}
				}
				
				
				for(int i = 0;i<CrossersOnRightList.getLength();i++)
			   {
				Node n = CrossersOnRightList.item(i);
				if(n.getNodeType()==Node.ELEMENT_NODE) {
					Element crosser = (Element) n;
					//String crosserType = crosser.getAttribute("type");
					NodeList crossersType = crosser.getChildNodes();
					for(int j = 0;j<crossersType.getLength();j++)
					{
						Node t = crossersType.item(j);
						if(t.getNodeType()==Node.ELEMENT_NODE) {
							Element type = (Element) t;
							String crosserType = type.getAttribute("type");
							//System.out.println(j);
							System.out.println(crosserType);
							if(crosserType.equals("Farmer")) {
								//Lcrossers.add("Farmer");
								
								rightCrossers.add(new Farmer());
							}
							else if (crosserType.equals("Wolf")) {
								//Lcrossers.add("Wolf");
								rightCrossers.add(new Wolf());
							}
							else if (crosserType.equals("Sheep")) {
								//Lcrossers.add("Sheep");
								rightCrossers.add(new Sheep());
							}
							else if (crosserType.equals("Cabbage")) {
								//Lcrossers.add("Cabbage");
								rightCrossers.add(new Cabbage());
							}
							else if (crosserType.equals("20.0")) {
								//Rcrossers.add("20");
								rightCrossers.add(new Animal(20));
							}
							else if (crosserType.equals("40.0")) {
								rightCrossers.add(new Person(40));
							}
							else if (crosserType.equals("60.0")) {
								rightCrossers.add(new Person(60));
							}
							else if (crosserType.equals("80.0")) {
								rightCrossers.add(new Person(80));
							}
							else if (crosserType.equals("90.0")) {
								rightCrossers.add(new Person(90));
							}
							
							
							
						}
					}
				}
			}
				
				
				
				for(int i = 0;i<CrossersOnLeftList.getLength();i++)
				   {
					Node n = CrossersOnLeftList.item(i);
					if(n.getNodeType()==Node.ELEMENT_NODE) {
						Element crosser = (Element) n;
						//String crosserType = crosser.getAttribute("type");
						NodeList crossersType = crosser.getChildNodes();
						for(int j = 0;j<crossersType.getLength();j++)
						{
							Node t = crossersType.item(j);
							if(t.getNodeType()==Node.ELEMENT_NODE) {
								Element type = (Element) t;
								String crosserType = type.getAttribute("type");
								//System.out.println(j);
								System.out.println(crosserType);
								if(crosserType.equals("Farmer")) {
									//Lcrossers.add("Farmer");
									
									leftCrossers.add(new Farmer());
								}
								else if (crosserType.equals("Wolf")) {
									//Lcrossers.add("Wolf");
									leftCrossers.add(new Wolf());
								}
								else if (crosserType.equals("Sheep")) {
									//Lcrossers.add("Sheep");
									leftCrossers.add(new Sheep());
								}
								else if (crosserType.equals("Cabbage")) {
									//Lcrossers.add("Cabbage");
									leftCrossers.add(new Cabbage());
								}
								else if (crosserType.equals("20.0")) {
									//Rcrossers.add("20");
									leftCrossers.add(new Animal(20));
								}
								else if (crosserType.equals("40.0")) {
									leftCrossers.add(new Person(40));
								}
								else if (crosserType.equals("60.0")) {
									leftCrossers.add(new Person(60));
								}
								else if (crosserType.equals("80.0")) {
									leftCrossers.add(new Person(80));
								}
								else if (crosserType.equals("90.0")) {
									leftCrossers.add(new Person(90));
								}
								
								
								
							}
						}
					}
				}
				
				
				
				
			System.out.println(Lcrossers);
			System.out.println(Rcrossers);
		}} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<List<ICrosser>> solveGame() {
		
		return null;
	}
	public void addToBoat(ICrosser crosser)
	{
			boatCrossers.add(crosser);
	}
	public void removeFromBoat(ICrosser crosser)
	{
		boatCrossers.remove(crosser);
	}
	@Override
	public void addToLeft(ICrosser crosser) {
		leftCrossers.add(crosser);
		
	}
	@Override
	public void removeFromLeft(ICrosser crosser) {
		leftCrossers.remove(crosser);
		
	}
	@Override
	public void addToRight(ICrosser crosser) {
		rightCrossers.add(crosser);
		
	}
	@Override
	public void removeFromRight(ICrosser crosser) {
		rightCrossers.remove(crosser);
		
	}
	@Override
	public List<ICrosser> getBoatCrossers() {
		return boatCrossers;
	}
	public void setBoatCrossers(List<ICrosser> boatCrossers)
	{
		this.boatCrossers=boatCrossers;
	}
	public void setRightCrossers(List<ICrosser> rightCrossers) {
		this.rightCrossers=rightCrossers;
		
	}
	public void setLeftCrossers(List<ICrosser> leftCrossers) {
		this.leftCrossers=leftCrossers;
		
	}
	public int getLevel()
	{
		if(currentLevel instanceof Scene1)
		{
			return 1;
		}
		if(currentLevel instanceof Scene2)
		{
			return 2;
		}
		else
			return 0;
	}
}
