package imagesearch.comparator;
import java.util.Properties;

import org.apache.jcs.JCS;
import org.apache.jcs.access.CacheAccess;
import org.apache.jcs.access.exception.CacheException;
import org.apache.jcs.engine.control.CompositeCacheManager;
import org.openimaj.feature.DoubleFVComparison;
import org.openimaj.image.MBFImage;
import org.openimaj.image.pixel.statistics.HistogramModel;
import org.openimaj.math.statistics.distribution.MultidimensionalHistogram;

import imagesearch.NotCompatibleException;
import imagesearch.image.Image;
import imagesearch.image.factory.ImageFactory;

public class HistogramComparator implements Comparator{
	
	private HistogramModel model;
	private ImageFactory factory;
	private CacheAccess histogramCache;


	public HistogramComparator(ImageFactory factory){
		configureCache();
		try {
			this.histogramCache = JCS.getInstance("histogramCache");
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.model = new HistogramModel( 4, 4, 4 );
		this.factory = factory;
	}
	
	private void configureCache() {
		// TODO Auto-generated method stub
		CompositeCacheManager ccm = CompositeCacheManager.getUnconfiguredInstance();
		Properties props = new Properties();

		props.put("jcs.default","DC");
		props.put("jcs.default.cacheattributes",
		          "org.apache.jcs.engine.CompositeCacheAttributes");
		props.put("jcs.default.cacheattributes.MaxObjects", "1000");
		props.put("jcs.default.cacheattributes.MemoryCacheName", "org.apache.commons.jcs.engine.memory.lru.LRUMemoryCache");
		ccm.configure(props);
	}

	public ImageFactory getImageFactory() {
		return this.factory;
	}
	
	public double compare(Image img1, Image img2) throws NotCompatibleException{
		if (this.histogramCache.get(img1.getPath()) == null) {
			loadHistogram(img1);
			compare(img1, img2);
		}
		if (this.histogramCache.get(img2.getPath()) == null) {
			loadHistogram(img2);
			compare(img1, img2);
		}
		MultidimensionalHistogram histogram1 = (MultidimensionalHistogram) this.histogramCache.get(img1.getPath());
		MultidimensionalHistogram histogram2 = (MultidimensionalHistogram) this.histogramCache.get(img2.getPath());
		return histogram1.compare( histogram2, DoubleFVComparison.EUCLIDEAN );
	}
	
	private void loadHistogram(Image img) throws NotCompatibleException {
		Object image = img.getImg();
		if(!(image instanceof MBFImage))
			throw new NotCompatibleException(image.getClass().getSimpleName() + 
					" not compatible with MBFImage");
		this.model.estimateModel((MBFImage)image);
		MultidimensionalHistogram toInsert = this.model.histogram.clone();
		try {
			this.histogramCache.put(img.getPath(), toInsert);
		} catch (CacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
