import java.awt.Graphics;
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
	Enemy[] enemies = new Enemy[50]; // initiate an array of 50 enemies
	
	//paint method
	public void paint(Graphics g) {
		super.paintComponent(g); // proper redrawing of the entire screen
		
		for (Enemy e : enemies) e.paint(g); //go through enemies array and invoke paint method for each enemy
		Enemy ei, ej;
		// a double for loop in order to check every possible duo of enemies in the array for collisions
		for(int i = 0; i < 50; i++) {
			for (int j = i + 1; j < 50; j++) {
				ei = enemies[i];
				ej = enemies[j];
				//test if the enemies at index i and j collide
				if (ei.collide(ej) == true) {
					if (ei.getR() > ej.getR()) {
						// enemies[i].setR(ei.getR() + enemies[j].getR());
						enemies[i].setR(ei.combine(ej)); // sets a new radius that adds the two areas of the enemies
						enemies[i].setX(ei.getX() - (ei.combine(ej) - ei.getR()) / 2); // keeps the x coordinate of center the same
						enemies[i].setY(ei.getY() - (ei.combine(ej) - ei.getR()) / 2); // keeps the y coordinate of center the same
						enemies[j].setR(0); // if the enemy at index i is larger than the enemy at index j, delete the enemy at index j
					}
					if (ej.getR() > ei.getR()) {
						enemies[j].setR(ei.combine(ej)); // sets a new radius that adds the two areas of the enemies
						enemies[j].setX(ei.getX() - (ei.combine(ej) - ei.getR()) / 2); // keeps the x coordinate of center the same
						enemies[j].setY(ei.getY() - (ei.combine(ej) - ei.getR()) / 2); // keeps the y coordinate of center the same
						enemies[i].setR(0); // if the enemy at index j is larger than the enemy at index i, delete the enemy at index i
					}
				}
			}
		}
	}

	public Driver(){
		JFrame frame = new JFrame("Agar.io");
		frame.setSize(800,600);
		frame.add(this);
		
		// places a default (randomized) enemy at every position in the array
		for (int i = 0; i < enemies.length; i++) enemies[i] = new Enemy();
		
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