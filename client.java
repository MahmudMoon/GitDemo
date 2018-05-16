import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class client{
    
	
	public static void main(String[] args) {
		
		client sum  = new client();
		sum.Spiting_image();
		int port;
		String host;
		
		try{
		
		port = 19867;
		host = "rmi://localhost:"+Integer.toString(port)+"/addition";
		
		infOne id1 = (infOne)Naming.lookup(host);
		
		 
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
	     BufferedImage b = ImageIO.read(new File("Demo1.png"));
		 ImageIO.write(b, "png", out);
		 byte[] byte_Ary = out.toByteArray();
		 byte[] imageOne  = id1.method_1(byte_Ary);
	      
		
		port = 19868;
		host = "rmi://localhost:"+Integer.toString(port)+"/addition";
		
		infTwo id2 = (infTwo)Naming.lookup(host);
		  
		 ByteArrayOutputStream out1 = new ByteArrayOutputStream();
	     BufferedImage b1 = ImageIO.read(new File("Demo2.png"));
		 ImageIO.write(b1, "png", out1);
		 byte[] byte_Ary1 = out1.toByteArray();
		  byte[] imagetwo = id2.method_2(byte_Ary1);
		 System.out.println("Size of 1 is " + imagetwo.length);
		  
			 port = 19869;
		     host = "rmi://localhost:"+Integer.toString(port)+"/addition";
		
		infThree id3 = (infThree)Naming.lookup(host);
		 ByteArrayOutputStream out2 = new ByteArrayOutputStream();
	     BufferedImage b2 = ImageIO.read(new File("Demo3.png"));
		 ImageIO.write(b2, "png", out2);
		 byte[] byte_Ary2 = out2.toByteArray();
		 byte[] imagethree =  id3.method_3(byte_Ary2);
		 System.out.println("Size of 1 is " + imagethree.length);
		 
			 
			 port = 19870;
		host = "rmi://localhost:"+Integer.toString(port)+"/addition";
		
		infFour id4 = (infFour)Naming.lookup(host);
		
		  ByteArrayOutputStream out3 = new ByteArrayOutputStream();
	     BufferedImage b3 = ImageIO.read(new File("Demo4.png"));
		 ImageIO.write(b3, "png", out3);
		byte[] byte_Ary3 = out3.toByteArray();
		byte[] imagefour = id4.method_4(byte_Ary3);
		System.out.println("Size of 1 is " + imagefour.length);
		sum.combiningImages(imageOne,imagetwo,imagethree,imagefour);
		System.out.println("All the methods have been called !!!");
		
		 }catch(Exception e){
			System.out.println(" OCCURED" + e);
		}
		
		
		
		
		
		
		
	}
	
	
	public void combiningImages(byte[] imageOne,byte[] imagetwo,byte[] imagethree, byte[] imagefour)throws IOException{
		BufferedImage im1 = javax.imageio.ImageIO.read(new ByteArrayInputStream(imageOne));
		BufferedImage im2 = javax.imageio.ImageIO.read(new ByteArrayInputStream(imagetwo));
		BufferedImage im3 = javax.imageio.ImageIO.read(new ByteArrayInputStream(imagethree));
		BufferedImage im4 = javax.imageio.ImageIO.read(new ByteArrayInputStream(imagefour));
		
		
		BufferedImage newBufferedImage = new BufferedImage(im1.getWidth()*2-2 , im1.getHeight()*2-2,BufferedImage.TYPE_INT_ARGB);
		int height = im1.getHeight()*2 - 2;
		int width = im1.getWidth()*2 - 2;
		
		int p = 0;
		
		
		for(int i = 0;i<im1.getHeight()-1;i++)
		{
			for(int j =0;j<im1.getWidth()-1;j++)
			{
				
					 p = im1.getRGB(j, i);
					 
			
				
				 newBufferedImage.setRGB(j, i, p);
			}
		}
		
		int h = im1.getHeight()-1;
		int w = im1.getWidth()-1;
		
		for(int i = 0;i<im2.getHeight()-1;i++)
		{
			for(int j =0;j<im2.getWidth()-1;j++)
			{
				
					 p = im2.getRGB(j, i);
					 
				
				 newBufferedImage.setRGB(w+j, i, p);
			}
		}
		
		for(int i = 0;i<im3.getHeight()-1;i++)
		{
			for(int j =0;j<im3.getWidth()-1;j++)
			{
				
					 p = im3.getRGB(j, i);
					 
				
				
				 newBufferedImage.setRGB(j,h+i, p);
			}
		}
		
		
		for(int i = 0;i<im4.getHeight()-1;i++)
		{
			for(int j =0;j<im4.getWidth()-1;j++)
			{
				
					 p = im4.getRGB(j, i);
					 
				
				
				 newBufferedImage.setRGB(w+j, h+i, p);
			}
		}
		
		     JLabel finalImageToShow = new JLabel(new ImageIcon(newBufferedImage));

            JFrame frame = new JFrame();
            frame.setSize(newBufferedImage.getWidth(),newBufferedImage.getHeight());

            frame.setLayout(new FlowLayout());

            frame.add(finalImageToShow);

            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		ImageIO.write(newBufferedImage,"png",new File("finalImage.png"));
		System.out.println("Image has been created");
	}
	
	public void Spiting_image(){
		
		try{
		BufferedImage originalImage = null;
		BufferedImage[] all_images = new BufferedImage[4];
		originalImage = ImageIO.read(new File("image.jpg"));
		
		BufferedImage subImageA = originalImage.getSubimage(0,0,(originalImage.getWidth()/2),(originalImage.getHeight()/2));
		BufferedImage subImageB = originalImage.getSubimage((originalImage.getWidth()/2),0,(originalImage.getWidth()/2),(originalImage.getHeight()/2));
		
		BufferedImage subImageC = originalImage.getSubimage(0,(originalImage.getHeight()/2),(originalImage.getWidth()/2),(originalImage.getHeight()/2));
	    BufferedImage subImageD = originalImage.getSubimage((originalImage.getWidth()/2),(originalImage.getHeight()/2),(originalImage.getWidth()/2),(originalImage.getHeight()/2));
		
		ImageIO.write(subImageA, "png", new File("Demo1.png"));
		ImageIO.write(subImageB, "png", new File("Demo2.png"));
		ImageIO.write(subImageC, "png", new File("Demo3.png"));
		ImageIO.write(subImageD, "png", new File("Demo4.png"));
		
		 JLabel givenImage = new JLabel(new ImageIcon(originalImage));

            JFrame frame = new JFrame();
            frame.setSize(originalImage.getWidth(),originalImage.getHeight());

            frame.setLayout(new FlowLayout());

            frame.add(givenImage);

            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		
		}catch(IOException e){
			System.out.println("Error in loading image");
		
		}
	}
}