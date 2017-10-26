package imagesearch.model;

import java.util.Iterator;

import imagesearch.image.Image;

public interface Model {
	void addImage(Image img);
	void addDirectory(String path);
	Iterator<Image> iterator();
}
