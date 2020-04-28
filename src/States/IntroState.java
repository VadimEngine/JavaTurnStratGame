package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import Engine.Handler;

public class IntroState implements State {
	
	private static final int MAX_COUNT = 200;
	private int counter;
	private Handler handler;
	
	public IntroState(Handler handler) {
		this.handler = handler;
	}
	
	
	public void tick() {
		counter++;
		if (counter > MAX_COUNT) {
			handler.state = new GameState(handler);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans", 1, 54));
		g.drawString("Vadim Games Inc.\u00a9", 640/2 - 248, 640/2 - 32);
	}


	@Override
	public void click(int x, int y) {
		handler.state = new GameState(handler);
		
	}


	@Override
	public void mouseMove(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
