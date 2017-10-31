package imagesearch.image;

import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.resize.BilinearInterpolation;

import imagesearch.image.factory.ImageFactory;

public class ImageProxyResized extends ImageProxy {

	private float scale;

	public ImageProxyResized(String path,ImageFactory fact, float scale) {
		super(path, fact);
		this.scale = scale;
	}
	
	@Override
	public Object getImg() {
		MBFImage img = (MBFImage) super.getImg();
		BilinearInterpolation b = new BilinearInterpolation(img.getWidth(), img.getHeight(), this.scale);
		img.bands.forEach((band) -> b.processImage(band));
		return img;
	}

}
