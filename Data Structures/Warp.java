import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;


class Warp {

	// reads in an image
	public static BufferedImage readImage(String fname) throws Exception {
		BufferedImage image = ImageIO.read(new File(fname));
		return image;
	}

	// saves file
	public static void saveImage(BufferedImage img, File file) throws IOException {
		ImageWriter writer = null;
		java.util.Iterator iter = ImageIO.getImageWritersByFormatName("jpg");

		if(iter.hasNext()) {
			writer = (ImageWriter)iter.next();
		}

		ImageOutputStream ios = ImageIO.createImageOutputStream(file);
		writer.setOutput(ios);

		ImageWriteParam param = new JPEGImageWriteParam(java.util.Locale.getDefault());
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
		param.setCompressionQuality(0.98f);

		writer.write(null, new IIOImage(img, null, null), param);
	}
	static void command(BufferedImage image, String command) {
		if (command.equals("R")) 
			removeRed(image);
		else if (command.equals("B"))
			removeBlue(image);
		else if (command.equals("G"))
			removeGreen(image);
		else if (command.equals("L"))
			lighten(image);
		else if (command.equals("D"))
			darken(image);
		else if (command.equals("H"))
			flipH(image);
	}
	
	static void removeBlue(BufferedImage image) {
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				Color col = new Color(image.getRGB(w, h));
				int r = col.getRed();
				int g = col.getGreen();
				int b = col.getBlue();
				b = 0;
				Color newcol = new Color(r, g, b);
				image.setRGB(w, h, newcol.getRGB());
			}
		}
	}
	
	static void removeRed(BufferedImage image) {
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				Color col = new Color(image.getRGB(w, h));
				int r = col.getRed();
				int g = col.getGreen();
				int b = col.getBlue();
				r = 0;
				Color newcol = new Color(r, g, b);
				image.setRGB(w, h, newcol.getRGB());
			}
		}
	}
	
	static void removeGreen(BufferedImage image) {
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				Color col = new Color(image.getRGB(w, h));
				int r = col.getRed();
				int g = col.getGreen();
				int b = col.getBlue();
				g = 0;
				Color newcol = new Color(r, g, b);
				image.setRGB(w, h, newcol.getRGB());
			}
		}
	}
	
	static void darken(BufferedImage image) {
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				Color col = new Color(image.getRGB(w, h));
				int r = col.getRed();
				int g = col.getGreen();
				int b = col.getBlue();
			
				if (r < 16) {
					r=0;
				}
				else 
					r = r - 16;
				if (b < 16) {
					b=0;
				}
				else 
					b = b - 16;
				if (g < 16) {
					g=0;
				}
				else 
					g = g - 16;
				
				Color newcol = new Color(r, g, b);
				image.setRGB(w, h, newcol.getRGB());
			}
		}
		}
	static void lighten(BufferedImage image) {
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				Color col = new Color(image.getRGB(w, h));
				int r = col.getRed();
				int g = col.getGreen();
				int b = col.getBlue();
			
				if (r < 16) {
					r=0;
				}
				else 
					r = r + 16;
				if (b < 16) {
					b=0;
				}
				else 
					b = b + 16;
				if (g < 16) {
					g=0;
				}
				else 
					g = g + 16;
				
				Color newcol = new Color(r, g, b);
				image.setRGB(w, h, newcol.getRGB());
			}
		}
		}
	static void flipH(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		Color[][] orig = new Color[width][height];
		Color[][] hflip = new Color[width][height];
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				Color col = new Color(image.getRGB(w, h));
				orig[w][h] = col;
			}
		}
			for (int x = 1; x < width/2; x++) {
				for (int y = 1; y < height; y++) {
				hflip[x][y] = orig[width-x][y];
				hflip[width-x][y] = orig[x][y];
			}
			}
			for (int i = 1; i < height/2; i++){
				for (int j = 1; j < width/; j++) {
					Color col = hflip[j][i];
					int r = col.getRed();
					int g = col.getGreen();
					int b = col.getBlue();
					image.setRGB(j, i, col.getRGB());
					
				}
			}
		
	
			}
	
	static void flipV(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		Color[][] orig = new Color[width][height];
		Color[][] vflip = new Color[width][height];
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				Color col = new Color(image.getRGB(w, h));
				orig[w][h] = col;
			}
		}
			for (int x = 1; x < width/2; x++) {
				for (int y = 1; y < height; y++) {
				vflip[x][y] = orig[x][height-y];
				vflip[x][height-y] = orig[x][y];
			}
			}
			for (int i = 1; i < height/2; i++){
				for (int j = 1; j < width/2; j++) {
					Color col = vflip[j][i];
					int r = col.getRed();
					int g = col.getGreen();
					int b = col.getBlue();
					image.setRGB(j, i, col.getRGB());
					
					
				}
			}
		
	
			}
	public static void main(String[] args) throws Exception {
		System.out.println("There were " + args.length + " arguments.");
        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        
		System.out.println("Loading");
		BufferedImage img = readImage(args[0]);
		char[] comm = args[2].toCharArray();
		for (int i = 0; i < 3; i++) {
			System.out.println(comm[i]);
		}
		for (int i = 0; i < 3; i++) {
			command(img, String.valueOf(comm[i]));
		}
		saveImage(img, new File(args[1]));
	}

}
