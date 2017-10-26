package imagesearch.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.image.Image;
import imagesearch.searcher.Searcher;

public class BruteSearcher implements Searcher {

	private static final double THRESHOLD = 0.1;

	@Override
	public List<Image> search(Image img, Comparator comp, Model model) throws NotCompatibleException {
		List<Image> toReturn = new ArrayList<Image>();
		Iterator<Image> iter = model.iterator();
		while(iter.hasNext()) {
			Image imgToCompare = iter.next();
			double result = comp.compare(img, imgToCompare);
			if (result < THRESHOLD) {
				toReturn.add(imgToCompare);
			}
		}
		return toReturn;
	}

}
