package scanImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScanImage{
	static ArrayList<Shape> imageShapes = new ArrayList<Shape>();
	static int shapeSize = 0;
	static Pixel startingPixel = new Pixel(0,0);
	
	public static void main (String args[]) throws IOException
	{
		int width;
		int height;
		BufferedImage image = null;//original image
		File f = null;
		
		//read image file	
		ImageIcon icon = new ImageIcon("C:\\Users\\Brian\\Documents\\School\\EE 371R\\PictureIntoSoundProject\\images\\testPic2.jpg");
		width = icon.getIconWidth();
		height = icon.getIconHeight();
		f = new File("C:\\Users\\Brian\\Documents\\School\\EE 371R\\PictureIntoSoundProject\\images\\testPic2.jpg");
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		try{	
			image = ImageIO.read(f);
			System.out.println("Reading complete.");	
		}catch(IOException e){
			System.out.println("Error: "+e);
		}
		
		//write image
		try{
			f = new File("C:\\Users\\Brian\\Documents\\School\\EE 371R\\PictureIntoSoundProject\\output2.jpg");
			ImageIO.write(image, "jpg", f);
			System.out.println("Writing complete.");
		}catch(IOException e){
			System.out.println("Error "+e);
		}
		
		//display image
		JFrame frame = new JFrame();
		ImageIcon icon2 = new ImageIcon("C:\\Users\\Brian\\Documents\\School\\EE 371R\\PictureIntoSoundProject\\output2.jpg");
		JLabel label  = new JLabel(icon2);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	
		//begin scanning image
		int[][] imageCheck = new int[height][width];//array for checked pixels
		String[][] pixelColor = new String[height][width];//array for pixels color value
		initializeImageArray(imageCheck);
		initializeColorArray(pixelColor, image);
		//user input to determine where search starts go here, startX and startY and looping depends on input
		for(int i = 0; i < imageCheck.length; i++){//height, rows
			for(int j = 0; j < imageCheck[0].length; j++){//width, columns
				scanImage(imageCheck, pixelColor, j, i/*, 5th parameter for user search traversal*/);//start search again with next pixel marked 0, *make sure to apply color and size thresholds*
			}	
		}
	}
	
	static void initializeImageArray(int[][] array){
		for(int i = 0; i < array.length; i++){//y, height, rows
			for(int j = 0; j < array[0].length; j++){//x, width, columns
				array[i][j] = 0;
			}
		//System.out.println(Arrays.toString(array[i]));	
		}
	}
	static void initializeColorArray(String[][] color, BufferedImage image){
		for(int i = 0; i < color.length; i++){//y, height, rows
			for(int j = 0; j < color[0].length; j++){//x, width, columns
				  int pixel = image.getRGB(j, i);	
				  int red = (pixel >> 16) & 0xff;
				  int green = (pixel >> 8) & 0xff;
				  int blue = (pixel) & 0xff;
				  ColorList pixelColor = new ColorList();
				  color[i][j] = pixelColor.getColorNameFromRgb(red, green, blue);
			}
		System.out.println(Arrays.toString(color[i]));	
		}
	}
	static void scanImage(int[][] array, String[][] color, int x, int y){
		if(array[x][y] != 0) {return;}
		if(shapeSize == 0) {startingPixel.setX(x); startingPixel.setY(y);}
		else if(color[x][y] != color[startingPixel.getX()][startingPixel.getY()]) {return;}
		shapeSize++;
		array[x][y] = 1;
		if(x == array.length-1){return;}
		else{scanImage(array, color, x+1,y);}//search from left to right and top to bottom, mark pixel with 1.	
		if(y == array[x].length-1){return;}
		else{scanImage(array, color, x,y+1);}//search neighbors and mark same colored pixels with 1 if not already 2 or 1 (recursion?). 	
		
		if(startingPixel.getX() == x && startingPixel.getY() == y /*&& shapeSize > 2*/){
		Shape s = new Shape(shapeSize, color[x][y], "Square", startingPixel);//after all 1s are found create Shape, define color, size, starting pixel, shape
		imageShapes.add(s);
		convertOneToTwo(array, x, y);
		shapeSize = 0;
		}
	}
	
	static void convertOneToTwo(int[][] array, int x, int y){
		for(int i = x; i < array.length; i++){
			for(int j = y; j < array[0].length; j++){
				if(array[i][j] == 1){
					array[i][j] = 2;
				}
			}
		}
	}
}