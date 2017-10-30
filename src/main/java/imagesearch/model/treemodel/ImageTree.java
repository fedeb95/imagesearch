package imagesearch.model.treemodel;

import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.image.Image;

public interface ImageTree {

	void insert(ImageNode n) throws NotCompatibleException;

	List<Image> pathToLeaf(Image img) throws NotCompatibleException;

}
