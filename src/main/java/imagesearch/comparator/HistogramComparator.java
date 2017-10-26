package imagesearch.comparator;
import org.openimaj.feature.DoubleFVComparison;
import org.openimaj.image.MBFImage;
import org.openimaj.image.pixel.statistics.HistogramModel;
import org.openimaj.math.statistics.distribution.MultidimensionalHistogram;

import imagesearch.NotCompatibleException;
import imagesearch.image.Image;

public class HistogramComparator implements Comparator{
	
	private HistogramModel model;

	public HistogramComparator(){
		this.model = new HistogramModel( 4, 4, 4 );
	}
	
	public double compare(Image img1, Image img2) throws NotCompatibleException{
		Object image1 = img1.getImg();
		Object image2 = img2.getImg();
		if (!((image1 instanceof MBFImage) && (image2 instanceof MBFImage)))
			throw new NotCompatibleException(image1.getClass().getSimpleName() + " " +
					image2.getClass().getSimpleName() + " not compatible with MBFImage");
		this.model.estimateModel((MBFImage)image1);
		MultidimensionalHistogram histogram1 = this.model.histogram.clone();
		this.model.estimateModel((MBFImage)img2.getImg());
		MultidimensionalHistogram histogram2 = this.model.histogram;
		return histogram1.compare( histogram2, DoubleFVComparison.EUCLIDEAN );
	}
}
