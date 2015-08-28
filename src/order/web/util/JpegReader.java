package order.web.util;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class JpegReader {

	

	    public BufferedImage readImage(File f) throws IOException {
	    	
	        //Find a suitable ImageReader
	        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
	        ImageReader reader = null;
	        while(readers.hasNext()) {
	            reader = (ImageReader)readers.next();
	            if(reader.canReadRaster()) {
	                break;
	            }
	        }

	        //Stream the image file (the original CMYK image)
	        ImageInputStream input =   ImageIO.createImageInputStream(f); 
	        reader.setInput(input); 

	        //Read the image raster
	        Raster raster = reader.readRaster(0, null); 

	        //Create a new RGB image
	        BufferedImage bi = new BufferedImage(raster.getWidth(), raster.getHeight(), 
	        BufferedImage.TYPE_4BYTE_ABGR); 

	        //Fill the new image with the old raster
	        bi.getRaster().setRect(raster);
	        
	        return bi;
	    }
}
