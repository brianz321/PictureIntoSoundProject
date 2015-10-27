package scanImage;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class ScanImage{
	static ArrayList<Shape> imageShapes = new ArrayList<Shape>();
	public static void main (String args[]) throws IOException
	{
		int width = 9;
		int height = 9;
		BufferedImage image = null;
		File f = null;
		
		//read image file
		try{
			f = new File("C:\\Users\\Brian\\Documents\\School\\EE 371R\\Brian\\PictureIntoSound\\src\\testPic.jpg");
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			image = ImageIO.read(f);
			//f = new File("C:\\Users\\Brian\\Documents\\School\\EE 371R\\Brian\\PictureIntoSound\\src\\testPic.jpg");
			
			System.out.println("Reading complete.");
		}catch(IOException e){
			System.out.println("Error: "+e);
		}
		
		//write image
		try{
			f = new File("C:\\Users\\Brian\\Documents\\School\\EE 371R\\Brian\\PictureIntoSound\\src\\output.jpg");
			ImageIO.write(image, "jpg", f);
			System.out.println("Writing complete.");
		}catch(IOException e){
			System.out.println("Error "+e);
		}
		
		//Shape s = new Shape(0,"","");
		//imageShapes.add(s);
	}
}