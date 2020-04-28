package Engine;
import java.awt.Color;
import java.awt.Graphics;

import Art.Art;
import Art.Tile;

public class GridMap {
	private static final int GRID_WIDTH = 64;
	private static final int GRID_HEIGHT = 64;

	public int[][] grid;
	//private List resources;

	public GridMap() {
		grid = new int[10][10];
	}

	public GridMap(int[][] map) {
		grid = map;
	}

	public void render(Graphics g, Camera c) {
		g.setColor(Color.BLACK);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				g.drawImage(Tile.TILE_MAP.get(grid[i][j]).getImage(), j * (GRID_WIDTH) - c.getX(), i * (GRID_WIDTH) - c.getY(), GRID_WIDTH, GRID_HEIGHT, null);

				//				if (grid[i][j] == 0) {//Art.COLORMAP.get(new Color(0,255,0))
				//					g.drawImage(Art.SPRITE[2][0], j * (GRID_WIDTH) - c.getX(), i * (GRID_WIDTH) - c.getY(), GRID_WIDTH, GRID_HEIGHT, null);
				//				}
				//				if (grid[i][j] == 1) {
				//					g.drawImage(Art.SPRITE[2][2], j * (GRID_WIDTH) - c.getX(), i * (GRID_WIDTH) - c.getY(), GRID_WIDTH, GRID_HEIGHT, null);
				//				}
				g.drawRect(j * GRID_WIDTH - c.getX(), i * GRID_HEIGHT - c.getY(), GRID_WIDTH, GRID_HEIGHT);
			}
		}
	}

	public void tick () {}

	public void click(int x, int y, Camera c) {	}


	//	private void printInts(int... args) {
	//		for (int i = 0; i < args.length; i++) {
	//			System.out.print(args[i]);
	//			if (i < args.length) {
	//				System.out.print(", ");
	//			}
	//		}
	//		System.out.print("\n");
	//	}

}
