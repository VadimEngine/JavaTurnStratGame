package Entities;

import java.awt.image.BufferedImage;

public class GameHut extends AbstractEntity implements Selectable {
	
	private int turn;
	private String[] tasks;

	public GameHut(int x, int y, String name, BufferedImage image) {
		super(x, y, name, image);
		turn = 0;
		tasks = new String[]{"Destroy", "Build Settler"};
	}
	
	public boolean canAct(int turn) {
		return this.turn < turn;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}

	@Override
	public String[] getTasks() {
		return tasks.clone();
	}

}
