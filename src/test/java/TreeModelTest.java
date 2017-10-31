import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import imagesearch.NotCompatibleException;
import imagesearch.comparator.Comparator;
import imagesearch.image.Image;
import imagesearch.model.treemodel.SimpleImageNode;
import imagesearch.model.treemodel.SimpleImageTree;

public class TreeModelTest {

	@Test
	public void testImageTree() throws NotCompatibleException {
		Comparator comp = new FakeComparator();
		SimpleImageTree tree = new SimpleImageTree(comp, 1f);
		tree.insert(new SimpleImageNode(getFakeImage(1)));
		tree.insert(new SimpleImageNode(getFakeImage(0)));
		tree.insert(new SimpleImageNode(getFakeImage(2)));
		tree.insert(new SimpleImageNode(getFakeImage(3)));
		List<Image> res = tree.pathToLeaf(getFakeImage(2));
		assertEquals(3, res.size());
		assertEquals(1, res.get(0).getImg());
		assertEquals(2, res.get(1).getImg());
		assertEquals(3, res.get(2).getImg());
	}
	
	private FakeImage getFakeImage(int n) {
		FakeImage im = new FakeImage();
		im.setImg(n);
		return im;
	}
}
