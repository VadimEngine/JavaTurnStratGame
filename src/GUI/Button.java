package GUI;
import java.awt.Color;
import java.awt.Graphics;

public class Button {
	
	private int x;
	private int y;
	private String text;
	
	private int width = 32;
	private int height = 16;
	
	public Button(int x, int y, String text) {//pass in width to keep buttons uniform
		this.x = x;
		this.y = y;
		this.text = text;
		this.width = text.length() * 10;
	}
	
	public Button(int x, int y, String text, int width) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.width = width;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.drawString(text, x + 2, y+14);
	}
	
	public boolean click(int x, int y) {//interface to pass in a click method
		boolean xCheck = x >= this.x && x <= this.x + width;
		boolean yCheck = y >= this.y && y <= this.y + height;
		return xCheck && yCheck;
	}

}
