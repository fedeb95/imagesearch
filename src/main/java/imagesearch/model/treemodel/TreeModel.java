package imagesearch.model.treemodel;

import java.io.File;
import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.image.Image;
import imagesearch.image.ImageProxy;
import imagesearch.image.factory.ImageFactory;
import imagesearch.model.Model;

public class TreeModel implements Model{
	
	private NodeFactory nodeFactory;
	private SimpleImageTree tree;
	private ImageFactory imageFactory;

	public TreeModel(NodeFactory nodeFact,
			Comparator comparator,double threshold) {
		this.nodeFactory = nodeFact;
		this.tree = new SimpleImageTree(comparator,threshold);
		this.imageFactory = comparator.getImageFactory();
	}

	@Override
	public void addImage(Image img) throws NotCompatibleException {
		ImageNode n = this.nodeFactory.create(img);
		this.tree.insert(n);
	}

	@Override
	public void addDirectory(String path) throws NotCompatibleException {
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				Image img = this.imageFactory.create(files[i]);
				addImage(img);
			}
		}
	}
	
	@Override
	public List<Image> search(Image img) throws NotCompatibleException {
		return this.tree.pathToLeaf(img);
	}

}
