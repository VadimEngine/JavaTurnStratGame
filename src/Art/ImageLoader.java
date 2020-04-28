package Art;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage load(String file) {
		BufferedImage image = null;
		try {
			
			InputStream input = ImageLoader.class.getClassLoader().getResourceAsStream(file);
			image = ImageIO.read(input);
			//image = ImageIO.read(new File("./res/" + file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
