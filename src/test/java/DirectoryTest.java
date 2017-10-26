import java.io.File;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import imagesearch.NotCompatibleException;

public class DirectoryTest extends AbstractTestWithImages{

	@Test @Ignore
	public void test() throws IOException, NotCompatibleException {
		File folder = new File("/media/federico/Volume1/space_collection");
		String imgToCompare = PATH+"1.jpg";
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f != null && f.isFile()) {
				double result = compare(f.getAbsolutePath(),imgToCompare);
				System.out.println("1 - " + f.getName() + ": " + result);
			}
		}
	}

}
