package fhlr.ponguorino;

public class Balle {
	
	private int width;
	private int height;
	
	private int x;
	private int y;
	
	private float vx;
	private float vy;
	
	public Balle(int width, int height, int x, int y) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		
		this.vx = 0;
		this.vy = 0;
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void bouger() {
		x -= vx;
		y -= vy;
	}
		
	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public void accelerer() {
		vx *= 1.3;
		vx *= 1.3;
	}
	
	
}
