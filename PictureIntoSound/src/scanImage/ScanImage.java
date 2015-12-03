package scanImage;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ScanImage{
	static ArrayList<Shape> imageShapes = new ArrayList<Shape>();
	static int shapeSize;
	static Pixel startingPixel;
	
	public ArrayList<Shape> scan(String pictureSelected, String traversalDirection) throws IOException
	{
		shapeSize = 0;
		startingPixel = new Pixel(0,0);
		int width;
		int height;
		BufferedImage image = null;
		File f = null;
		
		
		
		
		//read image file	
		String pathToImage = "images/" + pictureSelected;
		ImageIcon icon = new ImageIcon(pathToImage);
		width = icon.getIconWidth();
		height = icon.getIconHeight();
		f = new File(pathToImage);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		BufferedImage gray = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);//
		Graphics g = image.getGraphics();//
		g.drawImage(image, 0, 0, null);//
		g.dispose();//
		
		try{	
			image = ImageIO.read(f);
			System.out.println("Reading complete.");	
		}catch(IOException e){
			System.out.println("Error: "+e);
		}
		
		//write image
/*		try{
			f = new File("C:\\Users\\Brian\\Documents\\School\\EE 371R\\PictureIntoSoundProject\\output2.jpg");
			ImageIO.write(image, "jpg", f);
		}catch(IOException e){
			System.out.println("Error "+e);
		}
*/		
		//begin scanning image
		int[][] imageCheck = new int[height][width];
		int[][] histogramValues = new int[height][width];
		String[][] pixelColor = new String[height][width];
		initializeImageArray(imageCheck);
		initializeColorArray(pixelColor, histogramValues, image);
		if(traversalDirection == "Left-Right"){
			for(int i = 0; i < imageCheck.length; i++){//height, rows
				for(int j = 0; j < imageCheck[0].length; j++){//width, columns
					scanImage(imageCheck, pixelColor, j, i, traversalDirection);
				}	
			}
		}
		if(traversalDirection == "Right-Left"){
			for(int i = 0; i < imageCheck.length; i++){//height, rows
				for(int j = imageCheck[0].length-1; j > 0; j--){//width, columns
					scanImage(imageCheck, pixelColor, j, i, traversalDirection);
				}	
			}
		}
		if(traversalDirection == "Top-Bottom"){
			for(int j = 0; j < imageCheck[0].length; j++){//width, columns
				for(int i = 0; i < imageCheck.length; i++){//height, rows
					scanImage(imageCheck, pixelColor, j, i, traversalDirection);
				}	
			}
		}
		if(traversalDirection == "Bottom-Top"){
			for(int j = 0; j < imageCheck[0].length; j++){//width, columns
				for(int i = imageCheck.length-1; i > 0; i--){//height, rows
					scanImage(imageCheck, pixelColor, j, i, traversalDirection);
				}	
			}
		}
		Histogram hist = createHistogram(histogramValues);
		return imageShapes;
	}
	/*
	 * initializes array used to verify each pixel gets checked
	 */
	static void initializeImageArray(int[][] array){
		for(int i = 0; i < array.length; i++){//y, height, rows
			for(int j = 0; j < array[0].length; j++){//x, width, columns
				array[i][j] = 0;
			}
		//System.out.println(Arrays.toString(array[i]));	
		}
	}
	/*
	 * loads array used to store String value of the color at each pixel
	 */
	static void initializeColorArray(String[][] color, int[][] histogram, BufferedImage image){
		for(int i = 0; i < color.length; i++){//y, height, rows
			for(int j = 0; j < color[0].length; j++){//x, width, columns
				  int pixel = image.getRGB(j, i);	
				  int red = (pixel >> 16) & 0xff;
				  int green = (pixel >> 8) & 0xff;
				  int blue = (pixel) & 0xff;
				  int colorTotal = (red + green + blue)/3;
				  ColorList pixelColor = new ColorList();
				  histogram[i][j] = colorTotal;
				  color[i][j] = pixelColor.getColorNameFromRgb(red, green, blue);
			}
	//	System.out.println(Arrays.toString(color[i]));	
		}
	}
	/*
	 * goes through pixel by pixel and groups together neighboring pixels that share the same color.
	 * initial pixel value, color, size of created shape, and the kind of shape that is formed are saved
	 */
	static void scanImage(int[][] array, String[][] color, int x, int y, String directionString){
		if(array[x][y] != 0) {return;}
		if(shapeSize == 0) {startingPixel.setX(x); startingPixel.setY(y);}
		else if(color[x][y] != color[startingPixel.getX()][startingPixel.getY()]) {return;}
		shapeSize++;
		array[x][y] = 1;
		
		if(directionString == "Left-Right"){
			if(x == array.length-1){return;}
			else{scanImage(array, color, x+1,y, directionString);}//search from left to right	
			if(y == array[x].length-1){return;}
			else{scanImage(array, color, x,y+1, directionString);} 	
		}
		if(directionString == "Right-Left"){
			if(x == 0){return;}
			else{scanImage(array, color, x-1,y, directionString);}//search from right to left	
			if(y == array[x].length-1){return;}
			else{scanImage(array, color, x,y+1, directionString);} 	
		}
		if(directionString == "Top-Bottom"){
			if(y == array[x].length-1){return;}
			else{scanImage(array, color, x,y+1, directionString);}//search from top to bottom
			if(x == array.length-1){return;}
			else{scanImage(array, color, x+1,y, directionString);}		
		}
		if(directionString == "Bottom-Top"){
			if(y == 0){return;}
			else{scanImage(array, color, x,y-1, directionString);}//search from bottom to top 
			if(x == array.length-1){return;}
			else{scanImage(array, color, x+1,y, directionString);}
		}
		
		if(startingPixel.getX() == x && startingPixel.getY() == y /*&& shapeSize > 2*/){
		Shape s = new Shape(shapeSize, color[x][y], "Square", startingPixel);//after all 1s are found create Shape, define color, size, starting pixel, shape
		imageShapes.add(s);
		convertOneToTwo(array, x, y);
		shapeSize = 0;
		}
	}
	
	public Histogram createHistogram(int[][] histogram){
		Histogram hist = new Histogram();
		int[] histValues = new int[256];
		for(int i = 0; i < histogram.length; i++){
			for(int j = 0; j < histogram[0].length; j++){
				histValues[histogram[i][j]]++;
			}
		}
		hist.setHistogram(histValues);
		return hist;
	}
	public static void convertOneToTwo(int[][] array, int x, int y){
		for(int i = x; i < array.length; i++){
			for(int j = y; j < array[0].length; j++){
				if(array[i][j] == 1){
					array[i][j] = 2;
				}
			}
		}
	}
}