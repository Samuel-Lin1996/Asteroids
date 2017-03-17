package asteroids;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
//Uses W,A,D to move.
public class Ship extends Polygon implements KeyListener {
	boolean isKeyA = false;
	boolean isKeyD = false;
	boolean isKeyW = false;
	boolean isSpace = false;
	Point[] s = {new Point(0,0), new Point(5,0), new Point(5,3),new Point(0,3)};
	Bullet bullet;
	//boolean ok = false;
	//Bullet [] ammo = new Bullet[5];
	//int currentbullet = 0;
	LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	
	
	public Ship(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape,new Point(400,300), 0);	
		shape = inShape;
		position = inPosition;
		rotation = inRotation;
	}
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();
		switch(keyCode){
			case KeyEvent.VK_A:
				isKeyA = true;
				break;
			case KeyEvent.VK_D:
				isKeyD = true;
				break;
			case KeyEvent.VK_W:
				isKeyW = true;
				break;
			case KeyEvent.VK_SPACE:
				break;
			default:
				break;
		}
				
	}
	public void keyReleased(KeyEvent e){
		int keyCode = e.getKeyCode();
		switch(keyCode){
			case KeyEvent.VK_A:
				isKeyA = false;
				break;
			case KeyEvent.VK_D:
				isKeyD = false;
				break;
			case KeyEvent.VK_W:
				isKeyW = false;
				break;
			case KeyEvent.VK_SPACE:
				//if(!(ok)){
					bullet = new Bullet(s,this.position.clone(), this.rotation);
					bullets.add(bullet);
					//currentbullet++;
					isSpace = true;
				//}
				break;
			default:
				break;
				
		}
	}
	public void keyTyped(KeyEvent e){
	}
	public boolean Checkcollision(Polygon [] arr){
		for(int i =0; i<arr.length; i++){
			if(this.collision(arr[i])){
				this.position.setX(400);
				this.position.setY(300);
				this.rotation = 0;
				for(int j =0; j<arr.length; j++){
					arr[0].position.setX(100);
					arr[0].position.setY(100);
					arr[1].position.setX(200);
					arr[1].position.setY(150);
					arr[2].position.setX(600);
					arr[2].position.setY(150);
					if(arr.length >= 4){
						arr[3].position.setX(300);
						arr[3].position.setY(200);
					}
					if(arr.length >= 5){
						arr[4].position.setX(113);
						arr[4].position.setY(0);
					}
					if(arr.length >= 6){
						arr[5].position.setX(15);
						arr[5].position.setY(590);
					}
					if(arr.length >= 7){
						arr[6].position.setX(135);
						arr[6].position.setY(0);
					}
					if(arr.length >= 8){
						arr[7].position.setX(0);
						arr[7].position.setY(25);
					}
					if(arr.length >= 9){
						arr[8].position.setX(0);
						arr[8].position.setY(158);
					}
					if(arr.length >= 10){
						arr[9].position.setX(0);
						arr[9].position.setY(65);
					}					
				}
				return true;
			}
			}	
		return false;
	}
	public void move(Graphics brush){
		if(isKeyA){
			this.rotate(-2);
		}
		if(isKeyD){
			this.rotate(2);
		}
		if(isKeyW){
			this.position.setX(this.position.getX()+2*Math.cos(Math.toRadians(rotation)));
			this.position.setY(this.position.getY()+2*Math.sin(Math.toRadians(rotation)));
		}
		if(isSpace){
			//code below is for shooting infinite times.
			for(int i=0; i<bullets.size(); i++){
				bullets.get(i).paint(brush);
				bullets.get(i).move(brush);
				if(bullets.get(i).position.getX() > 800 || bullets.get(i).position.getX() < 0 || bullets.get(i).position.getY() > 600 ||bullets.get(i).position.getY() < 0){
					bullets.remove(i);
				}
			}
		}
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
