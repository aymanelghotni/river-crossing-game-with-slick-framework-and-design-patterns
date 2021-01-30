package Crosser;

import java.util.ArrayList;
import java.util.List;

public class Scene2 implements ICrossingStrategy{
	
	public Scene2() {
		
	}
	
	
	
	public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers,List<ICrosser> boatRiders) {
		if(boatRiders.size()>2)
			return false;
		boolean flag=false;
		for(ICrosser crosser : boatRiders)
			if(crosser.canSail())
				flag=true;
		int totalWeight=0;
		for(ICrosser crosser : boatRiders)
			totalWeight+=crosser.getWeight();
		if(totalWeight>100)
			return false;
		return flag;
	
	}
	public List<ICrosser> getInitialCrossers() {
		List<ICrosser> c=new ArrayList<ICrosser>();
		CrosserFactory factory=new CrosserFactory();
		c.add(factory.makeCrosser("Person",90));
		c.add(factory.makeCrosser("Person",80));
		c.add(factory.makeCrosser("Person",60));
		c.add(factory.makeCrosser("Person",40));
		c.add(factory.makeCrosser("Animal", 20));
		return c;
	}
	public String[] getInstructions() {
		String[] instructions= {"You should move all crossers to the other side","The raft can only hold maximum weight of 100KGs","All 4 people can sail but the animal cannot","How will you do that?"};
		
		return instructions;
	}
	
}
