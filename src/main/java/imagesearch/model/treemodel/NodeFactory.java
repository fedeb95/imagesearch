package imagesearch.model.treemodel;

import imagesearch.image.Image;

public interface NodeFactory {

	ImageNode create(Image img);

}
