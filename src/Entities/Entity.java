package Entities;
import java.awt.Graphics;

import Engine.Camera;

public interface Entity {
	
	void render(Graphics g, Camera c);
	
	void tick();
	
	int getX();
	
	int getY();
	
	String getName();
	
	void setX(int x);
	
	void setY(int y);

}
