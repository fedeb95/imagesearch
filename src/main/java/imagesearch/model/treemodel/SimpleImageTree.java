package imagesearch.model.treemodel;

import java.util.ArrayList;
import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.image.Image;

public class SimpleImageTree implements ImageTree{
	
	private SimpleImageNode root;
	private Comparator comparator;
	private double threshold;

	public SimpleImageTree(Comparator comparator, double threshold) {
		this.root = null;
		this.comparator = comparator;
		this.threshold = threshold;
	}

	@Override
	public void insert(ImageNode n) throws NotCompatibleException {
		SimpleImageNode node = null;
		if (!(n instanceof SimpleImageNode))
			throw new NotCompatibleException(n.getClass().getSimpleName() + 
					" not compatible with SimpleImageNode");
		else
			node = (SimpleImageNode) n;
		if (this.root == null) 
			this.root = node;
		else {
			if(this.root.getLeft() == null)
				this.root.setLeft(node);
			else if (this.root.getRight() == null)
				this.root.setRight(node);
			else {
				compareInsert(node, root);
			}
		}
	}

	private void compareInsert(SimpleImageNode toInsert, SimpleImageNode node) throws NotCompatibleException {
		if(node.getLeft() == null)
			node.setLeft(node);
		else if (node.getRight() == null)
			node.setRight(node);
		else {
			//double resRoot = this.comparator.compare(toInsert.getImage(),node.getImage());
			double resLeft = this.comparator.compare(toInsert.getImage(), node.getLeft().getImage());
			double resRight = this.comparator.compare(toInsert.getImage(), node.getRight().getImage());
//			if (resRoot > resLeft && resRoot > resRight) {
//				SimpleImageNode tmp = node;
//				node = toInsert;
//				toInsert.setLeft(tmp.getLeft());
//				toInsert.setRight(tmp.getRight());
//				tmp.setLeft(null);
//				tmp.setRight(null);
//				compareInsert(tmp, node);
			if(resLeft > resRight)
				compareInsert(toInsert,node.getLeft());
			else
				compareInsert(toInsert,node.getRight());
		}
	}

	@Override
	public List<Image> pathToLeaf(Image img) throws NotCompatibleException {
		List<Image> toReturn;
		if (this.root == null)
			return null;
		else {
			toReturn = new ArrayList<Image>();
			searchAccumulate(img,this.root,toReturn);
		}
		return toReturn;
	}
	
	private void searchAccumulate(Image img,SimpleImageNode node, List<Image> list) throws NotCompatibleException{
		if(node != null) {
			if (node.getLeft() == null && node.getRight() != null) {
				double res = this.comparator.compare(img, node.getRight().getImage());
				if(res < this.threshold)
					list.add(node.getRight().getImage());
				searchAccumulate(img, node.getRight(), list);
			}else if (node.getLeft() != null && node.getRight() == null) {				
				double res = this.comparator.compare(img, node.getLeft().getImage());
				if(res < this.threshold)
					list.add(node.getLeft().getImage());
				searchAccumulate(img, node.getLeft(), list);
			}else {
				double resLeft = this.comparator.compare(img, node.getLeft().getImage());
				double resRight = this.comparator.compare(img, node.getRight().getImage());
				if (resLeft < this.threshold) {
					list.add(node.getLeft().getImage());
					searchAccumulate(img, node.getLeft(), list);
				}else {
					searchCloser(img,resLeft,resRight,node,list);
				}
				if (resRight < this.threshold) {
					searchAccumulate(img, node.getRight(), list);
					list.add(node.getRight().getImage());
				}
			}
		}
	}

	private void searchCloser(Image img, double resLeft, double resRight, SimpleImageNode node, List<Image> list) throws NotCompatibleException {
		if(resRight < resLeft)
			searchAccumulate(img, node.getRight(), list);
		else
			searchAccumulate(img, node.getLeft(), list);
		
	}
}
