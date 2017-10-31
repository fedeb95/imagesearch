package imagesearch.image.factory;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openimaj.image.ImageUtilities;

import imagesearch.image.Image;
import imagesearch.image.OpenimajImage;

public class OpenimajFactory implements ImageFactory {

	@Override
	public Image create(File file) {
		OpenimajImage im = new OpenimajImage();
		try {
			im.setImg(ImageUtilities.createMBFImage(ImageIO.read(file), false));
			//im.setImg(ImageUtilities.readMBF(file));
		} catch (IOException e) {
			im = null;
		}
		return im;
	}

}
