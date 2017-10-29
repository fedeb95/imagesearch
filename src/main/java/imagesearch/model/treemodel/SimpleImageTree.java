package imagesearch.model.treemodel;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;

public class SimpleImageTree implements ImageTree{
	
	private SimpleImageNode root;
	private Comparator comparator;

	public SimpleImageTree(Comparator comparator) {
		this.root = null;
		this.comparator = comparator;
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
			double resRoot = this.comparator.compare(toInsert.getImage(),node.getImage());
			double resLeft = this.comparator.compare(toInsert.getImage(), node.getLeft().getImage());
			double resRight = this.comparator.compare(toInsert.getImage(), node.getRight().getImage());
			if (resRoot > resLeft && resRoot > resRight) {
				SimpleImageNode tmp = node;
				node = toInsert;
				toInsert.setLeft(tmp.getLeft());
				toInsert.setRight(tmp.getRight());
				tmp.setLeft(null);
				tmp.setRight(null);
				compareInsert(tmp, node);
			}else if(resLeft > resRight)
				compareInsert(toInsert,node.getLeft());
			else
				compareInsert(toInsert,node.getRight());
		}
	}

}
