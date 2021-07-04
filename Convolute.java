package uk.ac.soton.ecs.sg4n20.hybridimages;
import org.openimaj.image.FImage;
import org.openimaj.image.processor.SinglebandImageProcessor;
public class MyConvolution implements SinglebandImageProcessor<Float, FImage> {
	private float[][] kernel; 
	public MyConvolution(float[][] kernel) {
		
		this.kernel = kernel;
		}
	@Override 
	public void processImage(FImage image) {
		FImage imgCl= image.clone();         //Clonning Image
		int tx= (kernel.length-1)/2;
		int ty = (kernel[0].length-1)/2;
		//for convolving and storing the result back in the image 
		
		for(int i= 0; i < image.getHeight(); i++) {
			for(int j = 0; j <image.getWidth(); j++) {
				if (i < tx || i >= image.height - tx || j < ty || j >=image.width - ty) {
					
					
				}
				
				else {
					float sum = 0f;
					
					for(int k= 0; k <kernel.length; k++) {
						for(int m = 0; m <kernel.length; m++) {
							//finding coordinates of each image pixel
							int li = i - tx + k;  
							int lj = j - ty + m;
							
							sum = sum + kernel[k][m] * image.pixels[li][lj]; //kernel weight corresponding to pixel val
						}
					}
					
					imgCl.pixels[i][j] = sum;
				}
			}
		}
		
		imgCl = imgCl.normalise(); //normalizing the image
		image.internalAssign(imgCl);
		

	}
}
