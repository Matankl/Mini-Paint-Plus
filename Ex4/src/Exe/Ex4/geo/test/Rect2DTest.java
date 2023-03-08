package Exe.Ex4.geo.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;

class Rect2DTest {
         Point2D p1 = new Point2D(4,4);
         Point2D p2 = new Point2D(8,4);
         Point2D p3 = new Point2D(8,2);
         Point2D p4 = new Point2D(4,2);
         Point2D[] points = {p1,p2,p3,p4};
         Rect2D rect = new Rect2D(p1,p3);
         Point2D cen = rect.getCen();
	@Test
	void testRect2D() {
		assertTrue(rect instanceof Rect2D);
	}

	@Test
	void testToString() {
	    assertEquals(rect.toString(),"4.0,4.0,4.0,2.0,8.0,2.0,8.0,4.0");
	}

	@Test
	void testGetCen() {
		assertEquals(rect.getCen(),new Point2D(6,3));
	}

	@Test
	void testGetHalfWidth() {
		assertEquals(rect.getHalfWidth(),2);
	}

	@Test
	void testGetHalfHieght() {
		assertEquals(rect.getHalfHieght(),1);
	}

	@Test
	void testContains() {
		Point2D  outside = new Point2D(5,5); 
		boolean ifinside = rect.contains(outside); // should return false
		
		Point2D  inside = new Point2D(6,3); 
		boolean ifinside2 = rect.contains(inside); // should return true 
		
		assertEquals(ifinside,false);
		assertNotSame(ifinside2,false);
	
	}

	@Test
	void testArea() {
		assertEquals(rect.area(),(2*rect.getHalfHieght())*(rect.getHalfWidth()*2));
	}

	@Test
	void testPerimeter() {
		assertEquals(rect.perimeter(),2*((rect.getHalfHieght()*2) +(rect.getHalfWidth()*2)));
	}

	@Test
	void testMove() {
        
		double addedValue = 4;
         
		Point2D p1 = new Point2D((cen.x()+addedValue )-cen.x(), cen.y() - cen.y());
	    
		rect.move(p1); // the cen should be equal to the new Point
		assertEquals(rect.getCen(),new Point2D(cen.x()+addedValue ,cen.y()));
	}

	@Test
	void testCopy() {
		Rect2D copy = (Rect2D) rect.copy();
		assertEquals(copy.getHalfHieght(),rect.getHalfHieght());
		assertEquals(copy.getHalfWidth(),rect.getHalfWidth());
		assertEquals(copy.getCen(),rect.getCen());
		assertEquals(Arrays.toString(copy.getPoints()),Arrays.toString(rect.getPoints()));
	}

	@Test
	void testScale() {
	
	       /////scaling with  ratio 1.1/////
		   rect.scale(cen, 1.1); 
		   
		   double height = 2*rect.getHalfHieght(); // after --> the height should be 2.2
		   assertEquals(height,2.2,0.01);
		      
		   
		   double width = 2*rect.getHalfWidth(); // after --> the width should be 4.4
		   assertEquals(width,4.4,0.01);
		 
		   
		   /////scaling with ratio 0.9/////
		   
		   rect.scale(cen, 0.9); 
		   
		   double height2 = 2*rect.getHalfHieght(); // after --> the height should be 2.2*0.9
		   assertEquals(height2,2.2*0.9,0.01);
		   
		   
		   double width2 = 2*rect.getHalfWidth(); // after --> the width should be 4.4*0.9
		   assertEquals(width2,4.4*0.9,0.01);
		   
	}

	@Test
	void testRotate() {
		double height  = 2*rect.getHalfHieght();
		double  width  = 2*rect.getHalfWidth();
		
		// test when rotate by 90 degrees --> width = height and  height = width
		rect.rotate(cen, 90);
		
		assertEquals(2*rect.getHalfWidth() == height,true);
		assertEquals(2*rect.getHalfHieght() == width,true);
		
		// test when rotate by 180 degrees --> width = width and height = height
		rect.rotate(cen, 90); // using the prev rotated state 
		
		assertEquals(2*rect.getHalfHieght() == height,true);
		assertEquals(2*rect.getHalfWidth() == width,true);
	}

	@Test 
	void testGetPoints() {
	    
		 Point2D p1 = new Point2D(5,5);
         Point2D p2 = new Point2D(9,5);
         Point2D p3 = new Point2D(9,1);
         Point2D p4 = new Point2D(5,1);
         Point2D[] rectPoints = {p1,p4,p3,p2};
	
		Rect2D rect = new Rect2D(p1,p3);
		
		assertEquals(Arrays.toString(rect.getPoints()), Arrays.toString(rectPoints));
	}

}
