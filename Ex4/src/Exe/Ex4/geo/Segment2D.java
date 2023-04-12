package Exe.Ex4.geo;


/**
 * This class represents a 2D segment on the plane, 
 */
public class Segment2D implements GeoShapeable{
	//data
	private Point2D p1,p2;
	//add length?

	//constructor (2 points) 
	public Segment2D(Point2D p1,Point2D p2) {
		if(p1 == null ||p2 == null) {return;}
		this.p1 = new Point2D(p1);
		this.p2 = new Point2D(p2);
	}


	//	use the equation of line and check if on the line 
	@Override
	public boolean contains(Point2D ot) {
	//	boolean ans = ot.distance(p1) + ot.distance(p2)== p1.distance(p2); distance calc
		return(this.get_slope()*ot.x()+this.get_Ycut() == ot.y()); //formula calc
	}
	
	@Override
	public String toString(){
		return p1.toString()+", "+ p2.toString();
		}
	
	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		return p1.distance(p2);
	}

	@Override
	public void move(Point2D vec) {
		this.p1 = p1.add(vec);
		this.p2 = p2.add(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Segment2D(this.p1,this.p2);
	}
	
	//this method uses the sister method on Point2D and apply in on the points of this shape
	@Override
	public void scale(Point2D center, double ratio) {
		this.p1.scale(center, ratio);
		this.p2.scale(center, ratio);

	}
	
	//this method uses the sister method on Point2D and apply in on the points of this shape
	@Override
	public void rotate(Point2D center, double angleDegrees) {
		this.p1.rotate(center, angleDegrees);
		this.p2.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		Point2D[] ans = {p1,p2}; 
		return ans;
	}

	//______________________________________________________privet________________________________________________//
	public double get_slope() {
		double ans = 99999999;
		try {
			ans = (this.p1.y()-this.p2.y())/(this.p1.x()-this.p2.x());
		}catch(ArithmeticException e) {System.err.println("division by 0");}	
		return ans;

	}

	public double get_Ycut() {
		double ans = this.p1.y()-(this.p1.x()*this.get_slope());
		return ans;


	}
}
