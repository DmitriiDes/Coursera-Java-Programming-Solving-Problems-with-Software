
/**
 * Write a description of ImageConvertor here.
 * 
 * @author Dmitrii Desiatkov 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class ImageConvertor {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }
    
    public ImageResource makeInvert(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255-inPixel.getRed());
            pixel.setGreen(255-inPixel.getBlue());
            pixel.setBlue(255-inPixel.getGreen());
        }
        return outImage;
    }
    
    public void saveGray(ImageResource image){
        String newName = "gray-" + image.getFileName();
        image.setFileName(newName);
        image.save();    
    }
    
    public void saveInvert(ImageResource image){
        String newName = "invert-" + image.getFileName();
        image.setFileName(newName);
        image.save();    
    }

    public void selectConvertAndSaveGray () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            saveGray(gray);
        }
    }

    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    
    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();
            String newName = "copy-" + fname;
            image.setFileName(newName);
            image.draw();
            image.save();
        }
    }
}
