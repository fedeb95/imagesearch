package imagesearch.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.image.Image;
import imagesearch.image.ImageProxy;

public class BruteModel implements Model {

	private List<Image> images;
	private Comparator comparator;
	private double threshold;
	
	public BruteModel(Comparator comp,double thresh) {
		this.images = new ArrayList<Image>();
		this.comparator = comp;
		this.threshold = thresh;
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
				addImage(img);
			}
		}
	}

	public Iterator<Image> iterator() {
		return this.images.iterator();
	}
	
	@Override
	public List<Image> search(Image img) throws NotCompatibleException{
		List<Image> toReturn = new ArrayList<Image>();
		Iterator<Image> iter = this.iterator();
		while(iter.hasNext()) {
			Image imgToCompare = iter.next();
			double result = this.comparator.compare(img, imgToCompare);
			if (result < this.threshold) {
				toReturn.add(imgToCompare);
			}
		}
		return toReturn;

	}
}
