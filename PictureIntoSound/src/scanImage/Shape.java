package scanImage;

public class Shape{
	int size;
	String color;
	String name;
	Pixel pixel;
	
	public Shape(){
		size = 0;
		color = "";
		name = "";
		pixel = new Pixel(0,0);
	}
	
	public Shape(int size, String color, String name, Pixel pixel){
		this.size = size;
		this.color = color;
		this.name = name;
		this.pixel = pixel;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pixel getPixel() {
		return pixel;
	}

	public void setPixel(Pixel pixel) {
		this.pixel = pixel;
	}

}