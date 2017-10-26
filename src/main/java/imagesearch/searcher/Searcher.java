package imagesearch.searcher;

import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.image.Image;
import imagesearch.model.Model;

public interface Searcher {
	List<Image> search(Image img, Comparator comp, Model model) throws NotCompatibleException;
}
