package States;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface State {
	
	void tick();
	
	void render(Graphics g);
	
	void click(int x, int y);

	void mouseMove(MouseEvent e);

}
