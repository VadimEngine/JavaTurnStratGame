package Engine;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Art.Art;

public class Unit {//pass in map
	
	//draw x, cord x...
	
	private static final BufferedImage UNIT = Art.SPRITE[0][0];
	private static final int WIDTH = 64;
	private static final int HEIGHT = 64;	
	
	private int x;
	private int y;
	private String name;
	
	private int actionTurn = 0;
	
	public Unit(int x, int y) {
		this.x = x;
		this.y = y;
		this.name = "Unit";
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, Camera c) {
		g.drawImage(UNIT, (x * (WIDTH)) - c.getX(), (y * (HEIGHT)) - c.getY(), WIDTH, HEIGHT, null);		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTurn() {
		return actionTurn;
	}
	
	public void setTurn(int turn) {
		actionTurn = turn;
	}
	
	public boolean canAct(int turn) {
		return actionTurn != turn;//less than
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public BufferedImage getImage() {
		return UNIT;
	}

}
