import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.HistogramComparator;
import imagesearch.image.OpenimajImage;

public abstract class AbstractTestWithImages {
	public static final String PATH = "/home/federico/imagedb/ImageDB/src/test/images/";

	protected double compare(String imgName1, String imgName2) throws IOException, NotCompatibleException {
		MBFImage img1 = ImageUtilities.createMBFImage(ImageIO.read(new File(imgName1)),false);
    	MBFImage img2 = ImageUtilities.createMBFImage(ImageIO.read(new File(imgName2)),false);
    	OpenimajImage image1 = new OpenimajImage();
    	OpenimajImage image2 = new OpenimajImage();
    	image1.setImg(img1);
    	image2.setImg(img2);
    	HistogramComparator comparator = new HistogramComparator();
    	return comparator.compare(image1, image2);	
	}
}
