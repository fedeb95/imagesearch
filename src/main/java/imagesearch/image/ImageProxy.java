package imagesearch.image;

import java.io.File;

import imagesearch.image.factory.ImageFactory;

public class ImageProxy implements Image {
	
	private String path;
	private ImageFactory factory;

	public ImageProxy(String path,ImageFactory proxedFact) {
		this.path = path;
		this.factory = proxedFact;
	}

	@Override
	public Object getImg() {
		return this.factory.create(new File(path)).getImg();
	}

	@Override
	public void setImg(Object img) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

}
