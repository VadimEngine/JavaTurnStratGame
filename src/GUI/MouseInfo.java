package GUI;

import java.awt.Color;
import java.awt.Graphics;

public class MouseInfo {
	
	private String[] info;
	
	public MouseInfo(String[] info) {
		this.info = info;
	}
	
	public void render(Graphics g, int x, int y) {
		int height = info.length * 16;
		int width = 72;
		
		g.setColor(Color.gray);
		g.fillRect(x + 8, y + 8, width, height);
		g.setColor(Color.BLACK);
		for (int i = 0; i < info.length; i++) {
			g.drawString(info[i], x + 8 + 4, y  + 8 + 16 +  i * 16);
		}
	}
	
	public void setInfo(String[] info) {
		this.info = info;
	}
	
	public void addInfo(String[] info) {
		this.info = merge(this.info, info);
	}
	
	public boolean hasInfo() {
		return info != null;
	}
	
	private String[] merge(String[] a, String[] b) {
		int len1 = a.length;
		int len2 = b.length;
		String[] merge = new String[len1 + len2];
		for (int i = 0; i < len1; i++) {
			merge[i] = a[i];
		}
		for (int i = 0; i < len2; i++) {
			merge[i + len1] = b[i]; 
		}
		return merge;
	}

}
