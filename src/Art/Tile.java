package Art;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Tile {
	
	public static List<Tile> TILE_MAP = buildMap();
	
	private int id;
	private BufferedImage image;
	private String name;
	
//	public Tile(int id) {
//		this.image = TILE_MAP.get(id).image;
//	}
	
	private Tile(int id, BufferedImage image, String name) {
		this.id = id;
		this.image = image;
		this.name = name;
	}
	
	
	private static List<Tile> buildMap() {
		List<Tile> tiles = new ArrayList<Tile>();
		tiles.add(new Tile(0,Art.SPRITE[2][0], "Grasslands"));
		tiles.add(new Tile(0,Art.SPRITE[2][2], "Sea"));
		
		return tiles;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public String getName() {
		return name;
	}
	
	//static get Tile(id)
	

}
