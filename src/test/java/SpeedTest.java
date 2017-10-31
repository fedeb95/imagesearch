import java.io.File;

import org.junit.Before;
import org.junit.Test;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.BufferedHistogramComparator;
import imagesearch.comparator.Comparator;
import imagesearch.comparator.HistogramComparator;
import imagesearch.image.Image;
import imagesearch.image.factory.ImageFactory;
import imagesearch.image.factory.ImageProxyResizedFactory;
import imagesearch.image.factory.OpenimajFactory;
import imagesearch.model.BruteModel;
import imagesearch.model.Model;
import imagesearch.model.treemodel.SimpleNodeFactory;
import imagesearch.model.treemodel.TreeModel;

public class SpeedTest extends AbstractTestWithImages{

	private static final int N = 10;
	private static final double THRESHOLD = 0.1;
	ImageFactory factory;
	private Comparator comparator;
	
	@Before
	public void setUp() {
		this.factory =new ImageProxyResizedFactory(new OpenimajFactory(), 0.2f);
		this.comparator = new HistogramComparator(factory);
	}

	@Test
	public void testBruteModel() throws NotCompatibleException {
		runModel(new BruteModel(comparator, THRESHOLD));
	}
	
	@Test
	public void testTreeModel() throws NotCompatibleException {
		runModel(new TreeModel(new SimpleNodeFactory(), comparator, THRESHOLD));
	}
	
	private void runModel(Model model) throws NotCompatibleException {
		model.addDirectory("/home/federico/Immagini/campovecchio");
		Image img = factory.create(new File(PATH+"1.jpg"));
		for (int i = 0; i < N; i++) {
			model.search(img);
		}
	}
}
