package imagesearch.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import imagesearch.image.Image;
import imagesearch.image.ImageProxy;

public class BruteModel implements Model {

	private List<Image> images;
	
	public BruteModel() {
		this.images = new ArrayList<Image>();
	}

	@Override
	public void addImage(Image img) {
		this.images.add(img);
	}

	@Override
	public void addDirectory(String path) {
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				Image img = new ImageProxy(files[i].getAbsolutePath());
				this.images.add(img);
			}
		}
	}
	@Override
	public Iterator<Image> iterator() {
		return this.images.iterator();
	}
}
