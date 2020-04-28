package GUI;
import java.awt.Color;
import java.awt.Graphics;

import Art.Art;
import Engine.Camera;
import Entities.GameHut;
import Entities.GameUnit;
import States.GameState;

public class HUD {

	private static final int HEIGHT = 128;
	private static final int WIDTH = 256;

	private int x = 640-WIDTH - 8;
	private int y = 640-HEIGHT - 8;

	private Button button = new Button(x + 4, y + 100, "Next Turn");
	private Button hutButton = new Button(x + 4 + 32, y + 8 + 40, "Build");
	private Button btn_newGame = new Button(0,0, "New Game");
	private Button btn_quit = new Button(80,0, "Quit");

	private GameState state;


	public HUD(GameState state) {
		this.state = state;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		Camera c = ((GameState)state).camera;
		
		g.drawImage(Art.MAP_IMG, x - 64, y, 64, 64, null);
		g.drawRect(x-64 + (c.getX()/32), y + (c.getY()/32), 20, 20);//10x10
		g.setColor(Color.GRAY);
		g.fillRect(x, y, WIDTH, HEIGHT);

		g.setColor(Color.BLACK);
		g.drawRect(x, y, WIDTH, HEIGHT);
		g.drawRect(x + 8, y + 8, 32, 32);

		GameUnit u = state.getSelectedUnit();
		if (u != null) {
			g.drawImage(u.getImage(), x + 8, y + 8, 32, 32, null);
			g.drawString(u.getName(), x + 8 + 32 + 4, y + 8 +16);
			g.drawString("Move: " + u.canAct(state.getCurrentTurn()), x + 8 + 32  + 4, y + 8 +32);
			hutButton.render(g);

			String[] tasks = u.getTasks();
			for (int i = 0;i < tasks.length; i++) {
				new Button(x + 164, y + i*16, tasks[i], 64).render(g);
			}

		}

		g.drawString("Turn: " + state.getCurrentTurn(), x + 8 + 4, y + 100-16);

		button.render(g);

		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 640, 16);
		btn_newGame.render(g);
		btn_quit.render(g);
	}

	public void click(int x, int y) {
		GameUnit u = state.getSelectedUnit();
		if (button.click(x, y)) {
			state.nextTurn();
		}

		if (hutButton.click(x, y) && state.getSelectedUnit().canAct(state.getCurrentTurn())) {
			state.addHut(new GameHut(state.getSelectedUnit().getX(), state.getSelectedUnit().getY(), "Hut", Art.SPRITE[4][5]));
			state.getSelectedUnit().setTurn(state.getCurrentTurn());
		}

		if (btn_newGame.click(x, y)) {
			state.handler.state = new GameState(state.handler);
		}
		if (btn_quit.click(x, y)) {
			System.exit(0);
		}

		if (imageBound(x, y)) {
			if (u != null) {
				((GameState)state).camera.setX(u.getX()* 64-640/2);
				((GameState)state).camera.setY(u.getY()*64-640/2);
			}
		}


		if (u != null) {
			for (int i = 0; i < u.getTasks().length; i++) {
				if (new Button(this.x + 164, this.y + 16 + i*16, u.getTasks()[i], 64).click(x, y)) {
				}
			}
		}

	}

	public boolean imageBound(int x, int y) {//x + 8, y + 8, 32, 32
		boolean xCheck = x >= this.x + 8 && x <= this.x + 32;
		boolean yCheck = y + 8 >= this.y && y <= this.y + 32;

		return xCheck && yCheck;
	}

	public boolean inBound(int x, int y) {
		boolean xCheck = x >= this.x && x <= this.x + WIDTH;
		boolean yCheck = y >= this.y && y <= this.y + HEIGHT;		
		boolean y2Check = y > 0 && y < 16;

		return (xCheck && yCheck) || (y2Check);
	}

}
