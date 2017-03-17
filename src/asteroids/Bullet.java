package asteroids;

import java.awt.Graphics;
import java.util.Random;

public class Bullet extends Polygon{
	

	public Bullet(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape,inPosition, inRotation);
		shape = inShape;
		position = inPosition;
		rotation = inRotation;
	}
	public void move(Graphics brush){
		this.position.setX(this.position.getX()+4*Math.cos(Math.toRadians(rotation)));
		this.position.setY(this.position.getY()+4*Math.sin(Math.toRadians(rotation)));
	}
	public boolean Checkcollision(Polygon [] arr){
		Random rand = new Random();
		int n = rand.nextInt(8);
		int d = rand.nextInt(2);
		for(int i =0; i<arr.length; i++){
			if(this.collision(arr[i])){
				this.position.setX(900);
				this.position.setY(900);
				this.rotation = 0;
				if(d == 0){
					arr[i].position.setX(100*n);
					arr[i].position.setY(0);
				}
				if(d == 1){
					arr[i].position.setX(0);
					arr[i].position.setY(50*n);
				}
				return true;
			}
		}
		
		return false;
	}
}