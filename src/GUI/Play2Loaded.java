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

public class Play2Loaded extends BasicGameState{
	public String mouse="No input yet";
	ICrossingStrategy level=new Scene2();
	
	GameController game=new GameController(level);
	
	
	Command newGame;
	Command doMove;
	Image world;
	Image moveButton;
	Image label;
	Image boatImageLeft;
	Image boatImageRight;
	int flagB=0;
	Image close;
	Image farmer90ImageLeft;
	Image farmer90ImageRight;
	Image farmer80ImageLeft;
	Image farmer80ImageRight;
	Image farmer60ImageLeft;
	Image farmer60ImageRight;
	Image farmer40ImageLeft;
	Image farmer40ImageRight;
	Image animal20ImageLeft;
	Image animal20ImageRight;
	Image winImg;
	Animation boat,farmer90,farmer80,farmer60,farmer40,animal20,movingRightFarmer90,movingLeftFarmer90,movingRightFarmer80,movingRightFarmer60,movingRightBoat,movingRightFarmer40,movingLeftFarmer80,movingLeftFarmer60,movingLeftFarmer40,movingleftBoat,win,movingLeftAnimal20,movingRightAnimal20;
	int[] duration= {200,200};
	float positionX=0;
	float positionY=0;
	float shiftFarmer90X=positionX+5;
	float farmer90OnLeftBank=5;
	float farmer90OnBoatLeft=270;
	float farmer90OnRightBank=750;
	float farmer90OnBoatRight=580;
	float farmer60OnLeftBank=230;
	float farmer60OnBoatLeft=370;
	float farmer60OnRightBank=700;
	float farmer60OnBoatRight=550;
	float farmer40OnLeftBank=145;
	float farmer40OnBoatLeft=390;
	float farmer40OnRightBank=740;
	float farmer40OnBoatRight=560;
	float farmer80OnLeftBank=310;
	float farmer80OnBoatLeft=350;
	float farmer80OnRightBankX=670;
	float farmer80OnRightBankY=500-125;
	float farmer80OnBoatRight=570;
	float animal20OnBoatRight=600;//kkkkkkkkk
	float animal20OnBoatLeft=390;//kkkkkkkkkk
	float animal20OnLeftBank=230;//kkkkkkkkkkk
	float animal20OnRightBank=660;//kkkkkkkkkkkk
	float shiftFarmer80X=positionX+310;
	float shiftFarmer80Y=positionY+500-145;
	float shiftFarmer60X=positionX+230;
	float shiftFarmer60Y=positionY+500-130;
	float shiftFarmer40X=positionX+145;
	float shiftFarmer40Y=positionY+500-36;
	float shiftAnimal20X=230;//kkkkkkkk
	float shiftAnimal20Y=500-50;//kkkkkkkk
	
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
	ICrosser farmer90Obj;
	ICrosser farmer80Obj;
	ICrosser farmer60Obj;
	ICrosser farmer40Obj;
	ICrosser animal20Obj;
	List<ICrosser> boatCrossers;
	
public Play2Loaded(int state) {
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		 world=new Image("data/world.jpg");
		 boatImageLeft=new Image("data/boat2.png");
		 moveButton=new Image("data/sail2.png");
		 close=new Image("data/close.png");
		 label=new Image("data/label.png");
		 winImg=new Image ("data/win.png");
		 doMove=new doMoveCommand(level,game);
		game.loadGame();
		
		for(ICrosser iCrosser : game.getCrossersOnLeftBank())
		{
			if(iCrosser instanceof Person)
			{
				if(iCrosser.getWeight()==90)
				{
					farmer90Obj=iCrosser;
				}
				if(iCrosser.getWeight()==80)
				{
					farmer80Obj=iCrosser;
				}
				if(iCrosser.getWeight()==60)
				{
					farmer60Obj=iCrosser;
				}
				if(iCrosser.getWeight()==40)
				{
					farmer40Obj=iCrosser;
				}
				
			}
			if(iCrosser instanceof Animal)
			{
				animal20Obj=iCrosser;
				
			}
			
		}
		
		for(ICrosser iCrosser : game.getCrossersOnRightBank())
		{
			if(iCrosser instanceof Person)
			{
				if(iCrosser.getWeight()==90)
				{
					farmer90Obj=iCrosser;
					shiftFarmer90X=farmer90OnRightBank;
				}
				if(iCrosser.getWeight()==80)
				{
					farmer80Obj=iCrosser;
					shiftFarmer80X=farmer80OnRightBankX;
					shiftFarmer80Y=farmer80OnRightBankY;
				}
				if(iCrosser.getWeight()==60)
				{
					farmer60Obj=iCrosser;
					shiftFarmer60X=farmer60OnRightBank;
					shiftFarmer60Y=500-60;
							
				}
				if(iCrosser.getWeight()==40)
				{
					farmer40Obj=iCrosser;
					shiftFarmer40X=farmer40OnRightBank;
				}
				
			}
			if(iCrosser instanceof Animal)
			{
				animal20Obj=iCrosser;
				shiftAnimal20X=animal20OnRightBank;
				
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
		
		 BufferedImage[] farmer90Images= farmer90Obj.getImages();
		 BufferedImage[] farmer80Images=farmer80Obj.getImages();
		 BufferedImage[] farmer60Images=farmer60Obj.getImages();
		 BufferedImage[] farmer40Images=farmer40Obj.getImages();
		 BufferedImage[] animal20Images=animal20Obj.getImages();
		 
		 try {
			Texture tex=BufferedImageUtil.getTexture("test", farmer90Images[0]);
			farmer90ImageLeft=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", farmer90Images[1]);
			farmer90ImageRight=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", farmer80Images[0]);
			farmer80ImageLeft=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", farmer80Images[1]);
			farmer80ImageRight=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", farmer60Images[0]);
			farmer60ImageLeft=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", farmer60Images[1]);
			farmer60ImageRight=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", farmer40Images[0]);
			farmer40ImageLeft=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", farmer40Images[1]);
			farmer40ImageRight=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", animal20Images[0]);
			animal20ImageLeft=new Image(tex);
			tex=BufferedImageUtil.getTexture("test", animal20Images[1]);
			animal20ImageRight=new Image(tex);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image[] walkRightFarmer90= {farmer90ImageLeft,farmer90ImageLeft};
		Image[] walkRightFarmer60= {farmer60ImageLeft,farmer60ImageLeft};
		Image[] walkRightFarmer80= {farmer80ImageLeft,farmer80ImageLeft};
		Image[] walkRightFarmer40= {farmer40ImageLeft,farmer40ImageLeft};
		Image[] walkRightAnimal20= {animal20ImageLeft,animal20ImageLeft};
		Image[] walkRightBoat= {new Image("data/boat2.png"), new Image("data/boat2.png")};
		Image[] winArr= {winImg,winImg};
		movingRightFarmer90=new Animation(walkRightFarmer90,duration,false);
		movingRightFarmer60=new Animation(walkRightFarmer60,duration,false);
		movingRightFarmer80=new Animation(walkRightFarmer80,duration,false);
		movingRightFarmer40=new Animation(walkRightFarmer40,duration,false);
		movingRightAnimal20=new Animation(walkRightAnimal20,duration,false);
		movingRightBoat=new Animation(walkRightBoat,duration,false);
		win=new Animation(winArr,duration,false);
		
		Image[] walkLeftFarmer90= {farmer90ImageRight,farmer90ImageRight};
		Image[] walkLeftFarmer60= {farmer60ImageRight,farmer60ImageRight};
		Image[] walkLeftFarmer80= {farmer80ImageRight,farmer80ImageRight};
		Image[] walkLeftFarmer40= {farmer40ImageRight,farmer40ImageRight};
		Image[] walkLeftAnimal20= {animal20ImageRight,animal20ImageRight};
		Image[] walkLeftBoat= {new Image("data/boat1.png"), new Image("data/boat1.png")};
		movingLeftFarmer90=new Animation(walkLeftFarmer90,duration,false);
		movingLeftFarmer60=new Animation(walkLeftFarmer60,duration,false);
		movingLeftFarmer80=new Animation(walkLeftFarmer80,duration,false);
		movingLeftFarmer40=new Animation(walkLeftFarmer40,duration,false);
		movingLeftAnimal20=new Animation(walkLeftAnimal20,duration,false);
		movingleftBoat=new Animation(walkLeftBoat,duration,false);
		farmer90=movingRightFarmer90;
		boat=movingRightBoat;
		farmer80=movingRightFarmer80;
		farmer60=movingRightFarmer60;
		farmer40=movingRightFarmer40;
		animal20=movingRightAnimal20;
		 
		for(ICrosser iCrosser : game.getCrossersOnRightBank())
		{
			if(iCrosser instanceof Person)
			{
				if(iCrosser.getWeight()==90)
				{
					farmer90=movingLeftFarmer90;
				}
				if(iCrosser.getWeight()==80)
				{
					farmer80=movingLeftFarmer80;
				}
				if(iCrosser.getWeight()==60)
				{
					farmer60=movingLeftFarmer60;
				}
				if(iCrosser.getWeight()==40)
				{
					farmer40=movingLeftFarmer40;
				}
				
			}
			if(iCrosser instanceof Animal)
			{
				animal20=movingLeftAnimal20;
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
		g.drawString(farmer80Obj.getLabelToBeShown(), shiftFarmer80X+30, shiftFarmer80Y+10);
		farmer80.draw(shiftFarmer80X,shiftFarmer80Y,150,180);
		animal20.draw(shiftAnimal20X, shiftAnimal20Y, 60, 80);
		g.drawString(farmer60Obj.getLabelToBeShown(), shiftFarmer60X+30, shiftFarmer60Y+10);
		farmer60.draw(shiftFarmer60X,shiftFarmer60Y,150,160);
		g.drawString(animal20Obj.getLabelToBeShown(), shiftAnimal20X, shiftAnimal20Y-10);
		g.drawString(farmer40Obj.getLabelToBeShown(), shiftFarmer40X+30, shiftFarmer40Y-10);
		farmer40.draw(shiftFarmer40X,shiftFarmer40Y,150,140);
		g.drawString(farmer90Obj.getLabelToBeShown(), shiftFarmer90X+50, 500-80);
		farmer90.draw(shiftFarmer90X,500-90,200,200);
		boat.draw(shiftBoatX,500-40,200,200);
		label.draw(50, 50, 200, 90);
		close.draw(700, 30, 0.1f);
		g.drawString("Moves: "+game.getNumberOfSails(), 100, 80);
		g.drawString(mouse, 500, 500);
		if(flagWin==1)
		{
			win.draw(300, 100, 400, 400);
		}
		
		
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
			if((xpos>70 && xpos<125 && ypos>20&&ypos<200) && game.isBoatOnTheLeftBank())
				if(input.isMouseButtonDown(0))
				{
					if(shiftFarmer90X==farmer90OnLeftBank)
					{
						shiftFarmer90X=farmer90OnBoatLeft;
						boatCrossers.add(farmer90Obj);
						game.removeFromLeft(farmer90Obj);
					}
				}
			if((xpos>835 && xpos<880 && ypos>20 && ypos<160) && boatCrossers.size()<2  && !game.isBoatOnTheLeftBank() && input.isMouseButtonDown(0))
			{
				if(shiftFarmer90X==farmer90OnRightBank)
				{
					shiftFarmer90X=farmer90OnBoatRight;
					boatCrossers.add(farmer90Obj);
					game.removeFromRight(farmer90Obj);
				}
			}
			
			if(xpos>340 && xpos<390 && ypos>50 && ypos<160 && input.isMousePressed(0))
			{
				if(shiftFarmer90X == farmer90OnBoatLeft && game.isBoatOnTheLeftBank())
				{
					game.addToLeft(farmer90Obj);
					boatCrossers.remove(farmer90Obj);
					shiftFarmer90X=farmer90OnLeftBank;
				}
					
			}
			
			if( xpos>660 && xpos<710 && ypos>50 && ypos<160 && input.isMousePressed(0))
			{
				if(shiftFarmer90X == farmer90OnBoatRight && !game.isBoatOnTheLeftBank())
				{
					game.addToRight(farmer90Obj);
					boatCrossers.remove(farmer90Obj);
					shiftFarmer90X=farmer90OnRightBank;
				}
			}
			//cabbage left movement (boat and bank)
			if(xpos>200 && xpos<240 && ypos>15&&ypos<115 && boatCrossers.size()<2)
				if(input.isMouseButtonDown(0))
				{
					if(shiftFarmer40X==farmer40OnLeftBank && game.isBoatOnTheLeftBank())
					{
						shiftFarmer40X=farmer40OnBoatLeft;
						shiftFarmer40Y=500-40;
						boatCrossers.add(farmer40Obj);
						game.removeFromLeft(farmer40Obj);
					}
					
				}
			
			if(xpos>800 && xpos<830 && ypos>22 && ypos<120 && boatCrossers.size()<2 && input.isMouseButtonDown(0))
			{
				if(shiftFarmer40X==farmer40OnRightBank && !game.isBoatOnTheLeftBank())
				{
					shiftFarmer40X=farmer40OnBoatRight;
					shiftFarmer40Y=500-40;
					boatCrossers.add(farmer40Obj);
					game.removeFromRight(farmer40Obj);
				}
			}
			if(xpos>430 && xpos<470 && ypos>50 && ypos<104)
			{
				if(input.isMouseButtonDown(0))
				{
				
						if(shiftFarmer40X==farmer40OnBoatLeft && game.isBoatOnTheLeftBank())
						{
								game.addToLeft(farmer40Obj);
								boatCrossers.remove(farmer40Obj);
								shiftFarmer40X=farmer40OnLeftBank;
								shiftFarmer40Y=500-36;
						}
						
				}
				
			}
			
			 if(xpos>620 && xpos<650 && ypos >50 && ypos<120 && input.isMouseButtonDown(0))
			 {
				 if(shiftFarmer40X==farmer40OnBoatRight && !game.isBoatOnTheLeftBank())
					{
							game.addToRight(farmer40Obj);
							boatCrossers.remove(farmer40Obj);
							shiftFarmer40X=farmer40OnRightBank;
					}
			 }
			//sheep movement left(boat and bank)
			if((xpos>285 && xpos<325 && ypos>90&&ypos<210))
				if(input.isMouseButtonDown(0))
				{
					if(shiftFarmer60X==farmer60OnLeftBank && game.isBoatOnTheLeftBank())
					{
						shiftFarmer60X=farmer60OnBoatLeft;
						shiftFarmer60Y=500-60;
						boatCrossers.add(farmer60Obj);
						game.removeFromLeft(farmer60Obj);
					}
					
					
					
				}
			if((xpos>760 && xpos<790 && ypos>20 && ypos <130)&& boatCrossers.size()<2 && input.isMouseButtonDown(0))
			{
				if(shiftFarmer60X==farmer60OnRightBank && !game.isBoatOnTheLeftBank())
				{
					shiftFarmer60X=farmer60OnBoatRight;
					shiftFarmer60Y=500-60;
					boatCrossers.add(farmer60Obj);
					game.removeFromRight(farmer60Obj);
				}
			}
			

			if(xpos>420 && xpos<465 && ypos>50 && ypos<135)
			{
				if(input.isMouseButtonDown(0))
				{
						if(shiftFarmer60X==farmer60OnBoatLeft && game.isBoatOnTheLeftBank())
						{
								
									game.addToLeft(farmer60Obj);
									boatCrossers.remove(farmer60Obj);
									shiftFarmer60X=farmer60OnLeftBank;
									shiftFarmer60Y=500-130;
																
						}
						
				}
			}
			if(xpos>610 && xpos<642 && ypos>50 && ypos<135 && input.isMouseButtonDown(0))
			{
				if(shiftFarmer60X==farmer60OnBoatRight && !game.isBoatOnTheLeftBank())
				{
						
							game.addToRight(farmer60Obj);
							boatCrossers.remove(farmer60Obj);
							shiftFarmer60X=farmer60OnRightBank;
														
				}	
			}

			//wolf movement left (boat and bank)
			if(xpos>360 && xpos<400 && ypos>60&&ypos<220 && boatCrossers.size()<2 )
				if(input.isMouseButtonDown(0))
				{
					if(shiftFarmer80X==farmer80OnLeftBank && game.isBoatOnTheLeftBank())
					{
						shiftFarmer80X=farmer80OnBoatLeft;
						shiftFarmer80Y=500-90;
						boatCrossers.add(farmer80Obj);
						game.removeFromLeft(farmer80Obj);
					}
					
					
					
					
				}
			if(xpos > 720 && xpos<760 && ypos>70 && ypos<200 && boatCrossers.size()<2 && input.isMouseButtonDown(0))
			{
				if(shiftFarmer80X==farmer80OnRightBankX && !game.isBoatOnTheLeftBank())
				{
					shiftFarmer80X=farmer80OnBoatRight;
					shiftFarmer80Y=500-90;
					boatCrossers.add(farmer80Obj);
					game.removeFromRight(farmer80Obj);
				}
			}
			if(xpos>400 && xpos<440 && ypos>50 && ypos<160)
			{
				if(input.isMouseButtonDown(0))
				{
					
						if(shiftFarmer80X==farmer80OnBoatLeft && game.isBoatOnTheLeftBank())
						{
								game.addToLeft(farmer80Obj);
								boatCrossers.remove(farmer80Obj);
								shiftFarmer80X=farmer80OnLeftBank;
								shiftFarmer80Y=500-145;
						}
						
				}
			}
			if(xpos>630 && xpos<665 && ypos>50 && ypos<160 && input.isMouseButtonDown(0))
			{
				if(shiftFarmer80X==farmer80OnBoatRight && !game.isBoatOnTheLeftBank())
				{
						game.addToRight(farmer80Obj);
						boatCrossers.remove(farmer80Obj);
						shiftFarmer80X=farmer80OnRightBankX;
						shiftFarmer80Y=farmer80OnRightBankY;
				}
			}
			
			
			if(xpos>235 && xpos<280 && ypos>80&&ypos<140 && boatCrossers.size()<2)
				if(input.isMouseButtonDown(0))
				{
					if(shiftAnimal20X==animal20OnLeftBank && game.isBoatOnTheLeftBank())
					{
						shiftAnimal20X=animal20OnBoatLeft;
						shiftAnimal20Y=500-12;
						boatCrossers.add(animal20Obj);
						game.removeFromLeft(animal20Obj);
					}
					
					
					
					
				}
			if(xpos > 665 && xpos<710 && ypos>100 && ypos<160 && boatCrossers.size()<2 && input.isMouseButtonDown(0))
			{
				if(shiftAnimal20X==animal20OnRightBank && !game.isBoatOnTheLeftBank())
				{
					shiftAnimal20X=animal20OnBoatRight;
					shiftAnimal20Y=500-12;
					boatCrossers.add(animal20Obj);
					game.removeFromRight(animal20Obj);
				}
			}
			if(xpos>395 && xpos<440 && ypos>50 && ypos<105)
			{
				if(input.isMouseButtonDown(0))
				{
					
						if(shiftAnimal20X==animal20OnBoatLeft && game.isBoatOnTheLeftBank())
						{
								game.addToLeft(animal20Obj);
								boatCrossers.remove(animal20Obj);
								shiftAnimal20X=animal20OnLeftBank;
								shiftAnimal20Y=500-56;
						}
						
				}
			}
			if(xpos>605 && xpos<650 && ypos>50 && ypos<103 && input.isMouseButtonDown(0))
			{
				if(shiftAnimal20X==animal20OnBoatRight && !game.isBoatOnTheLeftBank())
				{
						game.addToRight(animal20Obj);
						boatCrossers.remove(animal20Obj);
						shiftAnimal20X=animal20OnRightBank;
						shiftAnimal20Y=500-70;
						
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
			if(game.canMove(boatCrossers, game.isBoatOnTheLeftBank()))
			{
				game.setBoatCrossers(boatCrossers);
				if(flagMove==1 && game.isBoatOnTheLeftBank())
				{
					if(shiftBoatX<530)
					{
						shiftBoatX+=delta*0.1f;
						if(shiftFarmer90X>=farmer90OnBoatLeft && shiftFarmer90X<farmer90OnRightBank)
						{
							shiftFarmer90X+=delta*0.1f;
							if(shiftBoatX>525)
							{
								farmer90=movingLeftFarmer90;
								shiftFarmer90X=farmer90OnRightBank;
								
							}
						}
						if(shiftFarmer60X>=farmer60OnBoatLeft && shiftFarmer60X<farmer60OnRightBank)
						{
							shiftFarmer60X+=delta*0.1f;
							if(shiftBoatX>525)
							{
								farmer60=movingLeftFarmer60;
								shiftFarmer60X=farmer60OnRightBank;
								
							}
						}
						
						
						
						if(shiftFarmer40X>=farmer40OnBoatLeft && shiftFarmer40X<farmer40OnRightBank)
						{
							shiftFarmer40X+=delta*0.1f;
							if(shiftBoatX>525)
							{
								farmer40=movingLeftFarmer40;
								shiftFarmer40X=farmer40OnRightBank;
								
							}
						}
						
						if(shiftFarmer80X>=farmer80OnBoatLeft && shiftFarmer80X<farmer80OnRightBankX)
						{
							shiftFarmer80X+=delta*0.1f;
							if(shiftBoatX>525)
							{
								farmer80=movingLeftFarmer80;
								shiftFarmer80X=farmer80OnRightBankX;
								shiftFarmer80Y=farmer80OnRightBankY;
								
							}
						}
						
						if(shiftAnimal20X>=animal20OnBoatLeft && shiftAnimal20X<animal20OnRightBank)
						{
							shiftAnimal20X+=delta*0.1f;
							if(shiftBoatX>525)
							{
								animal20=movingLeftAnimal20;
								shiftAnimal20X=animal20OnRightBank;
								shiftAnimal20Y=500-70;
								
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
						if(shiftFarmer90X<=farmer90OnBoatRight && shiftFarmer90X>farmer90OnLeftBank)
						{
							shiftFarmer90X-=delta*0.1f;
							if(shiftBoatX<335)
							{
								farmer90=movingRightFarmer90;
								shiftFarmer90X=farmer90OnLeftBank;
								
							}
						}
						if(shiftFarmer60X<=farmer60OnBoatRight && shiftFarmer60X>farmer60OnLeftBank)
						{
							shiftFarmer60X-=delta*0.1f;
							if(shiftBoatX<335)
							{
								farmer60=movingRightFarmer60;
								shiftFarmer60X=farmer60OnLeftBank;
								shiftFarmer60Y=500-130;
								
							}
						}
						
						if(shiftFarmer40X<=farmer40OnBoatRight && shiftFarmer40X>farmer40OnLeftBank)
						{
							shiftFarmer40X-=delta*0.1f;
							if(shiftBoatX<335)
							{
								farmer40=movingRightFarmer40;
								shiftFarmer40X=farmer40OnLeftBank;
								
							}
						}
						
						if(shiftFarmer80X<=farmer80OnBoatRight && shiftFarmer80X>farmer80OnLeftBank)
						{
							shiftFarmer80X-=delta*0.1f;
							if(shiftBoatX<335)
							{
								farmer80=movingRightFarmer80;
								shiftFarmer80X=farmer80OnLeftBank;
								shiftFarmer80Y=500-145;
								
							}
						}
						
						if(shiftAnimal20X<=animal20OnBoatRight && shiftAnimal20X>animal20OnLeftBank)
						{
							shiftAnimal20X-=delta*0.1f;
							if(shiftBoatX<335)
							{
								animal20=movingRightFarmer60;
								shiftAnimal20X=animal20OnLeftBank;
								
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
		return 5;
	}
}
