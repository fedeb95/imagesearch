package imagesearch.image.factory;

import java.io.File;

import imagesearch.image.Image;
import imagesearch.image.ImageProxyResized;

public class ImageProxyResizedFactory implements ImageFactory{
	
	private float scale;
	private ImageFactory factory;

	public ImageProxyResizedFactory(ImageFactory toBeProxed, float scale) {
		this.factory = toBeProxed;
		this.scale = scale;
	}

	@Override
	public Image create(File file) {
		return new ImageProxyResized(file.getAbsolutePath(),this.factory,this.scale);
	}

}
