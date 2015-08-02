package qaobee.com.networkgraph.graph.beans;

public class Dimension {
	int width;
	int height;
	public Dimension(int w, int h) {
		width = w;
		height = h;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Dimension) {
			Dimension other = (Dimension) o;
			return (width == other.width) && (height == other.height);
		}
		
		return false;
	}
}
