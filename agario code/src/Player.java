import java.awt.Color;
import java.awt.Graphics;

public class Player {
	// instance variables
    public int x, y; // x and y coordinates of the upper left of player
    public int vx, vy; // x and y speed of the player
	public int pr = 50; // radius of the player
    private Color c = new Color(255, 0, 255); // sets color of player to magenta

    public Player() {
        x = 800 / 2 - pr; // sets x coordinate to middle of the screen
        y = 600 / 2 - pr; // sets y coordinate to middle of the screen
    }

    public void paint(Graphics g) {
        g.setColor(c); // sets the color to c
        g.fillOval(x, y, pr * 2, pr * 2); // draws the player
    }


    // method to detect if two cells are colliding
	public boolean collide(Enemy e) {
        // finds the center coordinates of both cells
		int ax = x + pr; // x coordinate of center of cell a
        int ex = e.getX() + e.getR(); // x coordinate of center of cell e
        int ay = y + pr; // y coordinate of center of cell a
        int ey = e.getY() + e.getR(); // y coordinate of center of cell e
        
        //calculates the distance between the centers of the two cells
        double dx = Math.abs(ax - ex); // x distance between the center of the two cells
        double dy = Math.abs(ay - ey); // y distance between the center of the two cells
        double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)); // distance between centers using the distance formula
        
        //return centers collide
        if (d <= Math.max(pr, e.getR())) return true; //if the distance is less than or equal to the radius of the larger cell return true
        else return false; //else the distance is larger than the radius of the larger cell so return false
    }
	
	//method to combine two enemies and return the combined enemy's radius
	public int combine(Enemy e) {
		double a1 = pr * pr * Math.PI; // area of the food cell
		double a2 = e.getR() * e.getR() * Math.PI; // area of the enemy cell
		double anew = a1 + a2; // combined area
		int newr = (int)Math.sqrt(anew / Math.PI); // radius of combined enemy
		return newr; // return new radius
	}
	
	// method to detect if two cells are colliding
	public boolean collide(Food f) {
        // finds the center coordinates of both cells
		int ax = x + pr; // x coordinate of center of cell a
        int ex = f.getX() + f.getR(); // x coordinate of center of cell e
        int ay = y + pr; // y coordinate of center of cell a
        int ey = f.getY() + f.getR(); // y coordinate of center of cell e
        
        //calculates the distance between the centers of the two cells
        double dx = Math.abs(ax - ex); // x distance between the center of the two cells
        double dy = Math.abs(ay - ey); // y distance between the center of the two cells
        double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)); // distance between centers using the distance formula
        
        //return centers collide
        if (d <= Math.max(pr, f.getR())) return true; //if the distance is less than or equal to the radius of the larger cell return true
        else return false; //else the distance is larger than the radius of the larger cell so return false
    }
	
	//method to combine two enemies and return the combined enemy's radius
	public int combine(Food f) {
		double a1 = pr * pr * Math.PI; // area of the player cell
		double a2 = f.getR() * f.getR() * Math.PI; // area of the enemy cell
		double anew = a1 + a2; // combined area
		int newr = (int)Math.sqrt(anew / Math.PI); // radius of combined enemy
		return newr; // return new radius
	}
	
    //getters and setters
    public int getX() {return x;}
    public void setX(int x) {this.x = x;}

	public int getY() {return y;}
	public void setY(int y) {this.y = y;}

	public int getVx() {return vx;}
	public void setVx(int vx) {this.vx = vx;}

	public int getVy() {return vy;}
	public void setVy(int vy) {this.vy = vy;}

	public int getPr() {return pr;}
	public void setPr(int pr) {this.pr = pr;}

	public Color getC() {return c;}
	public void setC(Color c) {this.c = c;}
}

