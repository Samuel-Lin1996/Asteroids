package asteroids;

import java.awt.Graphics;

public class Asteroid extends Polygon {

	public Asteroid(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		shape = inShape;
		position = inPosition;
		rotation = inRotation;
	}
	public void move(Graphics brush){
		this.position.setX(this.position.getX()+2*Math.cos(Math.toRadians(rotation*0.5+10)));
		this.position.setY(this.position.getY()+2*Math.sin(Math.toRadians(rotation*0.5+10)));
		
		if(this.position.getX() > 800){
			this.position.setX(0);
		}
		if(this.position.getX() < 0){
			this.position.setX(800);
		}
		if(this.position.getY() > 600){
			this.position.setY(0);
		}
		if(this.position.getY() < 0){
			this.position.setY(600);
		}
	}

}
