package Exe.Ex4.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;

class Circle2DTest {

	//data and refresh
	public final Point2D _center = new Point2D(1,1);
	
	public  Circle2D _circle = new Circle2D(_center,5);

	
	
	@BeforeEach
	public void hadas() {
		_circle = new Circle2D(_center,5);;
	}






	/*My Tester format |scale|
	 public void scale(Point2D cen, double ratio) 

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
			Point2D cen1 = null;
			Point2D cen2 = new Point2D(-1,-1);
			Point2D cen3 = new Point2D(2,2);

			double ratio1 = 1;
			double ratio2 = 0;
			double ratio3 = 1.5;
			double ratio4 = 0.5;

			_circle.scale(cen1, ratio3);
			assertEquals(1, _circle.getPoints()[0].x(),0.05); 
			assertEquals(1, _circle.getPoints()[0].y(),0.05);
			assertEquals(5, _circle.getRadius(),0.05);		hadas();
			_circle.scale(cen1, ratio4);
			assertEquals(1, _circle.getPoints()[0].x(),0.05); 
			assertEquals(1, _circle.getPoints()[0].y(),0.05);
			assertEquals(5, _circle.getRadius(),0.05);		hadas();

			_circle.scale(cen2, ratio1);
			assertEquals(1, _circle.getPoints()[0].x(),0.05); 
			assertEquals(1, _circle.getPoints()[0].y(),0.05);
			hadas();  
			_circle.scale(cen2, ratio2);
			assertEquals(1, _circle.getPoints()[0].x(),0.05); 
			assertEquals(1, _circle.getPoints()[0].y(),0.05);
			assertEquals(5, _circle.getRadius(),0.05);		hadas();
			
			_circle.scale(cen2, ratio3);
			assertEquals(2, _circle.getPoints()[0].x(),0.05); 
			assertEquals(2, _circle.getPoints()[0].y(),0.05);
			assertEquals(7.5, _circle.getRadius(),0.05);		hadas();
			_circle.scale(cen2, ratio4);
			assertEquals(0, _circle.getPoints()[0].x(),0.05); 
			assertEquals(0, _circle.getPoints()[0].y(),0.05); 
			assertEquals(2.5, _circle.getRadius(),0.05);		hadas();

			_circle.scale(cen3, ratio1);
			assertEquals(1, _circle.getPoints()[0].x(),0.05); 
			assertEquals(1, _circle.getPoints()[0].y(),0.05); 
			assertEquals(5, _circle.getRadius(),0.05);		hadas();
			_circle.scale(cen3, ratio2);
			assertEquals(1, _circle.getPoints()[0].x(),0.05); 
			assertEquals(1, _circle.getPoints()[0].y(),0.05); 
			assertEquals(5, _circle.getRadius(),0.05);		hadas();
			_circle.scale(cen3, ratio3);
			assertEquals(0.5, _circle.getPoints()[0].x(),0.05); 
			assertEquals(0.5, _circle.getPoints()[0].y(),0.05);
			assertEquals(7.5, _circle.getRadius(),0.05);		hadas();
			_circle.scale(cen3, ratio4);
			assertEquals(1.5, _circle.getPoints()[0].x(),0.05); 
			assertEquals(1.5, _circle.getPoints()[0].y(),0.05);
			assertEquals(2.5, _circle.getRadius(),0.05);		hadas();

		}

	/*My Tester format |rotate|
	 public void rotate(Point2D cen, double angleDegrees) 

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

		_circle.rotate(cen1, angleDegrees3);
		assertEquals(1, _circle.getPoints()[0].x(),0.05); 
		assertEquals(1, _circle.getPoints()[0].y(),0.05); hadas();
		_circle.rotate(cen1, angleDegrees4);
		assertEquals(1, _circle.getPoints()[0].x(),0.05); 
		assertEquals(1, _circle.getPoints()[0].y(),0.05); hadas();

		_circle.rotate(cen2, angleDegrees1);
		assertEquals(1, _circle.getPoints()[0].x(),0.001); 
		assertEquals(1, _circle.getPoints()[0].y(),0.001); hadas();
		_circle.rotate(cen2, angleDegrees2);
		assertEquals(1, _circle.getPoints()[0].x(),0.001); 
		assertEquals(-3, _circle.getPoints()[0].y(),0.001); hadas();     
		_circle.rotate(cen2, angleDegrees3);
		assertEquals(-3, _circle.getPoints()[0].x(),0.001); 
		assertEquals(-3, _circle.getPoints()[0].y(),0.001); hadas();
		_circle.rotate(cen2, angleDegrees4);
		assertEquals(-3, _circle.getPoints()[0].x(),0.001); 
		assertEquals(1, _circle.getPoints()[0].y(),0.001); hadas();	

		_circle.rotate(cen3, angleDegrees1);
		assertEquals(1, _circle.getPoints()[0].x(),0.001); 
		assertEquals(1, _circle.getPoints()[0].y(),0.001); hadas();
		_circle.rotate(cen3, angleDegrees2);
		assertEquals(1, _circle.getPoints()[0].x(),0.001); 
		assertEquals(3, _circle.getPoints()[0].y(),0.001); hadas();	
		_circle.rotate(cen3, angleDegrees3);
		assertEquals(3, _circle.getPoints()[0].x(),0.001); 
		assertEquals(3, _circle.getPoints()[0].y(),0.001); hadas();	
		_circle.rotate(cen3, angleDegrees4);
		assertEquals(3, _circle.getPoints()[0].x(),0.001); 
		assertEquals(1, _circle.getPoints()[0].y(),0.001); hadas();			
	}


}
