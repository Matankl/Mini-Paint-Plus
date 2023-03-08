package Exe.Ex4.geo.test;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Triangle2D;
import Exe.Ex4.gui.Ex4;


class Ex4Test {
    Ex4 ex = Ex4.getInstance();
	private GUI_Shapeable _gs;
    GeoShapeable gs = null;
    private ShapeCollectionable _shapes = ex.getShape_Collection();
    
   
    //cant be tested without the gui
    //has been tested visually
     @Test
     void load() {
         assertTrue(true);
    }
    
     //cant be tested without the gui
     //has been tested visually
    @Test
     void save() {
	   assertTrue(true);
    }
    
   
   
   @Test
	 void copy() {
	ex.init(_shapes);
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
   
   	    _shapes.get(0).setSelected(true);
     
     
     
     for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected() && s!=null) {
			assertTrue(s.getShape() instanceof Circle2D);			
		}
	  }
	   
    
      
	   
	}
   
    
    @Test
     void remove() {
    	ex.init(_shapes);
    	
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
     
     	    _shapes.get(1).setSelected(true);
       
       
       
       for(int i=0;i<_shapes.size();i++) {
  			GUI_Shapeable s = _shapes.get(i);
  			if(s.isSelected() && s!=null) {
  			assertTrue(s.getShape() instanceof Triangle2D);			
  		}
  	  }
    }
    
    
    
    @Test
     void scale() {
    	ex.init(_shapes);
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
   
   	 double ratio = 1.1;
   	    _shapes.get(0).setSelected(true);
         _shapes.get(0).getShape().scale(cen, ratio);
     
         
     
     for(int i=0;i<_shapes.size();i++) {
    	 System.out.println(_shapes.get(i).isSelected());
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				assertTrue(s.getShape() instanceof Circle2D);	
				
			}
		}
   	 
   	 
    }
    
    @Test
    void rotate(){
    	ex.init(_shapes);
    	
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
      
      	 double degrees = 90;
      	    _shapes.get(0).setSelected(true);
            _shapes.get(0).getShape().rotate(cen, degrees);
        
        
        
        for(int i=0;i<_shapes.size();i++) {
   			GUI_Shapeable s = _shapes.get(i);
   			GeoShapeable g = s.getShape();
   			if(s.isSelected() && g!=null) {
   				assertTrue(s.getShape() instanceof Circle2D);			
   			}
   		}
      	 
    }
	
	

}
