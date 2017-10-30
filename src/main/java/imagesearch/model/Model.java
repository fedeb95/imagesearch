package imagesearch.model;

import java.util.Iterator;
import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.image.Image;

public interface Model {
	void addImage(Image img) throws NotCompatibleException;
	void addDirectory(String path) throws NotCompatibleException;
	List<Image> search(Image img) throws NotCompatibleException;
}
