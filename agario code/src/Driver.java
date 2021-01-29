import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements MouseListener, ActionListener{
	// instance variables
	Enemy[] enemies = new Enemy[10]; // initiate an array of 50 enemies
	Food[] food = new Food[10];
	Player p = new Player();
	
	
	//paint method
	public void paint(Graphics g) {
		super.paintComponent(g); // proper redrawing of the entire screen
		
		double theta;
		
		boolean onx = false;
		boolean ony = false;
		int mx = MouseInfo.getPointerInfo().getLocation().x;
		int my = MouseInfo.getPointerInfo().getLocation().y;
		
		int px = p.x + p.pr;
		int py = p.y + p.pr;
		
		if (mx - px == 0) onx = true;
		if (my - py == 0) {
			ony = true;
			theta = Math.PI / 2;
		}
		else theta = Math.atan((mx-px)/(my-py));
		
		
		p.vx = (int) ((200 / p.pr) * Math.sin(theta));
		p.vy = (int) ((200 / p.pr) * Math.cos(theta));
		
		p.vx = Math.abs(p.vx) + 1;
		p.vy = Math.abs(p.vy) + 1;
		
		if (mx < px + p.pr) p.vx *= -1;
		if (my < py + p.pr) p.vy *= -1;
		
		if (onx) p.vx = 0;
		if (ony) p.vy = 0;
		System.out.println(p.vx + ":" + p.vy);
		
		
	
		p.paint(g);
		
		for (Enemy e : enemies) {
			if (p.collide(e)) {
				p.setX(p.x - p.combine(e) + p.pr);
				p.setY(p.y - p.combine(e) + p.pr);
				p.setPr(p.combine(e));
				e.setX(100000);
			}
			
			e.updatePos(p.vx, p.vy);
			e.paint(g); //go through enemies array and invoke paint method for each enemy			
		}
		Enemy ei, ej;
		
		for (Food f : food) {
			if (p.collide(f)) {
				p.setX(p.x - p.combine(f) + p.pr);
				p.setY(p.y - p.combine(f) + p.pr);
				p.setPr(p.combine(f));
				f.setX(100000);
			}

			f.updatePos(p.vx, p.vy);
			f.paint(g);
		}
		Food fj;
		// a double for loop in order to check every possible duo of enemies in the array for collisions
		for(int i = 0; i < enemies.length; i++) {
			ei = enemies[i];
			for (int j = i + 1; j < enemies.length; j++) {
				
				ej = enemies[j];
				//test if the enemies at index i and j collide
				if (ei.collide(ej) == true) {
					if (ei.getR() > ej.getR()) {
						// enemies[i].setR(ei.getR() + enemies[j].getR());
						enemies[i].setX(ei.getX() - (ei.combine(ej) - ei.getR())); // keeps the x coordinate of center the same
						enemies[i].setY(ei.getY() - (ei.combine(ej) - ei.getR())); // keeps the y coordinate of center the same
						enemies[i].setR(ei.combine(ej)); // sets a new radius that adds the two areas of the enemies
						enemies[j].setX(100000); // if the enemy at index i is larger than the enemy at index j, delete the enemy at index j
					}
					if (ej.getR() > ei.getR()) {
						enemies[j].setX(ej.getX() - (ej.combine(ei) - ej.getR())); // keeps the x coordinate of center the same
						enemies[j].setY(ej.getY() - (ej.combine(ei) - ej.getR())); // keeps the y coordinate of center the same
						enemies[j].setR(ej.combine(ei)); // sets a new radius that adds the two areas of the enemies
						enemies[i].setX(100000); // if the enemy at index j is larger than the enemy at index i, delete the enemy at index i
					}
				}
			
			
				
			}
			
			for (int j = 0; j < food.length; j++) {
				fj = food[j];
				if (fj.collide(ei)) {
					enemies[i].setX(ei.getX() - (fj.combine(ei) - ei.getR())); // keeps the x coordinate of center the same
					enemies[i].setY(ei.getY() - (fj.combine(ei) - ei.getR())); // keeps the y coordinate of center the same
					enemies[i].setR(fj.combine(ei)); // sets a new radius that adds the two areas of the enemies
					food[j].setX(100000); // if the enemy at index i is larger than the enemy at index j, delete the enemy at index j
				}
			}
			
			if (enemies[i].getR() != 0) enemies[i].update();
		}
	}

	public Driver(){
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800,600);
		frame.add(this);
		
		// places a default (randomized) enemy at every position in the array
		for (int i = 0; i < enemies.length; i++) enemies[i] = new Enemy();
		for (int i = 0; i < enemies.length; i++) food[i] = new Food();
		Timer t = new Timer(16, this); //chose swing library for import
		t.start();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	// main method
	public static void main(String[] arg) {
		Driver d = new Driver();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("here");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint(); //Timer will invoke this method which then refreshes the screen for the "animation"
	}
	
}