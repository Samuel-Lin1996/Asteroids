//Name: Samuel Lin
//Extra features: Background image, shooting bullet, bullet collision, lives, score, levels, gameover when no lives left.



package asteroids;
/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control cent
      er.
Original code by Dan Leyzberg and Art Simon
Modified by Megan Owen
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Asteroids extends Game {
	private int counter = 0;
	int life = 2;
	int Score = 0;
	int nextLevel = 1000;
	int Level = 1;
	int currentasteroid = 3;
	boolean isGame = true;
	Point[] p = {new Point(0,0), new Point(5,10), new Point(30,0), new Point(0,30),new Point(-5,20)};
	//Point[] Giant = {new Point(0,0), new Point(25,50), new Point(120,0),new Point(50,75), new Point(0,150),new Point(-25,100)};
	//Point[] octagon = {new Point(0,100), new Point(100,0), new Point(200,0),new Point(300,100), new Point(300,200),new Point(200,300), new Point(100,300),new Point(0,200)};
	Point[] s = {new Point(0,0), new Point(30,10), new Point(0,20),new Point(7,10)};
	Point mid = new Point(400,300);
	Ship ship = new Ship(s,new Point(400,300),.0);
	Polygon[] lives = {new Polygon(s, new Point(10,10),90.0),new Polygon(s, new Point(30,10),90.0)};
	Asteroid [] arr = {new Asteroid(p,new Point(100,100),0.0),new Asteroid(p,new Point(200,150),295.0),new Asteroid(p,new Point(600,150),120.0),
			new Asteroid(p,new Point(0,350),350.0),new Asteroid(p,new Point(113,0),180.0),new Asteroid(p,new Point(123,0),75.0),
			new Asteroid(p,new Point(205,0),360.0),new Asteroid(p,new Point(0,30),295.0),new Asteroid(p,new Point(763,0),287.0),
			new Asteroid(p,new Point(0,100),345.0)};
	Image background;
	public Asteroids() {
		super("Asteroids!",800,600);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(ship);
		try {
			background = ImageIO.read(new File("src//asteroids//background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics brush) {
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);
		
		// sample code for printing message- for debugging
		// counter is\ incremented and this message printed
		// each time the canvas is repainted
		counter++;
		brush.setColor(Color.white);
		brush.drawImage(background,0,0,null);
		brush.drawString("Score: " + Score, 700,10);
		brush.drawString("Counter is " + counter,10,10);
		if(Level < 8){
			brush.drawString("Level " + Level, 400, 10);
		}
		if(Level >= 8){
			brush.drawString("Level " + Level + " (Max Level)", 400, 10);
		}
		if(isGame){
			ship.paint(brush);
			ship.move(brush);
			for(int i=0; i<currentasteroid;i++){
				arr[i].paint(brush);
				arr[i].move(brush);
			}
			for(int j=0; j<life; j++){
				lives[j].paint(brush);
			}
			if(ship.Checkcollision(arr)){		//code for checking ship collision.
				life--;
			}
			for(int i=0; i<ship.bullets.size(); i++){				//code for checking collision for the infinite ammo
				if(ship.bullets.get(i).Checkcollision(arr)){
					Score = Score + 100;
				}
			}
			if(Score >= nextLevel){
				currentasteroid++;
				Level++;
				nextLevel = nextLevel + 1000*Level;
				if(currentasteroid == 10){
					nextLevel = 1000000000;
				}
			}
			if(life < 0){
				isGame = false;
			}
		}
		if(isGame == false){
			brush.drawString("Game Over!!!", 400, 300);
		}

	}


	public static void main (String[] args) {
		Asteroids a = new Asteroids();
		a.repaint();
	}
}