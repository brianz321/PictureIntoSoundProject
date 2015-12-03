package scanImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ScanImage{
	static ArrayList<Shape> imageShapes = new ArrayList<Shape>();
	public static void main (String args[]) throws IOException
	{
		int width;
		int height;
		BufferedImage image = null;//original image
		File f = null;
		
		//read image file	
		ImageIcon icon = new ImageIcon("C:\\Users\\Brian\\Documents\\School\\EE 371R\\PictureIntoSoundProject\\testPic.jpg");
		width = icon.getIconWidth();
		height = icon.getIconHeight();
		f = new File("C:\\Users\\Brian\\Documents\\School\\EE 371R\\PictureIntoSoundProject\\testPic.jpg");
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		try{	
			image = ImageIO.read(f);
			System.out.println("Reading complete.");	
		}catch(IOException e){
			System.out.println("Error: "+e);
		}
		
		//write image
		try{
			f = new File("C:\\Users\\Brian\\Documents\\School\\EE 371R\\PictureIntoSoundProject\\output.jpg");
			ImageIO.write(image, "jpg", f);
			System.out.println("Writing complete.");
		}catch(IOException e){
			System.out.println("Error "+e);
		}
		
		//display image
/*		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon("C:\\Users\\Brian\\Documents\\School\\EE 371R\\PictureIntoSoundProject\\output.jpg");
		JLabel label  = new JLabel(icon);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
*/		
		//begin scanning image
		int[][] imageCheck = new int[height][width];//array for checked pixels
		int[][] pixelColor = new int[height][width];//array for pixels color value
		initializeImageArray(imageCheck);
		initializeColorArray(pixelColor, image);//get colors of each pixel, combine them together
		//search from left to right and top to bottom, mark pixel with 1. search neighbors and mark same colored pixels with 1 if not already 2 or 1 (recursion?).
		//after all 1s are found create Shape, define color, size, starting pixel, shape
		//start search again with next pixel marked 0, *make sure to apply color and size thresholds* 
		
		//Shape s = new Shape(0,"","");
		//imageShapes.add(s);
	}
	
	static void initializeImageArray(int[][] array){
		for(int i = 0; i < array.length; i++){//height, rows
			for(int j = 0; j < array[0].length; j++){//width, columns
				array[i][j] = 0;
			}
		System.out.println(Arrays.toString(array[i]));	
		}
	}
	static void initializeColorArray(int[][] array, BufferedImage image){
		for(int i = 0; i < array.length; i++){//height, rows
			for(int j = 0; j < array[0].length; j++){//width, columns
				  int pixel = image.getRGB(j, i);	
				  int red = (pixel >> 16) & 0xff;
				  int green = (pixel >> 8) & 0xff;
				  int blue = (pixel) & 0xff;
				  int color = red + green + blue;
				  array[i][j] = color;
			}
		System.out.println(Arrays.toString(array[i]));	
		}
	}
}