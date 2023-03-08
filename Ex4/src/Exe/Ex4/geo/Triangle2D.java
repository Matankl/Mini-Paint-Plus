package Exe.Ex4.geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{
	//data
	private Point2D[] T_arr;


	//constructor
	public Triangle2D(Point2D[] points) {
		this.T_arr = points;
	}

	@Override
	public String toString(){
		String ans = "";
		for (int i = 0; i < T_arr.length; i++) {
			if(i<T_arr.length-1) {
				ans += T_arr[i].toString()+",";
			}else {
				ans += T_arr[i].toString();
			}
		}
		return ans;
	}

	@Override
	public boolean contains(Point2D ot) {
		double minX = T_arr[0].x();
		double maxX = T_arr[0].x();
		double minY = T_arr[0].y();
		double maxY = T_arr[0].y();
		for ( int i = 1 ; i < T_arr.length ; i++ )
		{
			Point2D q = T_arr[ i ];
			minX = Math.min( q.x(), minX );
			maxX = Math.max( q.x(), maxX );
			minY = Math.min( q.y(), minY );
			maxY = Math.max( q.y(), maxY );
		}
		if ( ot.x() < minX || ot.x() > maxX || ot.y() < minY || ot.y() > maxY )
		{return false;}

		boolean inside = false;
		for ( int i = 0, j = T_arr.length - 1 ; i < T_arr.length ; j = i++ ){
			if ((T_arr[i].y()>ot.y()) != (T_arr[j].y()>ot.y()) &&
					ot.x()<(T_arr[j].x()-T_arr[i].x())*(ot.y()-T_arr[i].y())/(T_arr[j].y()-T_arr[i].y())+T_arr[i].x()){
				inside = !inside;
			}
		}
		return inside;
	}

	//using Formula (area = 0.5 * ((x1*(y2-y3)) + (x2*(y3-y1)) + (x3*(y1-y2))))
	@Override
	public double area() {
		return (0.5 * (T_arr[0].x()*(T_arr[1].y()-T_arr[2].y()) + T_arr[1].x()*(T_arr[2].y()-T_arr[0].y()) + T_arr[2].x()*(T_arr[0].y()-T_arr[1].y())));
	}

	// this method calculate the length of the triangle sides
	//it dose it by for loop and if we get to the last vertex it measure to the first vertex
	//this method is good for polygon as well
	@Override
	public double perimeter() {
		double ans = 0;
		for (int i = 0; i < T_arr.length; i++) {
			if(i+1 ==T_arr.length) {
				ans += T_arr[i].distance(T_arr[0]);}
			else{ans += T_arr[i].distance(T_arr[i+1]);}
		}
		return ans;
	}
	//adds the vector to every point
	@Override
	public void move(Point2D vec) {
		for (int i = 0; i < T_arr.length; i++) {
			this.T_arr[i] =T_arr[i].add(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		return new Triangle2D(this.T_arr.clone());
	}

	//this method uses the sister method on Point2D and apply in on the points of this shape
	@Override
	public void scale(Point2D center, double ratio) {
		for (int i = 0; i < T_arr.length; i++) {
			this.T_arr[i].scale(center, ratio);
		}
	}

	//this method uses the sister method on Point2D and apply in on the points of this shape
	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (int i = 0; i < T_arr.length; i++) {
			this.T_arr[i].rotate(center, angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		return this.T_arr;
	}

	//______________________________________________________privet________________________________________________//


	//using Heron's Formula
	public double area_Herons_Formula() {
		double s = this.perimeter()/2;
		double q = 1;
		for (int i = 0; i < T_arr.length; i++) {// (p-side *the others)
			if(i+1 ==T_arr.length) {
				q *= s- T_arr[i].distance(T_arr[0]);
			}
			else{q *= s- T_arr[i].distance(T_arr[i+1]);
			}
		}
		return Math.sqrt(s * q);
	}
	//this method use the barycentric coordinate system it calculat if the sum of areas with ot 
	//(with out one different vertex each time) == the original area
	//might have a problem
	public boolean contains_(Point2D ot) {
		double TArea = this.area();
		double subTArea1 = 0.5 * ((ot.x()*(T_arr[1].y()-T_arr[2].y())) + (T_arr[1].x()*(T_arr[2].y()-ot.y())) + (T_arr[2].x()*(ot.y()-T_arr[1].y())));
		double subTArea2 = 0.5 * ((T_arr[0].x()*(ot.y()-T_arr[2].y())) + (ot.x()*(T_arr[2].y()-T_arr[0].y())) + (T_arr[2].x()*(T_arr[0].y()-ot.y())));
		double subTArea3 = 0.5 * ((T_arr[0].x()*(T_arr[1].y()-ot.y())) + (T_arr[1].x()*(ot.y()-T_arr[0].y())) + (ot.x()*(T_arr[0].y()-T_arr[1].y())));
		return (Math.abs(TArea - (Math.abs(subTArea1) + Math.abs(subTArea2) + Math.abs(subTArea3))) < 0.00001);
	}




}


