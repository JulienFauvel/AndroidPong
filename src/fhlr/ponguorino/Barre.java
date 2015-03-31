package fhlr.ponguorino;

public class Barre {
	
	private static final int WIDTH = 128;
	private static final int HEIGHT = 32;
	
	private int width;
	private int height;
	
	private int x;
	private int y;
	
	public Barre(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public Barre(int x, int y) {
		this.x = x;
		this.y = y;
		
		this.width = WIDTH;
		this.height = HEIGHT;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return x;
	}

	public int getRX() {
		return x - width/2;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRY() {
		return y - height/2;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}
