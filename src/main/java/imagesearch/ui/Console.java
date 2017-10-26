package imagesearch.ui;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import imagesearch.NotCompatibleException;
import imagesearch.controller.Controller;
import imagesearch.image.Image;

public class Console {
	private static final Object NEW_LINE = "\n";

	public static void main(String[] args) {
		StringBuilder log = new StringBuilder();
		if (args[0] != null && args[1] != null) {
			File image = new File(args[0]);
			File dir = new File(args[1]);
			tryToMatch(log, image, dir);
		}else {
			log.append("Usage: java -jar imagesearch.jar path/to/image path/to/dir_or_image");
		}
		System.out.println(log.toString());
	}

	private static void tryToMatch(StringBuilder log, File image, File dir) {
		if(!image.isFile()) {
			log.append("First argument is not a file.");
		} else if(!image.isDirectory()) {
			log.append("Second argument is not a directory.");
		}else {
			Controller c = new Controller();
			List<Image> result = null;
			result = trySearch(log, image, dir, c, result);
			if (result != null) {
				logMatches(log, result);
			}else {
				log.append("No match found!");
			}
		}
	}

	private static void logMatches(StringBuilder log, List<Image> result) {
		Iterator<Image> iter = result.iterator(); 
		while(iter.hasNext()) {
			Image toPrint = iter.next();
			log.append(toPrint.getPath()).append(NEW_LINE);
		}
	}

	private static List<Image> trySearch(StringBuilder log, File image, File dir, Controller c, List<Image> result) {
		try {
			result=c.searchInDirectory(image, dir);
		}catch(NotCompatibleException e) {
			log.append(e.toString()).append(NEW_LINE);
		}
		return result;
	}
}
