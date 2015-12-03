package scanImage;

public class Pixel {
	int x;
	int y;
	
	public Pixel(){
		x = 0;
		y = 0;
	}
	
	public Pixel(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
