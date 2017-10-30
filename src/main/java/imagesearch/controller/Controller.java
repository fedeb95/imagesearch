package imagesearch.controller;

import java.io.File;
import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.image.Image;
import imagesearch.image.ImageProxy;
import imagesearch.model.Model;

public class Controller {
	
	private Model model;

	public Controller (Model model, Comparator comp) {
		this.model = model;
	}
	
	public List<Image> searchInDirectory(File image, File dir) throws NotCompatibleException{
		if (image.isFile()) { 
			model.addDirectory(dir.getAbsolutePath());
			Image img = new ImageProxy(image.getAbsolutePath());
			return model.search(img);
		}
		return null;
	}

}
