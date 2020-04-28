package GUI;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import Engine.Camera;

public class Screen {
	
	private int[] pixels;
	private Camera camera;
	private BufferedImage image;
	
	private int height;
	private int width;
	
	private Random rand = new Random();
	
	public Screen(int  width, int height) {
		camera = new Camera(0,0);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		this.height = height;
		this.width = width;
	}
	
	public void render(Graphics g) {
		g.drawImage(image, 0, 0, width, height, null);
	}
	
	public void tick() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = rand.nextInt(0xFFFFFF);
		}
	}
	
	
	
	

}
