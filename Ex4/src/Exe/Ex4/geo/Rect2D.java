package Exe.Ex4.geo;


/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {
	//data
	//use p1 as top left point and p2 as bottom right point
	public Point2D[] points = new Point2D[4];
	

	//constructor
	//this constructor use p1 as top left point and p2 as bottom right point
	public Rect2D (Point2D p1,Point2D p2) {
		this.points[0] = new Point2D( Math.min(p1.x(), p2.x()),Math.max(p1.y(), p2.y()));
		this.points[2] = new Point2D( Math.max(p1.x(), p2.x()),Math.min(p1.y(), p2.y()));
		this.points[1] = new Point2D(points[0].x(), points[2].y());
		this.points[3] = new Point2D(points[2].x(), points[0].y());
	}

	@Override
	public String toString(){
		String ans = "";
		for (int i = 0; i < points.length; i++) {
			if(i<points.length-1) {
				ans += points[i].toString()+",";
			}else {
				ans += points[i].toString();
			}
		}
		return ans;
	}


	//if in the range of p1 and p2 x and y points its in the rect
	@Override
	public boolean contains(Point2D ot) {
		double minX = points[0].x();
		double maxX = points[0].x();
		double minY = points[0].y();
		double maxY = points[0].y();
		for ( int i = 1 ; i < points.length ; i++ )
		{
			Point2D q = points[ i ];
			minX = Math.min( q.x(), minX );
			maxX = Math.max( q.x(), maxX );
			minY = Math.min( q.y(), minY );
			maxY = Math.max( q.y(), maxY );
		}
		if ( ot.x() < minX || ot.x() > maxX || ot.y() < minY || ot.y() > maxY )
		{return false;}

		boolean inside = false;
		for ( int i = 0, j = points.length - 1 ; i < points.length ; j = i++ ){
			if ((points[i].y()>ot.y()) != (points[j].y()>ot.y()) &&
					ot.x()<(points[j].x()-points[i].x())*(ot.y()-points[i].y())/(points[j].y()-points[i].y())+points[i].x()){
				inside = !inside;
			}
		}
		return inside;
	}
	//just simple rectangle area formula Width*Hight
	@Override
	public double area() {
		return this.getHight()*this.getWidth();
	}

	//just simple rectangle perimeter formula Width*2 + Hight*2
	@Override
	public double perimeter() {
		return this.getHight()*2 + this.getWidth()*2;
	}

	//add the vector to the points
	@Override
	public void move(Point2D vec) {
		for (int i = 0; i < points.length; i++) {
			this.points[i] =points[i].add(vec);
		}
	}

	//make a new rectangle and returns it (this is an implantation for GeoShapeable)
	@Override
	public GeoShapeable copy() {
		return new Rect2D(points[0],points[2]);
	}

	//this method uses the sister method on Point2D and apply in on the points of this shape
	@Override
	public void scale(Point2D center, double ratio) {
		for (int i = 0; i < points.length; i++) {
			this.points[i].scale(center, ratio);
		}
	}

	//this method uses the sister method on Point2D and apply in on the points of this shape
	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (int i = 0; i < points.length; i++) {
			this.points[i].rotate(center, angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		return this.points;
	}



	//______________________________________________________privet________________________________________________//


	public double getWidth() {
		return this.points[1].distance(this.points[2]);
	}
	public double getHight() {
		return this.points[0].distance(this.points[1]);
	}

	public Point2D get_center() {
		double _x = (this.points[0].x()+this.points[2].x())/2;
		double _y = (this.points[0].y()+this.points[2].y())/2;
		Point2D ans = new Point2D(_x,_y);
		return ans;
	}
	public Point2D getCen() {
		 this.cen = new Point2D((this.points[0].x() + this.points[2].x())/2,(this.points[0].y() + this.points[2].y())/2);
		
		return this.cen;
	}
  
	public double getHalfWidth() {
		this.HalfWidth = Math.abs(this.points[0].x()  - this.getCen().x());
		
		return this.HalfWidth;
	}
	public double getHalfHieght() {
		this.HalfHeight = Math.abs(this.points[0].y()  - this.getCen().y());
		return this.HalfHeight;
	}

	
	private double HalfWidth;
	   private double HalfHeight;
	   private Point2D cen;
	
	
}
