package imagesearch.image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.vfs2.FileNotFolderException;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

public class ImageProxy implements Image {
	
	private String path;

	public ImageProxy(String path) {
		this.path = path;
	}

	@Override
	public Object getImg() {
		MBFImage image; 
		try {
			image = ImageUtilities.createMBFImage(ImageIO.read(new File(this.path)),false);
		}catch(IOException e) {
			image = null;
		}
		return image;
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
