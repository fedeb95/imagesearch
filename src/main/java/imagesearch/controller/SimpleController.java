package imagesearch.controller;

import java.io.File;
import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.image.Image;
import imagesearch.image.factory.ImageFactory;
import imagesearch.model.Model;

public class SimpleController implements Controller {
	
	private Model model;
	private ImageFactory imageFactory;

	public SimpleController (Model model, ImageFactory f) {
		this.model = model;
		this.imageFactory = f;
	}
	
	/* (non-Javadoc)
	 * @see imagesearch.controller.Controller#buildModel(java.io.File)
	 */
	@Override
	public void buildModel(File dir) throws NotCompatibleException {
		this.model.addDirectory(dir.getAbsolutePath());
	}
	
	/* (non-Javadoc)
	 * @see imagesearch.controller.Controller#searchInModel(java.io.File)
	 */
	@Override
	public List<Image> searchInModel(File image) throws NotCompatibleException{
		if (image.isFile()) { 
			Image img = imageFactory.create(image);
			return model.search(img);
		}
		return null;
	}

}
