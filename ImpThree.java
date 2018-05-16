import java.rmi.*;
import java.rmi.server.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


class ImplThree extends UnicastRemoteObject implements infThree{
	ImplThree() throws RemoteException{
		super();
	}
	
	public byte[] method_3(byte[] b)throws java.rmi.RemoteException{
		
		try{
		BufferedImage partOne = javax.imageio.ImageIO.read(new ByteArrayInputStream(b));
		System.out.println(partOne.getWidth() + " - " + partOne.getHeight());
		
		int width = partOne.getWidth();
        int height = partOne.getHeight();
        BufferedImage mimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    //create mirror image pixel by pixel
        for(int y = 0; y < height; y++){
          for(int x = 0; x < width; x++){
       
        int p = partOne.getRGB(x, y);
        //set mirror image pixel value - both left and right
        mimg.setRGB(width-1-x, y, p);
       
      }
    }
	        
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(mimg, "png", baos);
			byte[] rawImage2 = baos.toByteArray();

			System.out.println(mimg.getWidth() + " - " + mimg.getHeight());
			return rawImage2;

		
		
		}catch(IOException exception){
			System.out.println("Problem occured in partOne");
			return null;
		}
	} 
	
	
}