package States;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Art.Art;
import Art.ImageLoader;
import Art.Tile;
import Engine.Camera;
import Engine.GridMap;
import Engine.Handler;
import Engine.Hut;
import Entities.GameHut;
import Entities.GameUnit;
import GUI.HUD;
import GUI.MouseInfo;

public class GameState implements State {

	private static final int GRID_WIDTH = 64;
	private static final int GRID_HEIGHT = 64;

	private GridMap map;
	private HUD hud = new HUD(this);
	public Camera camera;

	private List<GameUnit> units = new ArrayList<>();
	
	private List<GameHut> huts = new ArrayList<>();
	
	private GameUnit selectedUnit;
	private int currentTurn = 1;
	
	private int gold;
	private int food;
	
	private MouseInfo info = new MouseInfo(null);
	private int mouseX;
	private int mouseY;

	public Handler handler;

	public GameState(Handler handler) {
		int[][] arrayMap = buildMap();
		map = new GridMap(arrayMap);
		units.add(new GameUnit(0,0, "Settler", Art.SPRITE[0][0]));
		camera = new Camera(0, 0);
		this.handler = handler;
	}

	public void render(Graphics g) {
		map.render(g, camera);

		for (int i = 0; i < huts.size(); i++) {
			huts.get(i).render(g, camera);
		}

		for (int i = 0; i < units.size(); i++) {
			units.get(i).render(g, camera);
		}

		if (selectedUnit != null) {
			g.setColor(Color.BLUE);
			g.drawRect((selectedUnit.getX() * (64)) - camera.getX(), (selectedUnit.getY() * (64)) - camera.getY(),
					selectedUnit.getWidth(), selectedUnit.getHeight());
		}

		hud.render(g);
		
		if (info != null && info.hasInfo()) {
			info.render(g, mouseX, mouseY);
		}

	}

	public void tick() {
		if (handler.keys[KeyEvent.VK_UP]) {// || handler.move_up
			camera.setY(camera.getY() - 6);
			updateInfo();
		}

		if (handler.keys[KeyEvent.VK_DOWN]) {// || handler.move_down
			camera.setY(camera.getY() + 6);
			updateInfo();
		}

		if (handler.keys[KeyEvent.VK_LEFT]) {// || handler.move_left
			camera.setX(camera.getX() - 6);
			updateInfo();
		}

		if (handler.keys[KeyEvent.VK_RIGHT]) {// || handler.move_right
			camera.setX(camera.getX() + 6);
			updateInfo();
		}

	}

	public void click(int x, int y) {
		boolean selected = false;
		int cX = x + camera.getX();
		int cY = y + camera.getY();

		if (!hud.inBound(x, y)) {
			for (int i = 0; i < units.size(); i++) {//allow for moving on top of another unit, dont check if already selecting
				GameUnit u = units.get(i);
				if (inBound(cX, cY, u.getX(), u.getY(), u.getWidth(), u.getHeight())) {
					if (selectedUnit == u) {
						selectedUnit = null;
					} else {
						selectedUnit = u;
					}
					selected = true;
					break;
				} 
			}
			if (!selected && selectedUnit != null && selectedUnit.canAct(currentTurn)) {
				selectedUnit.setX((cX/(GRID_WIDTH)));
				selectedUnit.setY((cY/(GRID_HEIGHT)));
				selectedUnit.setTurn(currentTurn);
				selectedUnit = null;
			}
		} else {
			hud.click(x, y);
		}
	}

	public GameUnit getSelectedUnit() {
		return selectedUnit;
	}

	public void nextTurn() {
		selectedUnit = null;
		currentTurn++;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void addHut(GameHut hut) {
		huts.add(hut);
	}

	private int[][] buildMap() {
		Color blue = new Color(0,0,255);
		Color green = new Color(0,255,0);
		BufferedImage map = ImageLoader.load("Map.png");
		int[][] mapArray = new int[map.getHeight()][map.getWidth()];

		for (int i = 0; i< map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				if (map.getRGB(j, i) == blue.getRGB()) {
					mapArray[i][j] = 1; 
				} 
				if (map.getRGB(j, i) == green.getRGB()) {
					mapArray[i][j] = 0; 
				}
			}
		}
		return mapArray;
	}

	private boolean inBound(int x, int y, int thatX, int thatY, int thatWidth, int thatHeight) {
		boolean xCheck = x >= (thatX * (thatWidth)) && x <= (thatX * (thatWidth)) + thatWidth;
		boolean yCheck = y >= (thatY * (thatHeight)) && y <= (thatY * (thatHeight)) + thatHeight;
		return xCheck && yCheck;
	}

	@Override
	public void mouseMove(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();		
		updateInfo();
	}
	
	public void updateInfo() {
		int cX = mouseX + camera.getX();
		int cY = mouseY + camera.getY();
		if (cX >=0 && cX/64 < 32 && cY >= 0 && cY/64 < 32) {
			int dis = map.grid[cY/64][cX/64];
			info.setInfo(new String[]{Tile.TILE_MAP.get(dis).getName()});
			
			info.addInfo(new String[]{"Test", "test2", "test3"});//get units, building, resources
			
		} else {
			info.setInfo(null);
		}
		
	}
	

}
