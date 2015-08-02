package qaobee.com.networkgraph.graph.beans;

public class Point2D {
	public double x;
	public double y;

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Point2D() {
		this.x = 0;
		this.y = 0;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void setLocation(Point2D p) {
		this.x = p.x;
		this.y = p.y;
	}
}
