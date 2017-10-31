package imagesearch.ui;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.BasicConfigurator;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.comparator.HistogramComparator;
import imagesearch.controller.Controller;
import imagesearch.controller.SimpleController;
import imagesearch.image.Image;
import imagesearch.image.factory.ImageFactory;
import imagesearch.image.factory.ImageProxyResizedFactory;
import imagesearch.image.factory.OpenimajFactory;
import imagesearch.model.Model;
import imagesearch.model.treemodel.SimpleNodeFactory;
import imagesearch.model.treemodel.TreeModel;

public class Console {
	private static final Object NEW_LINE = "\n";
	private static double THRESHOLD = 1.0;

	public static void main(String[] args) {
		StringBuilder log = new StringBuilder();
		if (args[0] != null && args[1] != null ) {
			File image = new File(args[0]);
			File dir = new File(args[1]);
			if (args[2] != null) {
				THRESHOLD = Float.parseFloat(args[2]);
				System.out.println("Threshold set to "+args[2]);
			}
			tryToMatch(log, image, dir);
		}else {
			log.append("Usage: java -jar imagesearch.jar path/to/image path/to/dir_or_image [threshold]");
		}
		System.out.println(log.toString());
	}

	private static void tryToMatch(StringBuilder log, File image, File dir) {
		if(!image.isFile()) {
			log.append("First argument is not a file.");
		} else if(!dir.isDirectory()) {
			log.append("Second argument is not a directory.");
		}else {
			// create image factory
			ImageFactory f = new ImageProxyResizedFactory(new OpenimajFactory(),0.2f);
			// create comparator
			Comparator comparator = new HistogramComparator(f);
			// create model
			Model model = new TreeModel(new SimpleNodeFactory(), comparator, THRESHOLD);
			// create controller with model and image factory 
			Controller c = new SimpleController(model,f);
			try {
				long time = System.currentTimeMillis();
			c.buildModel(dir);
			System.out.println("model built in " + (System.currentTimeMillis() - time)/1000 + "s");
			}catch (NotCompatibleException e){
				log.append(e.toString()).append(NEW_LINE);
			}
			List<Image> result = null;
			result = trySearch(log, image, c, result);
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

	private static List<Image> trySearch(StringBuilder log, File image, Controller c, List<Image> result) {
		try {
			result=c.searchInModel(image);
		}catch(NotCompatibleException e) {
			log.append(e.toString()).append(NEW_LINE);
		}
		return result;
	}
}
