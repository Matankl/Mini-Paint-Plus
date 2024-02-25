package Exe.Ex4;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;

/**
 * This class represents a collection of GUI_Shape.
 */
public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;
	GUI_Shapeable _gs; 

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		return _shapes.remove(i);
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		this._shapes.add(i, s);
	}

	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public ShapeCollectionable copy() {
		return new ShapeCollection();
	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		Collections.sort(this._shapes ,comp);
	}

	@Override
	public void removeAll() {
		_shapes.removeAll(_shapes);
	}

	@Override
	public void save(String file) {
		try {
			//Whatever the file path is.
			File filePath = new File(file);
			FileOutputStream is = new FileOutputStream(filePath);
			OutputStreamWriter osw = new OutputStreamWriter(is);    

			Writer w = new BufferedWriter(osw);

			for (int i = 0; i < _shapes.size(); i++) {
				GeoShapeable s = _shapes.get(i).getShape();
				String joined =s.toString();		
				System.out.println(joined);
				w.write(_shapes.get(i).getClass().getSimpleName()+","+_shapes.get(i).getColor().getRGB()+","+_shapes.get(i).isFilled()+","+0+","+s.getClass().getSimpleName()+","+joined+"\n");
			}	

			w.close();
		} catch (Exception e) {
			System.err.println("Problem writing to the file");
		}
		// TODO: handle exception
	}



	@Override
	public void load(String file) {
	GeoShapeable gs = null;
		  _shapes.clear();
		  	
		try {
			Scanner scanner = new Scanner(new File(file));

			while (scanner.hasNextLine()) {
				String currentLine = scanner.nextLine();
				String[] line = currentLine.split(","); 
				
				if(currentLine.contains("Circle2D")) {
					gs = new Circle2D(new Point2D(Double.parseDouble(line[5]),Double.parseDouble(line[6])), Double.parseDouble(line[7]));
				   _gs = new GUIShape(gs,Boolean.parseBoolean(line[2]), new Color(Integer.parseInt(line[1])), Integer.parseInt(line[3]));
				   _shapes.add(_gs);					
				}else {
					String[] polygon = currentLine.split(",");  
					String[] strPoints = Arrays.copyOfRange(polygon, 5, polygon.length);
			        Point2D[] points = new Point2D[strPoints.length/2];  
				      
			        for (int i = 0; i < strPoints.length/2; i++) {
						points[i] = new Point2D(Double.parseDouble(strPoints[2*i]),Double.parseDouble(strPoints[(2*i)+1]));    
				     }
				     
			        gs = new Polygon2D(points);
				    _gs = new GUIShape(gs,currentLine.contains("Segment2D") ? false :Boolean.parseBoolean(line[2]), new Color(Integer.parseInt(line[1])), Integer.parseInt(line[3]));
				   _shapes.add(_gs);
				}		
				
			System.out.println(Arrays.toString(currentLine.split(",")));	 
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("error");
		}
	}

	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;
		LinkedList<Point2D> all_points= new LinkedList<>();

		for (int i = 0; i < _shapes.size(); i++) {									//go over all the shapes
			Point2D[] shape_p = new Point2D[_shapes.get(i).getShape().getPoints().length+2];

			if(_shapes.get(i).getShape() instanceof Circle2D) {//if circle
				shape_p[0] =_shapes.get(i).getShape().getPoints()[0];
				shape_p[1] =_shapes.get(i).getShape().getPoints()[1];
				double rad = shape_p[0].distance(shape_p[1]);
				shape_p[2]= new Point2D(shape_p[0].x(), shape_p[0].y()-rad);
				shape_p[3]= new Point2D(shape_p[0].x()+rad, shape_p[0].y());
				shape_p[0]= new Point2D(shape_p[0].x()-rad, shape_p[0].y());

			}else {
				shape_p =_shapes.get(i).getShape().getPoints();}//if not

			for (int j = 0; j < shape_p.length; j++) {								//extract all the points
				all_points.push(shape_p[j]);
			}
		}
		double Xmax = all_points.get(0).x();
		double Xmin = all_points.get(0).x();
		double Ymax = all_points.get(0).y();
		double Ymin = all_points.get(0).y();
		for (int i = 1; i < all_points.size(); i++) {
			Xmax = Math.max(Xmax, all_points.get(i).x());
			Xmin = Math.min(Xmin, all_points.get(i).x());
			Ymax = Math.max(Ymax, all_points.get(i).y());
			Ymin = Math.min(Ymin, all_points.get(i).y());
		}
		Point2D p1 = new Point2D(Xmax, Ymin);
		Point2D p2 = new Point2D(Xmin, Ymax);
		ans = new Rect2D(p1, p2);
		return ans;
	}
	
	
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}


}
