import imagesearch.image.Image;

public class FakeImage implements Image {

	private int n;

	@Override
	public Object getImg() {
		return this.n;
	}

	@Override
	public void setImg(Object img) {
		this.n = (int)img;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPath(String path) {
		// TODO Auto-generated method stub

	}

}
