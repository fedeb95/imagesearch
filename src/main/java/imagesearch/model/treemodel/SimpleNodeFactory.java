package imagesearch.model.treemodel;

import imagesearch.image.Image;

public class SimpleNodeFactory implements NodeFactory{

	@Override
	public ImageNode create(Image img) {
		return new SimpleImageNode(img);
	}

}
