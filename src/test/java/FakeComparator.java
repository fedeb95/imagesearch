import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.image.Image;
import imagesearch.image.factory.ImageFactory;

public class FakeComparator implements Comparator {

	@Override
	public double compare(Image img1, Image img2) throws NotCompatibleException {
		FakeImage image1 = (FakeImage)img1;
		FakeImage image2 = (FakeImage)img2;
		return (int)image1.getImg() - (int)image2.getImg();
	}

	@Override
	public ImageFactory getImageFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
