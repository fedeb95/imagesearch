package imagesearch.model.treemodel;

import imagesearch.NotCompatibleException;

public interface ImageTree {

	void insert(ImageNode n) throws NotCompatibleException;

}
