package imagesearch.image.factory;

import java.io.File;

import imagesearch.image.Image;

public interface ImageFactory {

	Image create(File file);

}
