package imagesearch.model.treemodel;

import java.io.File;
import java.util.Iterator;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.image.Image;
import imagesearch.image.ImageProxy;
import imagesearch.model.Model;

public class TreeModel implements Model{
	
	private NodeFactory factory;
	private SimpleImageTree tree;

	public TreeModel(Comparator comparator) {
		this.factory = new SimpleNodeFactory();
		this.tree = new SimpleImageTree(comparator);
	}

	@Override
	public void addImage(Image img) throws NotCompatibleException {
		ImageNode n = this.factory.create(img);
		this.tree.insert(n);
	}

	@Override
	public void addDirectory(String path) throws NotCompatibleException {
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				Image img = new ImageProxy(files[i].getAbsolutePath());
				addImage(img);
			}
		}
		
	}

	@Override
	public Iterator<Image> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
