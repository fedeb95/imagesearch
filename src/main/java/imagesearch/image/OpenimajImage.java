package imagesearch.image;
import org.openimaj.image.MBFImage;
import org.openimaj.math.statistics.distribution.MultidimensionalHistogram;

public class OpenimajImage implements Image{
	
	private MBFImage img;
	private String path;

	@Override
	public MBFImage getImg() {
		return this.img;
	}

	@Override
	public void setImg(Object img) {
		if (img instanceof MBFImage)
			this.img = (MBFImage)img;
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}
}
