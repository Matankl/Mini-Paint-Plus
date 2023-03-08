package Exe.Ex4.geo.test;

	import static org.junit.Assert.assertEquals;
	import static org.junit.jupiter.api.Assertions.*;

	import java.awt.Point;

	import org.junit.Ignore;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Disabled;
	import org.junit.jupiter.api.Test;
	import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Segment2D;
	import Exe.Ex4.geo.Triangle2D;
	
	
	
class Polygon2DTest {



		//data and refresh
		public final Point2D _p1 = new Point2D(1,1);
		public final Point2D _p2 = new Point2D(3,3);
		public final Point2D _p3 = new Point2D(1,3);
		public final Point2D _p4 = new Point2D(-1,3);
		public final Point2D _p5 = new Point2D(-1,1);
		
		
		public Point2D[] points = new Point2D[5];
		public Point2D[] points_copy = new Point2D[5];
		
		public void set_array() {
			points[0] = new Point2D(_p1);
			points[1] = new Point2D(_p2);
			points[2] = new Point2D(_p3);
			points[3] = new Point2D(_p4);
			points[4] = new Point2D(_p5);
			
			points_copy[0] = new Point2D(_p1);
			points_copy[1] = new Point2D(_p2);
			points_copy[2] = new Point2D(_p3);
			points_copy[3] = new Point2D(_p4);
			points_copy[4] = new Point2D(_p5);
		}

		public Polygon2D _Polygon = new Polygon2D(points);
		public Polygon2D _copy = new Polygon2D(points_copy);


		@BeforeEach
		public void hadas() {
			set_array();
			_Polygon = new Polygon2D(points);
			_copy = new Polygon2D(points_copy);
		}



		/*Test List

		 * public Segment2D(Point2D p1,Point2D p2)
		 * public boolean contains(Point2D ot)
		 * public String toString()
		 * public double area()
		 * public double perimeter()
		 * public void move(Point2D vec)
		 * public GeoShapeable copy()
		 * public void scale(Point2D center, double ratio)
		 * public void rotate(Point2D center, double angleDegrees)
		 * public Point2D[] getPoints()
		 */


		@Test
		void Polygon2D_supertest() {
			set_array();
			Polygon2D x = new Polygon2D(points);

			assertTrue(x instanceof Polygon2D);
		}

		/*My Tester format |contains|
			public boolean contains(Point2D ot) 

		 *   Possible inputs |p|:
		 *p is null								answer: brake + no change
		 *p is on the triangle sides			answer: true
		 *p is not in the triangle				answer: true		
		 *p==_p1||p==_p2 						answer: true
		 */

		@Test
		void contains_supertest() {
			set_array();
//			Point2D p1 = new Point2D(1, 1);
			Point2D p2 = new Point2D(2, 2.5);
			Point2D p3 = new Point2D(7, 5);

//			assertTrue(this._Polygon.contains(p1));
			assertTrue(this._Polygon.contains(p2));
			assertFalse(this._Polygon.contains(p3));
		}
		/*My Tester format |toString|
			public String toString() 

		 *   Possible inputs |triangle|:
		 *   its comping from point2d class and it has been tested
		 */

		@Test
		void toString_supertest() {
			set_array();
			assertEquals("1.0,1.0,3.0,3.0,1.0,3.0,-1.0,3.0,-1.0,1.0", _Polygon.toString());
		}


		/*My Tester format |area|
			public double area()

		 *check if true value
		 *the area should be: 6
		 */

		@Test
		void area_supertest() {
			set_array();
			assertEquals(_Polygon.area(),6, 0.0005);
		}

		/*My Tester format |perimeter|
			public double perimeter() 

		 *   Possible inputs |-|:
		 *	check if true value
		 * *the perimeter should be: 10.828
		 */

		@Test
		void perimeter_supertest() {
			set_array();
			assertEquals(_Polygon.perimeter(),10.828,0.005);
		}


		/*My Tester format |move|
			public void move(Point2D vec)

			this method relay on the sister method apply on Point2D,
			in this one if the sister method activated on all the points it considered as working method

		 *   Possible inputs |vec|:
		 *vec is null								answer: brake + no change
		 *vec is negative							answer: move
		 *vec is positive							answer: move		
		 *vec is 0,0								answer: stay
		 */


		@Test
		void move_supertest() {
			set_array();
			Point2D vec1 = new Point2D(1, 1);
			Point2D vec2 = new Point2D(-2, 2);
			Point2D vec3 = new Point2D(7, 5);

			movelooper(vec1);
			Polygon2D move1 =new Polygon2D(points); hadas();
			movelooper(vec2);
			Polygon2D move2 =new Polygon2D(points); hadas();
			movelooper(vec3);
			Polygon2D move3 =new Polygon2D(points); hadas();

			this._Polygon.move(vec1);
			assertEquals(_Polygon.toString(), move1.toString()); hadas();
			this._Polygon.move(vec2);
			assertEquals(_Polygon.toString(),move2.toString()); hadas();
			this._Polygon.move(vec3);
			assertEquals(_Polygon.toString(),move3.toString()); hadas();
		}




		/*My Tester format |copy|
			public GeoShapeable copy()

		 *   Possible inputs |-|:
		 *if copy == original
		 */

		@Test
		void copy_supertest() {
			set_array();
			Polygon2D Deep_copy = (Polygon2D) _Polygon.copy();
			
			assertEquals(_Polygon.toString(),Deep_copy.toString());
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
			set_array();
			Point2D cen1 = null;
			Point2D cen2 = new Point2D(-1,-1);
			Point2D cen3 = new Point2D(2,2);

			double ratio1 = 1;
			double ratio2 = 0;
			double ratio3 = 1.5;
			double ratio4 = 0.5;

			_Polygon.scale(cen1, ratio3);
			assertEquals(_copy.perimeter(), _Polygon.perimeter(),0.05);				hadas();
			_Polygon.scale(cen1, ratio4);
			assertEquals(_copy.perimeter(), _Polygon.perimeter(),0.05);				hadas();

			_Polygon.scale(cen2, ratio1);
			assertEquals(_copy.perimeter()*ratio1, _Polygon.perimeter(),0.05);		hadas();		
			_Polygon.scale(cen2, ratio2);
			assertEquals(_copy.perimeter(), _Polygon.perimeter(),0.05);				hadas();
			System.out.println(_copy.getPoints()[0].toString());
			_Polygon.scale(cen2, ratio3);
			System.out.println(_copy.getPoints()[0].toString());
			assertEquals(_copy.perimeter()*ratio3, _Polygon.perimeter(),0.05);		hadas();
			_Polygon.scale(cen2, ratio4);
			assertEquals(_copy.perimeter()*ratio4, _Polygon.perimeter(),0.05);		hadas();

			_Polygon.scale(cen3, ratio1);
			assertEquals(_copy.perimeter()*ratio1, _Polygon.perimeter(),0.05);		hadas();
			_Polygon.scale(cen3, ratio2);
			assertEquals(_copy.perimeter(), _Polygon.perimeter(),0.05);			hadas();
			_Polygon.scale(cen3, ratio3);
			assertEquals(_copy.perimeter()*ratio3, _Polygon.perimeter(),0.05);		hadas();
			_Polygon.scale(cen3, ratio4);
			assertEquals(_copy.perimeter()*ratio4, _Polygon.perimeter(),0.05);		hadas();
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

			_Polygon.rotate(cen1, angleDegrees3);
			rotatelooper(cen1, angleDegrees3);
			assertEquals(_copy.toString(), _Polygon.toString()); hadas();
			_Polygon.rotate(cen1, angleDegrees4);
			rotatelooper(cen1, angleDegrees4);
			assertEquals(_copy.toString(), _Polygon.toString()); hadas();

			_Polygon.rotate(cen2, angleDegrees1);
			rotatelooper(cen1, angleDegrees3);
			assertEquals(_copy.toString(), _Polygon.toString()); hadas();
			_Polygon.rotate(cen2, angleDegrees2);
			rotatelooper(cen2, angleDegrees2);
			assertEquals(_copy.toString(), _Polygon.toString()); hadas();   
			_Polygon.rotate(cen2, angleDegrees3);
			rotatelooper(cen2, angleDegrees3);
			assertEquals(_copy.toString(), _Polygon.toString()); hadas();
			_Polygon.rotate(cen2, angleDegrees4);
			rotatelooper(cen2, angleDegrees4);
			assertEquals(_copy.toString(), _Polygon.toString()); hadas();

			_Polygon.rotate(cen3, angleDegrees1);
			rotatelooper(cen3, angleDegrees1);
			assertEquals(_copy.toString(), _Polygon.toString()); hadas();
			_Polygon.rotate(cen3, angleDegrees2);
			rotatelooper(cen3, angleDegrees2);
			assertEquals(_copy.toString(), _Polygon.toString()); hadas();
			_Polygon.rotate(cen3, angleDegrees3);
			rotatelooper(cen3, angleDegrees3);
			assertEquals(_copy.toString(), _Polygon.toString()); hadas();
			_Polygon.rotate(cen3, angleDegrees4);
			rotatelooper(cen3, angleDegrees4);
			assertEquals(_copy.toString(), _Polygon.toString()); hadas();
		}


		/*My Tester format |getPoints|
		 * public Point2D[] getPoints() 

			 just need to check if the point are real

		 *   Possible inputs |p|:
		 */

		@Test
		void getPoints_supertest() {
			Polygon2D copy = new Polygon2D(points);

			assertEquals(_Polygon.toString(),copy.toString());
		}




		//______________________________________________________privet________________________________________________//

		//looper
		public void movelooper(Point2D vec) {
			for (int i = 0; i < points.length; i++) {
				this.points[i] = points[i].add(vec);
			}
		}
		public void rotatelooper(Point2D center, double angleDegrees) {
			for (int i = 0; i < points_copy.length; i++) {
				this.points_copy[i].rotate(center, angleDegrees);
			}

		}
	}