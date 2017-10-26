package imagesearch.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.comparator.HistogramComparator;
import imagesearch.image.Image;
import imagesearch.image.ImageProxy;
import imagesearch.model.BruteModel;
import imagesearch.model.BruteSearcher;
import imagesearch.model.Model;
import imagesearch.searcher.Searcher;

public class Controller {
	public List<Image> searchInDirectory(File image, File dir) throws NotCompatibleException{
		if (image.isFile()) { 
			Model model = new BruteModel();
			model.addDirectory(dir.getAbsolutePath());
			Searcher searcher = new BruteSearcher();
			Comparator comp = new HistogramComparator();
			Image img = new ImageProxy(image.getAbsolutePath());
			return searcher.search(img, comp, model);
		}
		return null;
	}

}
