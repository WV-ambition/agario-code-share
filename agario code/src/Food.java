import java.awt.Color;
import java.awt.Graphics;

public class Food {
	// instance variables
		private int x, y; // x and y coordinates of the top left of the enemy
		private int r; // radius of the enemy
		private Color c; // color of the enemy

		//default constructor accepting 0 parameters
		public Food() {
			// randomizes x and y position, as well as radius of the enemy
			x = (int)(Math.random()*801); // random x position from 0 to 800
			y = (int)(Math.random()*601); // random y position from 0 to 600 
			r = 20; //random radius from 10 to 40
			
			/* randomizes a color for the enemy
			int red = (int)(Math.random()*256); // random R value from 0 to 255
			int green = (int)(Math.random()*256); // random G value from 0 to 255
			int blue = (int)(Math.random()*256); // random B value from 0 to 255
			*/
			int red = 0;
			int green = 0;
			int blue = 255;
			
			c = new Color(red,green,blue); // sets the color of the enemy using the random RGB values 
		}

		//paint method
		public void paint(Graphics g) {
			update(); // method that helps updating variables

			g.setColor(c); // sets the paint color to color stored in c
			
			g.fillOval(x, y, r*2, r*2); // draws the enemy
		}
		
		// anything that updates the variables of this object 
		public void update() {}
		
		//updates the position relative to the player cell
		public void updatePos(int pvx, int pvy) {
			x -= pvx;
			y -= pvy;
		}
		
		// method to detect if two cells are colliding
		public boolean collide(Enemy e) {
	        // finds the center coordinates of both cells
			int ax = x + r; // x coordinate of center of cell a
	        int ex = e.getX() + e.getR(); // x coordinate of center of cell e
	        int ay = y + r; // y coordinate of center of cell a
	        int ey = e.getY() + e.getR(); // y coordinate of center of cell e
	        
	        //calculates the distance between the centers of the two cells
	        double dx = Math.abs(ax - ex); // x distance between the center of the two cells
	        double dy = Math.abs(ay - ey); // y distance between the center of the two cells
	        double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)); // distance between centers using the distance formula
	        
	        //return centers collide
	        if (d <= Math.max(r, e.getR())) return true; //if the distance is less than or equal to the radius of the larger cell return true
	        else return false; //else the distance is larger than the radius of the larger cell so return false
	    }
		
		//method to combine two enemies and return the combined enemy's radius
		public int combine(Enemy e) {
			double a1 = r * r * Math.PI; // area of the food cell
			double a2 = e.getR() * e.getR() * Math.PI; // area of the enemy cell
			double anew = a1 + a2; // combined area
			int newr = (int) Math.sqrt(anew / Math.PI); // radius of combined enemy
			return newr; // returns new radius
		}
		
		//getter and setters
		public int getX() {return x;} // getter for x variable
		public void setX(int x) {this.x = x;} // setter for x variable 

		public int getY() {return y;} // getter for y variable 
		public void setY(int y) {this.y = y;} // setter for y variable

		public int getR() {return r;} // getter for r variable
		public void setR(int r) {this.r = r;} // setter for r variable

		public Color getC() {return c;} // getter for c variable
		public void setColor(Color c) {this.c = c;} // setter for c variable
	}
