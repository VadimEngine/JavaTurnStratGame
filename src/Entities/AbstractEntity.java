package Entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Engine.Camera;

public abstract class AbstractEntity implements Entity {

	private static final int HEIGHT = 64;
	private static final int WIDTH = 64;
	
	protected int x;
	protected int y;
	protected String name;
	protected BufferedImage image;
		
	protected AbstractEntity(int x, int y, String name, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.image = image;
	}
	
	@Override
	public void render(Graphics g, Camera c) {
		g.drawImage(image, (x * WIDTH) - c.getX(), (y * HEIGHT) - c.getY(), WIDTH, HEIGHT, null);
	}
	
	@Override
	public void tick() {
		
	}
	
	@Override
	public int getX() {
		return x;
	}
	
	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public void setY(int y) {
		this.y = y;
	}
	
	public BufferedImage getImage() {
		return image;
	}
		
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}

}
