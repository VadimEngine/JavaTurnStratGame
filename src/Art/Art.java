package Art;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Art {
	
	private Art() {}
	
	private static final int SPRITE_WIDTH = 16;
	
	private static final Color GREEN = new Color(0,255,0);
	private static final Color DARK_GREEN = new Color(0,100,0);
	private static final Color BLUE = new Color(0,0,255);
	private static final Color MEDIUM_GREEN = new Color(0,155,0);
	private static final Color YELLOW = new Color(255,255,0);
	private static final Color BROWN = new Color(155,155,55);
	private static final Color WHITE = new Color(255,255,255);
	private static final Color GRAY = new Color(155,155,155);
	
	public static BufferedImage SPRITE[][] = loadImages("Sheet.png");
	
	public static final BufferedImage MAP_IMG = ImageLoader.load("Map.png");
	
	public static Color[][] MAP = loadMap(MAP_IMG);
	public static HashMap<Color, BufferedImage> COLORMAP = loadColorMap();
	
	private static BufferedImage[][] loadImages(String file) {
		BufferedImage[][] sprites = new BufferedImage[8][8];
		BufferedImage image = ImageLoader.load(file);
		for (int i = 0; i < image.getHeight() / SPRITE_WIDTH; i++) {
			for (int j = 0; j < image.getWidth() / SPRITE_WIDTH; j++) {
				sprites[i][j] = cleanImage(image.getSubimage(j * 16, i * 16, 16, 16));
			}
		}
		return sprites;
	}
	
	private static BufferedImage cleanImage(BufferedImage image) {
		boolean hasAlpha = false;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);//
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				if (image.getRGB(j, i) != 0xFFFF00FF && image.getRGB(j, i) != 0xFFC800C8) {
					newImage.setRGB(j, i, image.getRGB(j, i));
				} else {
					hasAlpha = true;
				}
			}
		}
		if (hasAlpha) {
			return newImage;
		} else {
			return image;
		}
	}
	
	private static Color[][] loadMap(BufferedImage image) {
		final int width = image.getWidth();
		final int height = image.getHeight();
		Color[][] map = new Color[width][height];
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				map[i][j] = new Color(image.getRGB(i, j));
			}
		}

		return map;
	}
	
	private static HashMap<Color, BufferedImage> loadColorMap() {
		HashMap<Color, BufferedImage> colorMap = new HashMap<>();
		colorMap.put(GREEN, Art.SPRITE[2][0]);
		colorMap.put(MEDIUM_GREEN, Art.SPRITE[2][1]);
		colorMap.put(BLUE, Art.SPRITE[2][2]);
		colorMap.put(DARK_GREEN, Art.SPRITE[2][3]);
		colorMap.put(YELLOW, Art.SPRITE[2][4]);
		colorMap.put(BROWN, Art.SPRITE[2][5]);
		colorMap.put(WHITE, Art.SPRITE[2][6]);
		colorMap.put(GRAY, Art.SPRITE[2][7]);
		return colorMap;
	}
	
}
