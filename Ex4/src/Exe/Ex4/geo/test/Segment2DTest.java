package Exe.Ex4.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Segment2D;

class Segment2DTest {

	//data and refresh
	public  Point2D _p1 = new Point2D(1,1);
	public  Point2D _p2 = new Point2D(3,3);

	public Segment2D _Segment = new Segment2D(_p1,_p2);
	public Segment2D _copy = new Segment2D(_p1,_p2);
	
	public final Segment2D _Segment_co = new Segment2D(_p1,_p2);


	@BeforeEach
	public void hadas() {
		Point2D _p1 = new Point2D(1,1);
		Point2D _p2 = new Point2D(3,3);
		_Segment = new Segment2D(_p1,_p2);
		_copy = new Segment2D(_p1,_p2);
	}



	/*Test List

	 * public Segment2D(Point2D p1,Point2D p2)
	 * public boolean contains(Point2D ot)
	 * public String toString()
	 * public double area()
	 * public double perimeter()
	 * public void move(Point2D vec)
	 * public GeoShapeable copy()
	 * 
	 * public void scale(Point2D center, double ratio)
	 * public void rotate(Point2D center, double angleDegrees)
	 * public Point2D[] getPoints()
	 */


	@Test
	void Segment2D_supertest() {
		Segment2D x = new Segment2D(_p1, _p2);

		assertTrue(x instanceof Segment2D);
	}

	/*My Tester format |contains|
	public boolean contains(Point2D ot) 

	 *   Possible inputs |p|:
	 *p is null								answer: brake + no change
	 *p is on the segment					answer: true
	 *p is not on the segmnent				answer: true		
	 *p==_p1||p==_p2 						answer: true
	 */

	@Test
	void contains_supertest() {
		Point2D p1 = new Point2D(1, 1);
		Point2D p2 = new Point2D(2, 2);
		Point2D p3 = new Point2D(7, 5);

				assertTrue(this._Segment.contains(p1));
				assertTrue(this._Segment.contains(p2));
				assertFalse(this._Segment.contains(p3));
	}
	/*My Tester format |toString|
	public String toString() 

	 *   Possible inputs |segment|:
	 *   its comping from point2d class and it has been tested
	 */

	@Test
	void toString_supertest() {
		assertEquals("1.0,1.0, 3.0,3.0", _Segment.toString());
	}

	
	/*My Tester format |area|
	public double area()

	 *the only ans is 0
	 */

	@Test
	void area_supertest() {
		assertEquals(_Segment.area(),0, 0.0005);
	}
	
	/*My Tester format |perimeter|
	public double perimeter() 

	 *   Possible inputs |-|:
	 *	ans = dictance
	 */
	
	@Test
	void perimeter_supertest() {
		assertEquals(_Segment.perimeter(),_p1.distance(_p2),0.0000005);
	}
	
	
	/*My Tester format |move|
	public void move(Point2D vec)

	 *   Possible inputs |vec|:
	 *vec is null								answer: brake + no change
	 *vec is negative							answer: move
	 *vec is positive							answer: move		
	 *vec is 0,0								answer: stay
	 */
	
	
	@Test
	void move_supertest() {
		Point2D vec1 = new Point2D(1, 1);
		Point2D vec2 = new Point2D(2, 2);
		Point2D vec3 = new Point2D(7, 5);

		Segment2D move1 =new Segment2D(_p1.add(vec1),_p2.add(vec1)); hadas();
		Segment2D move2 =new Segment2D(_p1.add(vec2),_p2.add(vec2)); hadas();
		Segment2D move3 =new Segment2D(_p1.add(vec3),_p2.add(vec3)); hadas();
		
		this._Segment.move(vec1);
		assertEquals(_Segment.toString(), move1.toString()); hadas(); hadas();
		this._Segment.move(vec2);
		assertEquals(this._Segment.toString(),move2.toString()); hadas();
		this._Segment.move(vec3);
		assertEquals(this._Segment.toString(),move3.toString()); hadas();
	}
	
	
	
	
	/*My Tester format |copy|
	public GeoShapeable copy()

	 *   Possible inputs |-|:
	 *if copy == original
	 */
	
	@Test
	void copy_supertest() {
		assertEquals(_Segment.toString(),_Segment.copy().toString());
	}

	
	/*My Tester format |scale|
	 public void scale(Point2D cen, double ratio) 
	 
	 to check if the segment had scaled you can check if the peremiter has changed with the same ratio 
	 cause the point "scale" method has been checked in its class

	 *   Possible inputs |cen|:
	 *cen is null					answer: brake + no change
	 *cen is out of bounds			answer: as planed (this.cen move and radius change)
	 *cen is in bounds				answer: as planed (this.cen move and radius change)

	 * 	Possible inputs |ratio|:
	 * ratio is 1					answer: no change
	 * ratio is 0					answer: no change + err massage
	 * ratio < || > 1				answer:	as planed
	 */



	@Test
	void scale_supertest() {
		Segment2D D_copy = new Segment2D(_p1, _p2);
		Point2D cen1 = null;
		Point2D cen2 = new Point2D(-1,-1);
		Point2D cen3 = new Point2D(2,2);

		double ratio1 = 1;
		double ratio2 = 0;
		double ratio3 = 1.5;
		double ratio4 = 0.5;

		_Segment.scale(cen1, ratio3);
		assertEquals(D_copy.perimeter(), _Segment.perimeter(),0.05);			hadas();
		_Segment.scale(cen1, ratio4);
		assertEquals(D_copy.perimeter(), _Segment.perimeter(),0.05);			hadas();
		
		_Segment.scale(cen2, ratio1);
		assertEquals(D_copy.perimeter()*ratio1, _Segment.perimeter(),0.05);		hadas();		
		_Segment.scale(cen2, ratio2);
		assertEquals(D_copy.perimeter(), _Segment.perimeter(),0.05);			hadas();
		_Segment.scale(cen2, ratio3);
		assertEquals(D_copy.perimeter()*ratio3, _Segment.perimeter(),0.05);		hadas();
		_Segment.scale(cen2, ratio4);
		assertEquals(D_copy.perimeter()*ratio4, _Segment.perimeter(),0.05);		hadas();
		
		_Segment.scale(cen3, ratio1);
		assertEquals(D_copy.perimeter()*ratio1, _Segment.perimeter(),0.05);		hadas();
		_Segment.scale(cen3, ratio2);
		assertEquals(D_copy.perimeter(), _Segment.perimeter(),0.05);			hadas();
		_Segment.scale(cen3, ratio3);
		assertEquals(D_copy.perimeter()*ratio3, _Segment.perimeter(),0.05);		hadas();
		_Segment.scale(cen3, ratio4);
		assertEquals(D_copy.perimeter()*ratio4, _Segment.perimeter(),0.05);		hadas();
	}

	/*My Tester format |rotate|
	 public void rotate(Point2D cen, double angleDegrees) 
	 
	 be cause the main test for rotate is in the point its just needed to check if al the points has rotated

	 *   Possible inputs |cen|:
	 *cen is null					answer: brake
	 *cen is out of bounds			answer: as planed
	 *cen is in bounds				answer: as planed

	 * 	Possible inputs |angleDegrees|:
	 * angleDegrees is 0					answer: as planed
	 * angleDegrees is 90				answer: as planed
	 * angleDegrees is -180				answer:	as planed
	 * * angleDegrees is 280				answer:	as planed
	 */
	@Test
	void rotate_supertest() {
		Point2D cen1 = null;
		Point2D cen2 = new Point2D(-1,-1);
		Point2D cen3 = new Point2D(2,2);

		double angleDegrees1 = 0;
		double angleDegrees2 = 90;
		double angleDegrees3 = -180;
		double angleDegrees4 = 270;

		_Segment.rotate(cen1, angleDegrees3);
		_copy.getPoints()[0].rotate(cen1, angleDegrees3);
		_copy.getPoints()[1].rotate(cen1, angleDegrees3);
		assertEquals(_copy.toString(), _Segment.toString()); hadas();
		_Segment.rotate(cen1, angleDegrees4);
		_copy.getPoints()[0].rotate(cen1, angleDegrees4);
		_copy.getPoints()[1].rotate(cen1, angleDegrees4);
		assertEquals(_copy.toString(), _Segment.toString()); hadas();
		
		_Segment.rotate(cen2, angleDegrees1);
		_copy.getPoints()[0].rotate(cen2, angleDegrees1);
		_copy.getPoints()[1].rotate(cen2, angleDegrees1);
		assertEquals(_copy.toString(), _Segment.toString()); hadas();
		_Segment.rotate(cen2, angleDegrees2);
		_copy.getPoints()[0].rotate(cen2, angleDegrees2);
		_copy.getPoints()[1].rotate(cen2, angleDegrees2);
		assertEquals(_copy.toString(), _Segment.toString()); hadas();    
		_Segment.rotate(cen2, angleDegrees3);
		_copy.getPoints()[0].rotate(cen2, angleDegrees3);
		_copy.getPoints()[1].rotate(cen2, angleDegrees3);
		assertEquals(_copy.toString(), _Segment.toString()); hadas();
		_Segment.rotate(cen2, angleDegrees4);
		_copy.getPoints()[0].rotate(cen2, angleDegrees4);
		_copy.getPoints()[1].rotate(cen2, angleDegrees4);
		assertEquals(_copy.toString(), _Segment.toString()); hadas();

		_Segment.rotate(cen3, angleDegrees1);
		_copy.getPoints()[0].rotate(cen3, angleDegrees1);
		_copy.getPoints()[1].rotate(cen3, angleDegrees1);
		assertEquals(_copy.toString(), _Segment.toString()); hadas();
		_Segment.rotate(cen3, angleDegrees2);
		_copy.getPoints()[0].rotate(cen3, angleDegrees2);
		_copy.getPoints()[1].rotate(cen3, angleDegrees2);
		assertEquals(_copy.toString(), _Segment.toString()); hadas();
		_Segment.rotate(cen3, angleDegrees3);
		_copy.getPoints()[0].rotate(cen3, angleDegrees3);
		_copy.getPoints()[1].rotate(cen3, angleDegrees3);
		assertEquals(_copy.toString(), _Segment.toString()); hadas();
		_Segment.rotate(cen3, angleDegrees4);
		_copy.getPoints()[0].rotate(cen3, angleDegrees4);
		_copy.getPoints()[1].rotate(cen3, angleDegrees4);
		assertEquals(_copy.toString(), _Segment.toString()); hadas();
	}
	

	/*My Tester format |getPoints|
	 * public Point2D[] getPoints() 
	  
	 just need to check if the point are real

	 *   Possible inputs |p|:
	 */

	@Test
	void getPoints_supertest() {
		Segment2D copy = new Segment2D(_Segment.getPoints()[0], _Segment.getPoints()[1]);
		
		assertEquals(_Segment.toString(),copy.toString());
	}


	
	
	//______________________________________________________privet________________________________________________//



}