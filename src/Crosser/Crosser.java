package Crosser;

import java.awt.image.BufferedImage;

public abstract class Crosser implements ICrosser {

	protected int eatingRank;
	protected int weight;
	protected boolean canSail;
	protected String label;
	BufferedImage[] images;
	
	public boolean canSail() {
		return canSail;
	}
	public double getWeight() {
		return weight;
	}
	
	public BufferedImage[] getImages() {
		return images;
	}
	public ICrosser makeCopy() {
		return this;
	}
	public void setLabelToBeShown(String label) {
		
		this.label=label;
	}
	public String getLabelToBeShown() {
		return label;
	}
	
	public int getEatingRank() {
		return eatingRank;
	}
	public boolean isCanSail() {
		return canSail;
	}

}
