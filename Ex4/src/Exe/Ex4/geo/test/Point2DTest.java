package Exe.Ex4.geo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.geo.Point2D;

class Point2DTest {
	public Point2D _p = new Point2D(1,1);
	public final Point2D p_const = new Point2D(1,1);

	public void hadas() {
		_p = new Point2D(1,1);
	}


	/*My Tester format |scale|
	 public void scale(Point2D cen, double ratio) 

	 *   Possible inputs |cen|:
	 *cen is null					answer: brake + no change
	 *cen is out of bounds			answer: as planed
	 *cen is in bounds				answer: as planed

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

		_p.scale(cen1, ratio3);
		assertEquals(_p, p_const); hadas();
		_p.scale(cen1, ratio4);
		assertEquals(_p , p_const); hadas();

		_p.scale(cen2, ratio1);
		assertEquals(1, _p.x(),0.05); 
		assertEquals(1, _p.y(),0.05); hadas();  
		_p.scale(cen2, ratio2);
		assertEquals(1, _p.x(),0.05); 
		assertEquals(1, _p.y(),0.05); hadas();  
		_p.scale(cen2, ratio3);
		assertEquals(new Point2D(2, 2), this._p);////////////////////////////////////////////////////////// 
		assertEquals(2, _p.x(),0.05); 
		assertEquals(2, _p.y(),0.05); hadas();  
		_p.scale(cen2, ratio4);
		assertEquals(0, _p.x(),0.05); 
		assertEquals(0, _p.y(),0.05); hadas();  

		_p.scale(cen3, ratio1);
		assertEquals(1, _p.x(),0.05); 
		assertEquals(1, _p.y(),0.05); hadas();  
		_p.scale(cen3, ratio2);
		assertEquals(1, _p.x(),0.05); 
		assertEquals(1, _p.y(),0.05); hadas();  
		_p.scale(cen3, ratio3);
		assertEquals(0.5, _p.x(),0.05); 
		assertEquals(0.5, _p.y(),0.05); hadas();  
		_p.scale(cen3, ratio4);
		assertEquals(1.5, _p.x(),0.05); 
		assertEquals(1.5, _p.y(),0.05); hadas();  

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

		_p.rotate(cen1, angleDegrees3);
		assertEquals(_p, p_const); hadas();
		_p.rotate(cen1, angleDegrees4);
		assertEquals(_p , p_const); hadas();

		_p.rotate(cen2, angleDegrees1);
		assertEquals(p_const, _p); hadas();
		_p.rotate(cen2, angleDegrees2);
		assertEquals(1, _p.x(),0.001); 
		assertEquals(-3, _p.y(),0.001); hadas();     
		_p.rotate(cen2, angleDegrees3);
		assertEquals(-3, _p.x(),0.001); 
		assertEquals(-3, _p.y(),0.001); hadas();
		_p.rotate(cen2, angleDegrees4);
		assertEquals(-3, _p.x(),0.001); 
		assertEquals(1, _p.y(),0.001); hadas();	

		_p.rotate(cen3, angleDegrees1);
		assertEquals(p_const, _p); hadas();
		_p.rotate(cen3, angleDegrees2);
		assertEquals(1, _p.x(),0.001); 
		assertEquals(3, _p.y(),0.001); hadas();	
		_p.rotate(cen3, angleDegrees3);
		assertEquals(3, _p.x(),0.001); 
		assertEquals(3, _p.y(),0.001); hadas();	
		_p.rotate(cen3, angleDegrees4);
		assertEquals(3, _p.x(),0.001); 
		assertEquals(1, _p.y(),0.001); hadas();			
	}


}
