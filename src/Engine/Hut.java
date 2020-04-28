package Engine;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Art.Art;

public class Hut {
	
	private static final BufferedImage HUT = Art.SPRITE[4][5];
	
	private static final int HEIGHT = 64;
	private static final int WIDTH = 64;
	
	private int x;
	private int y;
	
	public Hut (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g, Camera c) {
		g.drawImage(HUT, (x * (WIDTH)) - c.getX(), (y * (HEIGHT)) - c.getY(), WIDTH, HEIGHT, null);
	}
	
	public void tick() {
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
