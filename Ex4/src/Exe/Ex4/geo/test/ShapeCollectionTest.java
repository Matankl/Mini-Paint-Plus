package Exe.Ex4.geo.test;

import static org.junit.jupiter.api.Assertions.*;
import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

class ShapeCollectionTest {
	String path = "src/TestFile";
	private ShapeCollectionable _shapes = new ShapeCollection();
	private GUI_Shapeable _gs;
	GeoShapeable gs = null;
	private Comparator<GUI_Shapeable> coperator;
	Point2D cen;

	@Test
	void testShapeCollection() {
		assertTrue(_shapes instanceof ShapeCollection );
	}

	@Test
	void testGet() {
		////Circle///  ---> index 0
		Point2D cen = new Point2D(5,5);
		double rad = 3; 
		gs = new Circle2D(cen, rad);
		_gs = new GUIShape(gs,true, Color.blue,0); 
		_shapes.add(_gs);

		////Triangle/// index 1
		Point2D[] trianglePoints = {new Point2D(5,5),new Point2D(1,1),new Point2D(-5,5)};
		gs = new Triangle2D(trianglePoints);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.add(_gs);

		assertTrue(_shapes.get(0).getShape() instanceof Circle2D);
		assertTrue(_shapes.get(1).getShape() instanceof Triangle2D);
	}

	@Test
	void testSize() {
		////Circle///
		Point2D cen = new Point2D(5,5);
		double rad = 3;
		gs = new Circle2D(cen, rad);
		_gs = new GUIShape(gs,true, Color.blue,0); 
		_shapes.add(_gs);

		////Triangle///
		Point2D[] trianglePoints = {new Point2D(5,5),new Point2D(1,1),new Point2D(-5,5)};
		gs = new Triangle2D(trianglePoints);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.add(_gs);

		//		  the size should be 2


		assertTrue(_shapes.size() == 2);


	}

	@Test
	void testRemoveElementAt() {
		////Circle///  ---> index 0
		Point2D cen = new Point2D(5,5);
		double rad = 3;
		gs = new Circle2D(cen, rad);
		_gs = new GUIShape(gs,true, Color.blue,0); 
		_shapes.add(_gs);

		////Triangle/// index 1
		Point2D[] trianglePoints = {new Point2D(5,5),new Point2D(1,1),new Point2D(-5,5)};
		gs = new Triangle2D(trianglePoints);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.add(_gs);

		// so if we will remove the shape  at index 0 we should get only the triangle

		_shapes.removeElementAt(0); // the circle has been removed

		for (int i = 0; i < _shapes.size(); i++) {
			assertTrue(_shapes.get(i).getShape() instanceof Triangle2D);	
		}



	}

	@Test
	void testAddAt() {
		////Circle///  ---> index 0
		Point2D cen = new Point2D(5,5);
		double rad = 3;
		gs = new Circle2D(cen, rad);
		_gs = new GUIShape(gs,true, Color.blue,0); 
		_shapes.add(_gs);

		////Triangle/// index 1
		Point2D[] trianglePoints = {new Point2D(5,5),new Point2D(1,1),new Point2D(-5,5)};
		gs = new Triangle2D(trianglePoints);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.add(_gs);


		//Adding triangle at index 0 instead the circle 
		Point2D[] trianglePoints2 = {new Point2D(6,6),new Point2D(2,2),new Point2D(5,5)};
		gs = new Triangle2D(trianglePoints2);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.addAt(_gs, 0);

		// check by order according the index 
		assertTrue(_shapes.get(0).getShape() instanceof Triangle2D);

		assertTrue(_shapes.get(1).getShape() instanceof Circle2D);

		assertTrue(_shapes.get(2).getShape() instanceof Triangle2D);


	}

	@Test
	void testAdd() {
		////Circle///  ---> index 0
		Point2D cen = new Point2D(5,5);
		double rad = 3;
		gs = new Circle2D(cen, rad);
		_gs = new GUIShape(gs,true, Color.blue,0); 
		_shapes.add(_gs);

		////Triangle/// index 1
		Point2D[] trianglePoints = {new Point2D(5,5),new Point2D(1,1),new Point2D(-5,5)};
		gs = new Triangle2D(trianglePoints);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.add(_gs);

		//	the size should be 2

		assertTrue(_shapes.size() == 2);

	}

	@Test
	void testCopy() {
		////Circle///
		Point2D cen = new Point2D(5,5);
		double rad = 3;
		gs = new Circle2D(cen, rad);
		_gs = new GUIShape(gs,true, Color.blue,0); 
		_shapes.add(_gs);

		////Triangle///
		Point2D[] trianglePoints = {new Point2D(5,5),new Point2D(1,1),new Point2D(-5,5)};
		gs = new Triangle2D(trianglePoints);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.add(_gs);

		/////////////////////////////////////////////////////////////
		ShapeCollectionable copyShapes =  _shapes.copy();

		for (int i = 0; i < copyShapes.size(); i++) {
			GUI_Shapeable guiShape = copyShapes.get(i); 
			GeoShapeable copyshape = guiShape.getShape(); 

			//// comaparing the GUIShape //////
			assertEquals(guiShape.isFilled(), _shapes.get(i).isFilled());
			assertEquals(guiShape.getColor(), _shapes.get(i).getColor());
			assertEquals(guiShape.toString(), _shapes.get(i).toString());


			//// comaparing the GeoShape //////
			assertEquals(copyshape.area(), _shapes.get(i).getShape().area());
			assertEquals(copyshape.perimeter(), _shapes.get(i).getShape().perimeter());
			assertEquals(copyshape.toString(), _shapes.get(i).getShape().toString());
			assertEquals(copyshape.getPoints(), _shapes.get(i).getShape().getPoints());
		}



	}

	@Test
	void testSort() {
		////Circle///    --- > area is smaller then the triangle 
		Point2D cen = new Point2D(5,5);
		double rad = 3;
		gs = new Circle2D(cen, rad);
		_gs = new GUIShape(gs,true, Color.blue,0); 
		_shapes.add(_gs);

		////Triangle/// --- > area is greater then the circle
		Point2D[] trianglePoints = {new Point2D(5,5),new Point2D(1,1),new Point2D(-5,5)};
		gs = new Triangle2D(trianglePoints);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.add(_gs);


		coperator = new ShapeComp(Ex4_Const.Sort_By_Area);
		_shapes.sort(coperator);

		// check print triangle first should be index 0 
		for (int i = 0; i < _shapes.size(); i++) {
			System.out.println(_shapes.get(i).getShape().getClass().getSimpleName());
		}
		assertTrue(_shapes.get(0).getShape() instanceof Triangle2D);


		coperator = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
		_shapes.sort(coperator);


		// check print circle first 
		for (int i = 0; i < _shapes.size(); i++) {
			System.out.println(_shapes.get(i).getShape().getClass().getSimpleName());
		}

		assertTrue(_shapes.get(0).getShape() instanceof Circle2D); 

	}

	@Test
	void testRemoveAll() {
		_shapes.removeAll();

		//the size should be 0
		assertTrue(_shapes.size() == 0);
	}

	@Test
	void testSave() {

		////Circle///
		Point2D cen = new Point2D(5,5);
		double rad = 3;
		gs = new Circle2D(cen, rad);
		_gs = new GUIShape(gs,true, Color.blue,0); 
		_shapes.add(_gs);

		////Triangle///
		Point2D[] trianglePoints = {new Point2D(5,5),new Point2D(1,1),new Point2D(-5,5)};
		gs = new Triangle2D(trianglePoints);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.add(_gs);

		//			 System.out.println(_shapes.size());  

		_shapes.save(path); 

		File file = new File(path);
		try {
			String st;
			BufferedReader br = new BufferedReader(new FileReader(file));

			for (int i = 0; i < _shapes.size(); i++) {
				if((st = br.readLine()) != null) {
					_gs = _shapes.get(i);

					int Color = _gs.getColor().getRGB();
					boolean isFilled = _gs.isFilled();
					String ShapeclassName = _gs.getShape().getClass().getSimpleName();
					String shapePoints = _gs.getShape().toString();

					assertEquals(st ,"GUIShape," + Color +","+isFilled+",0,"+ShapeclassName+","+shapePoints);
				}


			}

			_shapes.removeAll(); 

			//				  _shapes.removeAll(); // clear the shapes
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO: handle exception


	}

	@Test
	void testLoad() {
		// Checking if the File exist


		File f = new File(path);
		if(f.exists() && !f.isDirectory()) { 
			assertTrue(true);
		}else {
			fail("file Not Found");
		}

		_shapes.load(path);
		assertTrue(_shapes.size()> 0); // according to the save method the numbers of shapes should be the same; 



		//	   System.out.println(_shapes.size()+ " Load");



	}
	
	/*My Tester format |GetBoundingBox|
	 * the danger zone is when you have circle on the edge cause the edges of the circle are not in the array  
	 * 
	 * 
	 */
	

	@Test
	void testGetBoundingBox() {
		////Circle///
		Point2D cen = new Point2D(5,5);
		double rad = 5;
		gs = new Circle2D(cen, rad);
		_gs = new GUIShape(gs,true, Color.blue,0); 
		_shapes.add(_gs);

		////Triangle///
		Point2D[] trianglePoints = {new Point2D(5,5),new Point2D(1,1),new Point2D(-5,5)};
		gs = new Triangle2D(trianglePoints);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.add(_gs);
		
		Rect2D recti = new Rect2D(new Point2D(-5,10),new Point2D(10,0));
		
		assertEquals(_shapes.getBoundingBox().toString(),recti.toString());
		
	}







	@Test
	void testToString() {

		////Circle///
		Point2D cen = new Point2D(5,5);
		double rad = 3;
		gs = new Circle2D(cen, rad);
		_gs = new GUIShape(gs,true, Color.blue,0); 
		_shapes.add(_gs);

		////Triangle///
		Point2D[] trianglePoints = {new Point2D(5,5),new Point2D(1,1),new Point2D(-5,5)};
		gs = new Triangle2D(trianglePoints);
		_gs = new GUIShape(gs,false, Color.red,0); 
		_shapes.add(_gs);

		String ans = "";

		for (int i = 0; i < _shapes.size(); i++) {
			ans += _shapes.get(i).toString(); 
		}

		assertEquals(_shapes.toString(),ans);


	}

}
