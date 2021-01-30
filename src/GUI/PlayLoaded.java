package GUI;


import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.BufferedImageUtil;

import Crosser.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

public class PlayLoaded extends BasicGameState{
	public String mouse="No input yet";
	ICrossingStrategy level=new Scene1();
	GameController game=new GameController(level);
	Command newGame;
	Command doMove;
	Image world;
	Image close;
	Image undo;
	Image redo;
	Image reset;
	Image moveButton;
	Image label;
	Image boatImageLeft;
	Image boatImageRight;
	int flagB=0;
	Image farmerImageLeft;
	Image farmerImageRight;
	Image wolfImageLeft;
	Image wolfImageRight;
	Image sheepImageLeft;
	Image sheepImageRight;
	Image cabbageImageLeft;
	Image cabbageImageRight;
	Image winImg;
	Animation boat,farmer,wolf,sheep,cabbage,movingRightFarmer,movingLeftFarmer,movingRightWolf,movingRightCabbage,movingRightBoat,movingRightSheep,movingLeftWolf,movingLeftSheep,movingLeftCabbage,movingleftBoat,win;
	int[] duration= {200,200};
	float positionX=0;
	float positionY=0;
	float shiftFarmerX=positionX+5;
	float farmerOnLeftBank=5;
	float farmerOnBoatLeft=270;
	float farmerOnRightBank=750;
	float farmerOnBoatRight=580;
	float sheepOnLeftBank=195;
	float sheepOnBoatLeft=390;
	float sheepOnRightBank=700;
	float sheepOnBoatRight=550;
	float cabbageOnLeftBank=110;
	float cabbageOnBoatLeft=390;
	float cabbageOnRightBank=740;
	float cabbageOnBoatRight=560;
	float wolfOnLeftBank=225;
	float wolfOnBoatLeft=390;
	float wolfOnRightBankX=640;
	float wolfOnRightBankY=500-25;
	float wolfOnBoatRight=570;
	float shiftWolfX=positionX+225;
	float shiftWolfY=positionY+500-56;
	float shiftSheepX=positionX+195;
	float shiftSheepY=positionY+500-30;
	float shiftCabbageX=positionX+110;
	float shiftCabbageY=positionY+500-36;
	float shiftBoatX=positionX+300;
	int flagFarmer=0;
	int flagWolf=0;
	int flagSheep=0;
	int flagCabbage=0;
	int flagBoat=0;
	float moveButtonScale=0.1f;
	int flagMove=0;
	int flagWin=0;
	int varHeight=0;
	int varWidth=0;
	ICrosser farmerObj;
	ICrosser wolfObj;
	ICrosser sheepObj;
	ICrosser cabbageObj;
	List<ICrosser> boatCrossers;
public PlayLoaded(int state) {
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		 world=new Image("data/world.jpg");
		 boatImageLeft=new Image("data/boat2.png");
		 reset=new Image("data/reset.png");
		 undo=new Image("data/undo.png");
		redo=new Image("data/redo.png");
		 close=new Image("data/close.png");
		 moveButton=new Image("data/sail2.png");
		 label=new Image("data/label.png");
		 winImg=new Image("data/win.png");
		 doMove=new doMoveCommand(level,game);
		game.loadGame();
		
		for(ICrosser iCrosser : game.getCrossersOnLeftBank())
		{
			if(iCrosser instanceof Farmer)
			{
				farmerObj=iCrosser;
				shiftFarmerX=farmerOnLeftBank;
				
			}
			if(iCrosser instanceof Wolf)
			{
				wolfObj=iCrosser;
				shiftWolfX=wolfOnLeftBank;
				
			}
			if(iCrosser instanceof Sheep)
			{
				sheepObj=iCrosser;
				shiftSheepX=sheepOnLeftBank;
				
			}
			if(iCrosser instanceof Cabbage)
			{
				cabbageObj=iCrosser;
				shiftCabbageX=cabbageOnLeftBank;
				
			}
		}
		
		for(ICrosser iCrosser : game.getCrossersOnRightBank())
		{
			if(iCrosser instanceof Farmer)
			{
				farmerObj=iCrosser;
				shiftFarmerX=farmerOnRightBank;
			}
			if(iCrosser instanceof Wolf)
			{
				wolfObj=iCrosser;
				shiftWolfX=wolfOnRightBankX;
				shiftWolfY=wolfOnRightBankY;
				
			}
			if(iCrosser instanceof Sheep)
			{
				sheepObj=iCrosser;
				shiftSheepX=sheepOnRightBank;
				
			}
			if(iCrosser instanceof Cabbage)
			{
				cabbageObj=iCrosser;
				shiftCabbageX=cabbageOnRightBank;
			}
		}
		if(!game.isBoatOnTheLeftBank())
		{
			boat=movingleftBoat;
			shiftBoatX=530;
		}
		if(game.isBoatOnTheLeftBank())
		{
			boat=movingRightBoat;
			shiftBoatX=300;
		}
		
		 BufferedImage[] farmerImages= farmerObj.getImages();//check
		 BufferedImage[] wolfImages=wolfObj.getImages();//check
		 BufferedImage[] sheepImages=sheepObj.getImages();//check
		 BufferedImage[] cabbageImages=cabbageObj.getImages();//check
		 
		 try {
			Texture tex=BufferedImageUtil.getTexture("test", farmerImages[0]);
			farmerImageLeft=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", farmerImages[1]);
			farmerImageRight=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", wolfImages[0]);
			wolfImageLeft=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", wolfImages[1]);
			wolfImageRight=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", sheepImages[0]);
			sheepImageLeft=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", sheepImages[1]);
			sheepImageRight=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", cabbageImages[1]);
			cabbageImageLeft=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", cabbageImages[0]);
			cabbageImageRight=new Image(tex);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image[] walkRightFarmer= {farmerImageLeft,farmerImageLeft};
		Image[] walkRightSheep= {sheepImageLeft,sheepImageLeft};
		Image[] walkRightWolf= {wolfImageLeft,wolfImageLeft};
		Image[] walkRightCabbage= {cabbageImageLeft,cabbageImageLeft};
		Image[] walkRightBoat= {new Image("data/boat2.png"), new Image("data/boat2.png")};
		Image[] winArr= {winImg,winImg};
		movingRightFarmer=new Animation(walkRightFarmer,duration,false);
		movingRightSheep=new Animation(walkRightSheep,duration,false);
		movingRightWolf=new Animation(walkRightWolf,duration,false);
		movingRightCabbage=new Animation(walkRightCabbage,duration,false);
		movingRightBoat=new Animation(walkRightBoat,duration,false);
		win=new Animation(winArr,duration,false);
		
		Image[] walkLeftFarmer= {farmerImageRight,farmerImageRight};
		Image[] walkLeftSheep= {sheepImageRight,sheepImageRight};
		Image[] walkLeftWolf= {wolfImageRight,wolfImageRight};
		Image[] walkLeftCabbage= {cabbageImageRight,cabbageImageRight};
		Image[] walkLeftBoat= {new Image("data/boat1.png"), new Image("data/boat1.png")};
		movingLeftFarmer=new Animation(walkLeftFarmer,duration,false);
		movingLeftSheep=new Animation(walkLeftSheep,duration,false);
		movingLeftWolf=new Animation(walkLeftWolf,duration,false);
		movingLeftCabbage=new Animation(walkLeftCabbage,duration,false);
		movingleftBoat=new Animation(walkLeftBoat,duration,false);
		farmer=movingRightFarmer;
		boat=movingRightBoat;
		wolf=movingRightWolf;
		sheep=movingRightSheep;
		cabbage=movingRightCabbage;
		for(ICrosser iCrosser : game.getCrossersOnRightBank())
		{
			if(iCrosser instanceof Farmer)
			{
				farmer=movingLeftFarmer;
			}
			if(iCrosser instanceof Cabbage)
			{
				cabbage=movingLeftCabbage;
			}
			if(iCrosser instanceof Sheep)
			{
				sheep=movingLeftSheep;
			}
			if(iCrosser instanceof Wolf)
			{
				wolf=movingLeftWolf;
			}
		}
		if(!game.isBoatOnTheLeftBank())
		{
			boat=movingleftBoat;
		}
		boatCrossers=new ArrayList<ICrosser>();
		
		
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		world.draw(0, 0,1.5f);
		moveButton.draw(780,30,moveButtonScale);
		cabbage.draw(shiftCabbageX,shiftCabbageY,110,100);
		
		sheep.draw(shiftSheepX,shiftSheepY,60,80);
		wolf.draw(shiftWolfX,shiftWolfY,100,70);
		farmer.draw(shiftFarmerX,500-90,200,200);
		
		boat.draw(shiftBoatX,500-40,200,200);
		label.draw(50, 50, 200, 90);
		g.drawString("Moves: "+game.getNumberOfSails(), 100, 80);
		reset.draw(300, 50, 40, 40);
		redo.draw(380, 50, 40, 40);
		undo.draw(460,50,40,40);
		g.drawString(mouse, 500, 500);
		if(flagWin==1)
		{
			win.draw(300, 100, 400, 400);
		}
		close.draw(700, 30, 0.1f);
		
		
		//boat : 300, 500-40,0.4f
		
		
		
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int xpos=Mouse.getX();
		int ypos=Mouse.getY();
		Input input=gc.getInput();
		moveButtonScale=0.1f;
		mouse="Mouse position x: "+xpos+" y: "+ypos;
			
		
		
			//farmer left movement (boat and bank)
		if(flagWin==0)
		{
			
			
			if(xpos>300 && xpos<345 && ypos>515 && ypos<545)
			{
				if(input.isMousePressed(0))
				{
					if(game.canUndo())
					{
						game.undo();
						for(ICrosser iCrosser: game.getCrossersOnLeftBank())
						{
							if(iCrosser instanceof Farmer)
							{
								shiftFarmerX=farmerOnLeftBank;
								farmer=movingRightFarmer;
							}
								
							if(iCrosser instanceof Wolf)
							{
								shiftWolfX=wolfOnLeftBank;
								wolf=movingRightWolf;
								
							}
								
							if(iCrosser instanceof Cabbage)
							{
								shiftCabbageX=cabbageOnLeftBank;
								cabbage=movingRightCabbage;
							}
								
							if(iCrosser instanceof Sheep)
							{
								shiftSheepX=sheepOnLeftBank;
								sheep=movingRightSheep;
							}
							
							
								
						}
					
				
					}
					
			 }
		}
			
			if(((xpos>70 && xpos<125 && ypos>20&&ypos<200)) && boatCrossers.size()<2)
				if(input.isMouseButtonDown(0))
				{
					if(shiftFarmerX==farmerOnLeftBank && game.isBoatOnTheLeftBank())
					{
						shiftFarmerX=farmerOnBoatLeft;
						boatCrossers.add(farmerObj);
						game.removeFromLeft(farmerObj);
					}
				}
			if(xpos>835 && xpos<880 && ypos>20 && ypos<160 && boatCrossers.size()<2 && input.isMouseButtonDown(0))
			{
				if(shiftFarmerX==farmerOnRightBank && !game.isBoatOnTheLeftBank())
				{
					shiftFarmerX=farmerOnBoatRight;
					boatCrossers.add(farmerObj);
					game.removeFromRight(farmerObj);
				}
			}
			
			if(xpos>340 && xpos<390 && ypos>50 && ypos<160 && input.isMousePressed(0))
			{
				if(shiftFarmerX == farmerOnBoatLeft && game.isBoatOnTheLeftBank())
				{
					game.addToLeft(farmerObj);
					boatCrossers.remove(farmerObj);
					shiftFarmerX=farmerOnLeftBank;
				}
				
					
			}
			
			if( xpos>660 && xpos<710 && ypos>50 && ypos<160 && input.isMousePressed(0))
			{
				if(shiftFarmerX == farmerOnBoatRight && !game.isBoatOnTheLeftBank())
				{
					game.addToRight(farmerObj);
					boatCrossers.remove(farmerObj);
					shiftFarmerX=farmerOnRightBank;
				}
			}
			//cabbage left movement (boat and bank)
			if(xpos>150 && xpos<190 && ypos>58&&ypos<125 && boatCrossers.size()<2)
				if(input.isMouseButtonDown(0))
				{
					if(shiftCabbageX==cabbageOnLeftBank && game.isBoatOnTheLeftBank())
					{
						shiftCabbageX=cabbageOnBoatLeft;
						shiftCabbageY=500-12;
						boatCrossers.add(cabbageObj);
						game.removeFromLeft(cabbageObj);
					}
				}
			if( xpos>765 && xpos<810 && ypos>22 && ypos<100 && boatCrossers.size()<2 && input.isMouseButtonDown(0))
			{
				if(shiftCabbageX==cabbageOnRightBank && !game.isBoatOnTheLeftBank())
				{
					shiftCabbageX=cabbageOnBoatRight;
					shiftCabbageY=500-12;
					boatCrossers.add(cabbageObj);
					game.removeFromRight(cabbageObj);
				}
			}
			if(xpos>430 && xpos<470 && ypos>50 && ypos<104)
			{
				if(input.isMouseButtonDown(0))
				{
				
						if(shiftCabbageX==cabbageOnBoatLeft && game.isBoatOnTheLeftBank())
						{
								game.addToLeft(cabbageObj);
								boatCrossers.remove(cabbageObj);
								shiftCabbageX=cabbageOnLeftBank;
								shiftCabbageY=500-36;
						}
						
				}
				
			}
			
			if(xpos>580 && xpos<635 && ypos >50 && ypos<100 && input.isMouseButtonDown(0))
			{
				if(shiftCabbageX==cabbageOnBoatRight && !game.isBoatOnTheLeftBank())
				{
						game.addToRight(cabbageObj);
						boatCrossers.remove(cabbageObj);
						shiftCabbageX=cabbageOnRightBank;
						shiftCabbageY=500-12;
				}
			}
			//sheep movement left(boat and bank)
			if((xpos>200 && xpos<244 && ypos>63&&ypos<125) && boatCrossers.size()<2)
				if(input.isMouseButtonDown(0))
				{
					if(shiftSheepX==sheepOnLeftBank && game.isBoatOnTheLeftBank())
					{
						shiftSheepX=sheepOnBoatLeft;
						shiftSheepY=500-12;
						boatCrossers.add(sheepObj);
						game.removeFromLeft(sheepObj);
					}
					
					
				}
			
			if((xpos>710 && xpos<750 && ypos>42 && ypos <102)&& boatCrossers.size()<2 && input.isMouseButtonDown(0))
			{
				if(shiftSheepX==sheepOnRightBank && !game.isBoatOnTheLeftBank())
				{
					shiftSheepX=sheepOnBoatRight;
					shiftSheepY=500-12;
					boatCrossers.add(sheepObj);
					game.removeFromRight(sheepObj);
				}
			}

			if(xpos>395 && xpos<440 && ypos>50 && ypos<104 )
			{
				if(input.isMouseButtonDown(0))
				{
						if(shiftSheepX==sheepOnBoatLeft && game.isBoatOnTheLeftBank())
						{
								
									game.addToLeft(sheepObj);
									boatCrossers.remove(sheepObj);
									shiftSheepX=sheepOnLeftBank;
									shiftSheepY=500-30;
																
						}
							
				}
			}
			if(xpos>555 && xpos<600 && ypos>50 && ypos<105 && input.isMouseButtonDown(0))
			{
				if(shiftSheepX==sheepOnBoatRight && !game.isBoatOnTheLeftBank())
				{
						
							game.addToRight(sheepObj);
							boatCrossers.remove(sheepObj);
							shiftSheepX=sheepOnRightBank;
							shiftSheepY=500-12;
														
				}
			}
			//wolf movement left (boat and bank)
			if(xpos>235 && xpos<305 && ypos>80&&ypos<150 && boatCrossers.size()<2)
				if(input.isMouseButtonDown(0))
				{
					if(shiftWolfX==wolfOnLeftBank && game.isBoatOnTheLeftBank())
					{
						shiftWolfX=wolfOnBoatLeft;
						shiftWolfY=500-12;
						boatCrossers.add(wolfObj);
						game.removeFromLeft(wolfObj);
					}
					
					
					
				}
			if(xpos > 660 && xpos<703 && ypos>60 && ypos<120 && boatCrossers.size()<2 && input.isMouseButtonDown(0))
			{
				if(shiftWolfX==wolfOnRightBankX && !game.isBoatOnTheLeftBank())
				{
					shiftWolfX=wolfOnBoatRight;
					shiftWolfY=500-12;
					boatCrossers.add(wolfObj);
					game.removeFromRight(wolfObj);
				}
				
			}
			if(xpos>425 && xpos<470 && ypos>50 && ypos<105)
			{
				if(input.isMouseButtonDown(0))
				{
					
						if(shiftWolfX==wolfOnBoatLeft && game.isBoatOnTheLeftBank())
						{
								game.addToLeft(wolfObj);
								boatCrossers.remove(wolfObj);
								shiftWolfX=wolfOnLeftBank;
								shiftWolfY=500-56;
						}
						
				}
			}
			if(xpos>590 && xpos<635 && ypos>50 && ypos<106 && input.isMouseButtonDown(0))
			{
				if(shiftWolfX==wolfOnBoatRight && !game.isBoatOnTheLeftBank())
				{
						game.addToRight(wolfObj);
						boatCrossers.remove(wolfObj);
						shiftWolfX=wolfOnRightBankX;
						shiftWolfY=wolfOnRightBankY;
				}
			}
			
			
			if(xpos>710 && xpos<750 && ypos>520 && ypos<560)
			{
				if(input.isMouseButtonDown(0))
				{
					game.saveGame();
					gc.exit();
				}
			}
			
			if(xpos>785 && xpos<875 && ypos> 475 && ypos < 565)
			{	
				moveButtonScale=0.12f;
				if(input.isMouseButtonDown(0))
				{
					flagMove=1;
				}
				
			}
			if(flagMove==1)
			{
				for(ICrosser iCrosser : game.getCrossersOnLeftBank())
				{
					System.out.println(iCrosser.getLabelToBeShown());
				}
				System.out.println("-----------------------");
				for(ICrosser iCrosser : game.getCrossersOnRightBank())
				{
					System.out.println(iCrosser.getLabelToBeShown());
				}
				System.out.println("-----------------------");
				if(game.canMove(boatCrossers, game.isBoatOnTheLeftBank()))
				{
					game.setBoatCrossers(boatCrossers);
					if(flagMove==1 && game.isBoatOnTheLeftBank())
					{
						if(shiftBoatX<530)
						{
							shiftBoatX+=delta*0.1f;
							if(shiftFarmerX>=farmerOnBoatLeft && shiftFarmerX<farmerOnRightBank)
							{
								shiftFarmerX+=delta*0.1f;
								if(shiftBoatX>525)
								{
									farmer=movingLeftFarmer;
									shiftFarmerX=farmerOnRightBank;
									
								}
							}
							if(shiftSheepX>=sheepOnBoatLeft && shiftSheepX<sheepOnRightBank)
							{
								shiftSheepX+=delta*0.1f;
								if(shiftBoatX>525)
								{
									sheep=movingLeftSheep;
									shiftSheepX=sheepOnRightBank;
									
								}
							}
							
							if(shiftCabbageX>=cabbageOnBoatLeft && shiftCabbageX<cabbageOnRightBank)
							{
								shiftCabbageX+=delta*0.1f;
								if(shiftBoatX>525)
								{
									cabbage=movingLeftCabbage;
									shiftCabbageX=cabbageOnRightBank;
									
								}
							}
							
							if(shiftWolfX>=wolfOnBoatLeft && shiftWolfX<wolfOnRightBankX)
							{
								shiftWolfX+=delta*0.1f;
								if(shiftBoatX>525)
								{
									wolf=movingLeftWolf;
									shiftWolfX=wolfOnRightBankX;
									shiftWolfY=wolfOnRightBankY;
									
								}
							}
						}
						if(shiftBoatX>=530)
						{
							flagMove=0;
							doMove.execute();
							boatCrossers.clear();
							boat=movingleftBoat;
							
						}
							
					}
					if(flagMove==1 && !game.isBoatOnTheLeftBank())
					{
						if(shiftBoatX>330)
						{
							shiftBoatX-=delta*0.1f;
							System.out.println(shiftBoatX);
							if(shiftFarmerX<=farmerOnBoatRight && shiftFarmerX>farmerOnLeftBank)
							{
								shiftFarmerX-=delta*0.1f;
								if(shiftBoatX<335)
								{
									farmer=movingRightFarmer;
									shiftFarmerX=farmerOnLeftBank;
									
								}
							}
							if(shiftSheepX<=sheepOnBoatRight && shiftSheepX>sheepOnLeftBank)
							{
								shiftSheepX-=delta*0.1f;
								if(shiftBoatX<335)
								{
									sheep=movingRightSheep;
									shiftSheepX=sheepOnLeftBank;
									
								}
							}
							
							if(shiftCabbageX<=cabbageOnBoatRight && shiftCabbageX>cabbageOnLeftBank)
							{
								shiftCabbageX-=delta*0.1f;
								if(shiftBoatX<335)
								{
									cabbage=movingRightCabbage;
									shiftCabbageX=cabbageOnLeftBank;
									
								}
							}
							
							if(shiftWolfX<=wolfOnBoatRight && shiftWolfX>wolfOnLeftBank)
							{
								shiftWolfX-=delta*0.1f;
								if(shiftBoatX<335)
								{
									wolf=movingRightWolf;
									shiftWolfX=wolfOnLeftBank;
									
								}
							}
						}
						if(shiftBoatX<=330)
						{
							flagMove=0;
							doMove.execute();
							boatCrossers.clear();
							boat=movingRightBoat;
						}
					}			
				}
			}
			
		}
			
			if(game.getCrossersOnRightBank().size()==5)
			{
				flagWin=1;
				
			}
			if(input.isMouseButtonDown(0)&& flagWin==1)
			{
				sbg.enterState(2);
			}
			
	}
	public int getID(){
		return 4;
	}
}
