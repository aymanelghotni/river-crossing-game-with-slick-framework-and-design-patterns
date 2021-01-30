package Crosser;

import java.util.ArrayList;
import java.util.List;

public class Scene1 implements ICrossingStrategy{

	

	public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers,List<ICrosser> boatRiders) {
		if(boatRiders.size()>2)
		{

			return false;
		}
			
		int i=0;
		boolean b=false;
		if(leftBankCrossers.size()>1)
		{
			for (ICrosser iCrosser : leftBankCrossers) {
				i+=iCrosser.getEatingRank();	
				}
				if(i%2!=0 || i>=6)
					return false;
		}
		
		i=0;
		if(rightBankCrossers.size()>1)
		{
			for (ICrosser iCrosser : rightBankCrossers) {
				i+=iCrosser.getEatingRank();	
				}
				if(i%2!=0)
					return false;
		}
		
		i=0;
		if(!boatRiders.isEmpty())
		{
			for (ICrosser iCrosser : boatRiders)
			{
				if(iCrosser.canSail())
					b=true;
			}
				
	
		}
		else if(boatRiders.isEmpty())
		{
			return false;
		}
		return b;
		
		
	}
	public List<ICrosser> getInitialCrossers() {
		List<ICrosser> c=new ArrayList<ICrosser>();
		CrosserFactory factory=new CrosserFactory();
		c.add(factory.makeCrosser("Farmer", 0));
		c.add(factory.makeCrosser("Cabbage", 0));
		c.add(factory.makeCrosser("Sheep", 0));
		c.add(factory.makeCrosser("Wolf", 0));
		return c;
	}
	public String[] getInstructions() {
		String[] instructions= {"You should move all crossers to the other side","The raft can only hold 2 passengers","Only the farmer can sail","You shouldn't leave any 2 crossers that can harm each other on the same side","The wolf can eat the sheep but can't eat the cabbage","and the sheep can eat the cabbage","and the farmer can neither eat or get eaten","How will you do that?"};
		//instructions.add("You should move all crossers to the other side");
		//instructions.add("The raft can only hold 2 passengers");
		//instructions.add("Only the farmer can sail");
		//instructions.add("You shouldn't leave any 2 crossers that can harm each other on the same side");
		//instructions.add("The wolf can eat the sheep but can't eat the cabbage and the sheep can eat the cabbage and the farmer can neither eat or get eaten");
		//instructions.add("How will you do that?");
		//String[] s=(String[]) instructions.toArray();
		return instructions;
	}
	
	
	
	
	
	
}
