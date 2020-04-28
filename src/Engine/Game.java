package Engine;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * Main class of the game, includes the main method and the main tick and render methods.
 * Uses multi-threading to run a "run" method which controls the whole game.
 * 
 * @author Vadim Goncharuk
 *
 */
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 720;
	
	private boolean running;
	private Thread thread;
	private Handler handler = new Handler();
	private int fps = 0;

	/**
	 * Constructor, sets size of the canvas, uses Handler class as the key, mouse and focus listener.
	 * 
	 */
	public Game() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setSize(size);
		setPreferredSize(size);

		setFocusable(true);
		addKeyListener(handler);
		addMouseWheelListener(handler);
		addMouseListener(handler);
		addMouseMotionListener(handler);
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that runs the entire game, keeps track how often the methods are
	 * called and keep them at 60 calls per second or below.
	 */
	@Override
	public void run() {
		int frames = 0;
		double unprocessedSeconds = 0;
		long lastTime = System.nanoTime();
		double secondsPerTick = 1 / 60.0;
		int tickCount = 0;

		while (running) {
			long now = System.nanoTime();
			long passedTime = now - lastTime;
			lastTime = now;
			if (passedTime < 0) {
				passedTime = 0;
			}
			if (passedTime > 100000000) {
				passedTime = 100000000;
			}
			unprocessedSeconds += passedTime / 1000000000.0;
			boolean ticked = false;
			while (unprocessedSeconds > secondsPerTick) {
				tick();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;

				tickCount++;
				if (tickCount % 60 == 0) {
					fps = frames;
					lastTime += 1000;
					frames = 0;
				}
			}
			if (ticked) {
				render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Main tick method, controls the background calculations and object interactions.
	 * The calculations are handled and gathered by the handler class. 
	 */
	public void tick() {
		handler.tick();
	}

	/**
	 * Main render method, draws using triple buffering, draws the game information
	 * which is gathered and created by the Handler class.
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		handler.render(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", 1, 16));
		g.drawString("FPS: " + fps, 0, 32);
		g.dispose();
		bs.show();
	}

	/**
	 * Main method, builds the frame, adds the game, ands starts the game thread.
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();

		JFrame frame = new JFrame("Strat 2.0");
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(game, BorderLayout.CENTER);

		frame.setContentPane(panel);		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();	
	}

}
