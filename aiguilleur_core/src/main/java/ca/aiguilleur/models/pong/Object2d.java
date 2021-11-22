package ca.aiguilleur.models.pong;

import ca.ntro.core.path.ValuePath;

public class Object2d {
	
	private double centerX;
	private double centerY;
	
	private double speedX;
	private double speedY;

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public void updateValue(ValuePath valuePath, Object newValue) {
		if(valuePath.startsWith("centerX")) {

			setCenterX((double) newValue);

		}else if(valuePath.startsWith("centerY")){
			
			setCenterY((double) newValue);
			
		}else if(valuePath.startsWith("speedX")) {
			
			setSpeedX((double) newValue);
			
		}else if(valuePath.startsWith("speedY")) {
			
			setSpeedY((double) newValue);
		}
	}
}
