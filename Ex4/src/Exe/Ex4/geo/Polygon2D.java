package Exe.Ex4.geo;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 */
public class Polygon2D implements GeoShapeable{
	//data
	private Point2D[] p_arr;


	//constructor
	public Polygon2D(Point2D[] points) {
		this.p_arr = points;
	}


	@Override
	public String toString(){
		String ans = "";
		for (int i = 0; i < p_arr.length; i++) {
			if(i<p_arr.length-1) {
				ans += p_arr[i].toString()+",";
			}else {
				ans += p_arr[i].toString();
			}
		}
		return ans;
	}


	//this method checks how many times can a vector cross the polygon sides if its odd its inside the polygon
	@Override
	public boolean contains(Point2D ot) {
		double minX = p_arr[0].x();
		double maxX = p_arr[0].x();
		double minY = p_arr[0].y();
		double maxY = p_arr[0].y();
		for ( int i = 1 ; i < p_arr.length ; i++ )
		{
			Point2D q = p_arr[ i ];
			minX = Math.min( q.x(), minX );
			maxX = Math.max( q.x(), maxX );
			minY = Math.min( q.y(), minY );
			maxY = Math.max( q.y(), maxY );
		}
		if ( ot.x() < minX || ot.x() > maxX || ot.y() < minY || ot.y() > maxY )
		{return false;}

		boolean inside = false;
		for ( int i = 0, j = p_arr.length - 1 ; i < p_arr.length ; j = i++ ){
			if ((p_arr[i].y()>ot.y()) != (p_arr[j].y()>ot.y()) &&
					ot.x()<(p_arr[j].x()-p_arr[i].x())*(ot.y()-p_arr[i].y())/(p_arr[j].y()-p_arr[i].y())+p_arr[i].x()){
				inside = !inside;
			}
		}
		return inside;
	}

	//computes the area of the polygon by the formula
	@Override
	public double area() {
		// TODO Auto-generated method stub
		double sum = 0;
		for (int i = 0; i < p_arr.length ; i++){
			if (i == 0){
				sum += p_arr[i].x() * (p_arr[i + 1].y() - p_arr[p_arr.length - 1].y());
			}
			else if (i == p_arr.length - 1){
				sum += p_arr[i].x() * (p_arr[0].y() - p_arr[i - 1].y());
			}
			else{
				sum += p_arr[i].x() * (p_arr[i + 1].y() - p_arr[i - 1].y());}
		}
		return 0.5 * Math.abs(sum);
	}

	@Override
	public double perimeter() {
		double ans = 0;
		for (int i = 0; i < p_arr.length; i++) {
			if(i+1 ==p_arr.length) {
				ans += p_arr[i].distance(p_arr[0]);}
			else{ans += p_arr[i].distance(p_arr[i+1]);}
		}
		return ans;
	}

	@Override
	public void move(Point2D vec) {
		for (int i = 0; i < p_arr.length; i++) {
			this.p_arr[i] =p_arr[i].add(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		return new Polygon2D(this.p_arr.clone());
	}

	//this method uses the sister method on Point2D and apply in on the points of this shape
	@Override
	public void scale(Point2D center, double ratio) {
		for (int i = 0; i < p_arr.length; i++) {
			this.p_arr[i].scale(center, ratio);
		}
	}

	//this method uses the sister method on Point2D and apply in on the points of this shape
	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (int i = 0; i < p_arr.length; i++) {
			this.p_arr[i].rotate(center, angleDegrees);;
		}
	}

	@Override
	public Point2D[] getPoints() {
		return this.p_arr;
	}

}
