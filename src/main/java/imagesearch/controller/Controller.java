package imagesearch.controller;

import java.io.File;
import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.image.Image;

public interface Controller {

	void buildModel(File dir) throws NotCompatibleException;

	List<Image> searchInModel(File image) throws NotCompatibleException;

}