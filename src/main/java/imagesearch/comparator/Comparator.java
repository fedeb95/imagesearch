package imagesearch.comparator;

import imagesearch.NotCompatibleException;
import imagesearch.image.Image;

public interface Comparator {
	double compare(Image img1, Image img2) throws NotCompatibleException;
}
