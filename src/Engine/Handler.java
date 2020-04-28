package Engine;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import States.IntroState;
import States.State;


/**
 * Handles the game. Controls the mouse and key actions.
 * 
 * @author Vadim Goncharuk
 *
 */
public class Handler implements KeyListener, MouseWheelListener, MouseMotionListener, MouseListener {

	private static final int MOVE_PAD = 16;
	//Graphics2D g2d = ((Graphics2D) g);
	//System.out.println(g2d.getDeviceConfiguration().getBounds().getWidth());
	private static final int SCREEN_HEIGHT = Game.HEIGHT;
	private static final int SCREEN_WIDTH = Game.WIDTH;

	public boolean move_left = false;
	public boolean move_right = false;
	public boolean move_down = false;
	public boolean move_up = false;
	
	public State state;
	

	/**
	 * Boolean array of all keycodes.
	 */
	public boolean keys[] = new boolean[65536];


	public Handler() {
//		int[][] arrayMap = buildMap();
//		map = new GridMap(arrayMap);
//		temphud = new HUD(map);
		state = new IntroState(this);
	}

	/**
	 * Renders the game content
	 * @param g graphics from the main render method.
	 */
	public void render(Graphics g) {
		//map.render(g, camera);
		//temphud.render(g);
		//state.render(g, camera);
		state.render(g);
	}

	/**
	 * Ticks game content.
	 */
	public void tick() {
		state.tick();
	}


	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		state.click(x, y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getX() < 0 + MOVE_PAD) {
			move_left = true;
		} else {
			move_left = false;
		}

		if (e.getX() > SCREEN_WIDTH - MOVE_PAD) {
			move_right = true;
		} else {
			move_right = false;
		}

		if (e.getY() < 0 + MOVE_PAD) {
			move_up = true;
		} else {
			move_up = false;
		}

		if (e.getY() > SCREEN_HEIGHT - MOVE_PAD) {
			move_down = true;
		} else {
			move_down = false;
		}
		
		state.mouseMove(e);
		
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
