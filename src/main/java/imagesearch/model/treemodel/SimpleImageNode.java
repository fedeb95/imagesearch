package imagesearch.model.treemodel;

import imagesearch.image.Image;

public class SimpleImageNode implements ImageNode {

	private Image img;
	private SimpleImageNode left;
	private SimpleImageNode right;

	public SimpleImageNode(Image img) {
		this.img = img;
	}

	public void setLeft(SimpleImageNode node) {
		this.left = node;
	}

	public SimpleImageNode getLeft() {
		return left;
	}

	public void setRight(SimpleImageNode node) {
		this.right = node;
	}

	public SimpleImageNode getRight() {
		return this.right;
	}

	@Override
	public Image getImage() {
		return this.img;
	}



}
