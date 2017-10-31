package imagesearch.image.factory;

import java.io.File;

import imagesearch.image.Image;
import imagesearch.image.ImageProxy;

public class ImageProxyFactory implements ImageFactory{
	
	private ImageFactory factory;

	public ImageProxyFactory(ImageFactory toBeProxed) {
		this.factory = toBeProxed;
	}

	@Override
	public Image create(File file) {
		return new ImageProxy(file.getAbsolutePath(),this.factory);
	}

}
