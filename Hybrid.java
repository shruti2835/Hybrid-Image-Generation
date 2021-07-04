package uk.ac.soton.ecs.sg4n20.hybridimages;
import java.io.File;
//import java.io.IOException;
import static java.lang.Math.exp;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

public class MyHybridImages { 
	/*public static void main(String [] args) throws IOException {
		MBFImage lowImg =ImageUtilities.readMBF(new File("C:\\Users\\SHRUTI\\Downloads\\messi.bmp"));
		
		float lowSigma = 5;	
		MBFImage highImg = ImageUtilities.readMBF(new File("C:\\Users\\SHRUTI\\Downloads\\ronaldo.bmp"));
		
		float highSigma = 8;
		*/
		
	
	
	public static MBFImage makeHybrid(MBFImage lowImage, float lowSigma, MBFImage highImage, float highSigma) {
		//process low pass image
		MBFImage image1 = lowImage.process(new MyConvolution(makeGaussianKernel(lowSigma)));
		
		//process high pass image
		MBFImage image2 = highImage.process(new MyConvolution(makeGaussianKernel(highSigma)));
		
		
		//display low pass filtered image
		//DisplayUtilities.display(image1, "Low Pass");
		
		
		image2 =highImage.subtract(image2);
		image2.addInplace(0.5f);
		
		//display high pass filtered image
		//DisplayUtilities.display(image2, "High Pass");
		
		MBFImage img=image1.add(image2);
		img.subtractInplace(0.5f);
		
		//display hyrbid image
		//DisplayUtilities.display(img, "Hybrid Image");
		return img;
	}

	
		
	public static float[][] makeGaussianKernel(float sigma) {

		//Use this function to   create a 2D gaussian kernel with standard deviation sigma.
		//The kernel values should sum to   1.0, and the    size should be   floor(8*sigma+1) or
		//floor(8*sigma+1)+1 (whichever is odd) as per    the    assignment specification.} }
		int size =(int) Math.floor(8.0f* sigma+1.0f);
		//as size should be odd	
		if(size % 2 == 0) {
			size++;
		}
		
	  float[][] imgtmplate = new float[size][size];
	  int height = (size - 1)/2;
	  int width = (size - 1)/2;
	  
	  float sigmaSq = (sigma*sigma);
	  double sum = 0;
	   //for calculating template values using Gaussian kernel formula
	  for(int i=0;i<imgtmplate.length; i++) {
		  for (int j=0; j<imgtmplate[i].length; j++) {
			  imgtmplate[i][j] = (float)exp(-(((i-width) * (i-width)) + ((j-height) * (j-height)))/ (2*sigmaSq));
			  sum+= imgtmplate[i][j];
		  }
	  }
	  //insert values into template matrix
	  for(int i =0 ; i< imgtmplate.length; i++) {
		  for(int j=0; j<imgtmplate[i].length; j++) {
			  imgtmplate[i][j]/=sum;
			  System.out.println(imgtmplate[i][j]+" ");
		  }
	  }
	  
	  return imgtmplate;
	}
		
}
