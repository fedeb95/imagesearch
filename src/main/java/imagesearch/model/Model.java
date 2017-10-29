package imagesearch.model;

import java.util.Iterator;

import imagesearch.NotCompatibleException;
import imagesearch.image.Image;

public interface Model {
	void addImage(Image img) throws NotCompatibleException;
	void addDirectory(String path) throws NotCompatibleException;
	Iterator<Image> iterator();
}
