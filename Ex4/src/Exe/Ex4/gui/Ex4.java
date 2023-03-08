package Exe.Ex4.gui;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

//BY: Matan Ziv
//ID : 208235769

/*Mega TODO list
 * 
 * 
 * 
 * 
 */











/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */

//Data + init funcs
public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1;
	private  ArrayList<Point2D> poly_points = new ArrayList<Point2D>();
	private Comparator<GUI_Shapeable> coperator;

	private  static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}
	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}



	//	draw shapes methtods
	//run over all shapes and activats the drawer
	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable sh = _shapes.get(i);
			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}

	/*
	 * it draw all the 
	 */
	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());//get color
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}//dkdm
		GeoShapeable gs = g.getShape();//get shape
		boolean isFill = g.isFilled();

		//Circle
		if(gs instanceof Circle2D) {
			Circle2D c = (Circle2D)gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		//Rect
		if(gs instanceof Rect2D) {
			Rect2D R = (Rect2D)gs;
			double[] x = new double[R.getPoints().length];
			double[] y = new double[R.getPoints().length];

			for (int i = 0; i < R.getPoints().length; i++) {
				x[i]= R.getPoints()[i].x();
				y[i]= R.getPoints()[i].y();
			}
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x,y);
			}
			else {
				StdDraw_Ex4.polygon(x,y);
			}
		}
		//Segment
		if(gs instanceof Segment2D) {
			Segment2D S = (Segment2D)gs;
			StdDraw_Ex4.line(S.getPoints()[0].x(), S.getPoints()[0].y(), S.getPoints()[1].x(), S.getPoints()[1].y());
		}
		//Triangle
		if(gs instanceof Triangle2D) {
			Triangle2D T = (Triangle2D)gs;
			double[] x = new double[T.getPoints().length];
			double[] y = new double[T.getPoints().length];

			for (int i = 0; i < T.getPoints().length; i++) {
				x[i]= T.getPoints()[i].x();
				y[i]= T.getPoints()[i].y();
			}
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x,y);
			}
			else {
				StdDraw_Ex4.polygon(x,y);
			}
		}
		//polygon
		if(gs instanceof Polygon2D) {
			Polygon2D T = (Polygon2D)gs;
			double[] x = new double[T.getPoints().length];
			double[] y = new double[T.getPoints().length];

			for (int i = 0; i < T.getPoints().length; i++) {
				x[i]= T.getPoints()[i].x();
				y[i]= T.getPoints()[i].y();
			}
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x,y);
			}
			else {
				StdDraw_Ex4.polygon(x,y);
			}
		}
	}




	//if selected it colors it
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	//if selectd it fills it
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}


	//	mouse methods

	public void actionPerformed(String p) {
		_mode = p;
		//Appearance 
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		//remove
		if(p.equals("Clear")) {_shapes.removeAll();}
		if(p.equals("Remove")) {remove();}
		if(p.equals("Info")) {System.out.println( getInfo());}
		//select 
		if(_mode.equals("All")) {select(null);}
		if(_mode.equals("None")) {select(null);}
		if(_mode.equals("Anti")) {select(null);}
		//
		if(_mode.equals("Load")) {load();}
		if(_mode.equals("Save")) {save();}

		//sorts
		if(_mode.equals("ByArea")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Area);
			_shapes.sort(coperator);
		} 
		if(_mode.equals("ByAntiArea")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
			_shapes.sort(coperator);
		} 
		if(_mode.equals("ByPerimeter")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
			_shapes.sort(coperator);
		}
		if(_mode.equals("ByAntiPerimeter")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
			_shapes.sort(coperator);
		} 
		if(_mode.equals("ByToString")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_toString);
			_shapes.sort(coperator);
		} 
		if(_mode.equals("ByAntiToString")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
			_shapes.sort(coperator); 
		}
		if(_mode.equals("ByTag")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Tag);
			_shapes.sort(coperator);
		}
		if(_mode.equals("ByAntiTag")) {
			coperator = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
			_shapes.sort(coperator);
		}

		drawShapes();

	}


	public void mouseClicked(Point2D p) {
		System.out.println("Mode: "+_mode+"  "+p);

		if(_mode.equals("Circle")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(_shapes.size()-1);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if(_mode.equals("Rect")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs.setTag(_shapes.size()-1);
				_gs = null;
				_p1 = null;

			}
		}

		if(_mode.equals("Segment")) {
			if(_gs==null) {
				_p1 = new Point2D(p);

			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs.setTag(_shapes.size()-1);
				_gs = null;
				_p1 = null;
			}
		}

		if(_mode.equals("Triangle")) {
			if(_gs==null) { 
				_p1 = new Point2D(p);
				if(poly_points.size() == 0){
					poly_points.add(_p1); 
					poly_points.add(p);
				}
			}else{	
				if(poly_points.size() == 3 && poly_points.get(1).distance(poly_points.get(2)) != 0) {
					_gs.setColor(_color);
					_gs.setFilled(_fill);
					_shapes.add(_gs);  
					_gs.setTag(_shapes.size()-1);
					_gs = null;	
					_p1 = null;
					poly_points.clear();
				}
				if(poly_points.size() == 2 && poly_points.get(0).distance(p) != 0 ) {
					poly_points.add(p);
				}

			}
		}


		if(_mode.equals("Polygon")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
				poly_points.add(_p1);
				poly_points.add(p);
			}
			else {
				poly_points.add(p);
			}
		}

		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}

		if(_mode.equals("Copy")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				copy();
				_p1 = null;
			}
		}

		if(_mode.equals("Rotate")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				rotate(p);
				_p1 = null;
			}
		}
		if(_mode.equals("Point")) {
			select(p);
		}
		if(_mode.equals("Scale_90%")) {
			scale(p);
		}
		if(_mode.equals("Scale_110%")) {
			scale(p);
		}


		drawShapes();
	}




	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");
		GeoShapeable gs = null;
		if(!_mode.equals("Polygon")) {
			_gs = new GUIShape(gs,false, Color.pink, 0);
			_p1 = null;
		}
		else {
			poly_points.remove(poly_points.size()-1);
			Point2D[] pos = poly_points.toArray(new Point2D[poly_points.size()]);
			gs = new Polygon2D(pos);
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			_gs = new GUIShape(gs,_gs.isFilled(),_gs.getColor(),0);
			_shapes.add(_gs);
			_gs.setTag(_shapes.size()-1);
			poly_points.clear();
		}

		drawShapes();
		_p1 = null;
		_gs = null;
	}



	//Implement all geo here with the addition point of the current mouse location
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			//			System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1,y1);

			if(_mode.equals("Circle")) {		//Circle
				double r = _p1.distance(p);
				gs = new Circle2D(_p1,r);
			}
			if(_mode.equals("Rect")) {			//Rect
				gs = new Rect2D(_p1,p);
			}
			if(_mode.equals("Segment")) { 		//Segment
				gs = new Segment2D(_p1,p);
			}
			if(_mode.equals("Triangle")) { 		//Triangle
				poly_points.set(1, p);
				Point2D[] points = poly_points.toArray(new Point2D[poly_points.size()]);
				gs = new Triangle2D(points);
			}
			if(_mode.equals("Polygon")) { 		//Polygon
				poly_points.set(poly_points.size()-1, p);
				Point2D[] points = poly_points.toArray(new Point2D[poly_points.size()]);
				gs = new Polygon2D(points);
			}

			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}

	//this method check if p is null if not it changes the "is selected" status 
	//if null do what the mode says
	public void select(Point2D p) {
		if(p != null) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(g!=null && g.contains(p)) {
					s.setSelected(!s.isSelected());
				}
			}
		}else {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if(g!=null&& _mode.equals("All")==true) {
					s.setSelected(true);
				}
				else if(g!=null&& _mode.equals("Anti")==true) {
					s.setSelected(!s.isSelected());
				}
				else if(g!=null&& _mode.equals("None")==true) {
					s.setSelected(false);
				}
			}
		}
	}

	//this go over all the shapes and apply rotate on them
	public void rotate(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				double difx =p.x()-_p1.x();
				double dify =p.y()-_p1.y();
				double deg =Math.toDegrees(Math.atan2(dify,difx)); // degree angle
				System.out.println("deg:"+ deg);
				g.rotate(_p1, deg);
			}
		}
	}

	//this go over all the shapes and apply copy on them
	private void copy() {
		for(int i=0; i<_shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				GUI_Shapeable copy = s.copy();
				copy.getShape().move(_p1);
				_shapes.add(copy);
			}
		}
	}
	// this gives a string from file chooser to the load method in shape collection  
	private void load() {
		try {
			FileDialog fileChooser = new FileDialog(new Frame(),"Load from Text file", FileDialog.LOAD);
			fileChooser.setVisible(true);
			File file = fileChooser.getFiles()[0]; 
			_shapes.load(file.getCanonicalPath());
		} catch (Exception e) {
		}
	}

	//this gives the save method in shape collection a location to save the file 
	private void save() {
		try {
			FileDialog fileChooser = new FileDialog(new Frame(),"Save to Text file", FileDialog.SAVE);
			fileChooser.setVisible(true);
			System.out.println(fileChooser.getDirectory().toString()+fileChooser.getFile());
			_shapes.save(fileChooser.getDirectory().toString()+fileChooser.getFile());
		} catch (Exception e) {
		}
	}

	//this go over all the shapes and apply move on them
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}
	}

	//this go over all the shapes and apply remove on them
	private void remove() {
		for(int i=_shapes.size()-1 ;i>=0;i--) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				_shapes.removeElementAt(i);
				System.out.println(i);
			}
		}
	}

	//this go over all the shapes and apply scale on them
	private void scale(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				if(_mode.equals("Scale_90%")) {
					g.scale(p, 0.9);}
				else {g.scale(p, 1.1);}
			}
		}
	}

	@Override
	public ShapeCollectionable getShape_Collection() {
		return this._shapes;
	}

	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }

	@Override
	public String getInfo() {
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
