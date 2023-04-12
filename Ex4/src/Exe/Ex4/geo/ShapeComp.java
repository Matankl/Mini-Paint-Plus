package Exe.Ex4.geo;

import java.util.Comparator;
import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUI_Shapeable;

/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 */
public class ShapeComp implements Comparator<GUI_Shapeable>{
	//////////add your code below ///////////


	public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
	private int _flag;
	
	public ShapeComp(int flag) {
		_flag = flag;
	}

	
	//if o1 is bigger 1 if o2 is bigger -1 else 0
	//the comper method define by "flag"
	@Override
	public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
		int ans=0;
		if(_flag == Ex4_Const.Sort_By_toString) {
			ans = o1.toString().compareTo(o2.toString());
		}
		if(_flag == Ex4_Const.Sort_By_Anti_toString) {			//the ans of the tostring compare*-1 to flip it
			ans = -1*(o1.toString().compareTo(o2.toString()));
		}
		if(_flag == Ex4_Const.Sort_By_Area) {				//area
			double d1 = o1.getShape().area();
			double d2 = o2.getShape().area();
			if(d1<d2) {ans  = -1;}
			if(d2<d1) {ans = 1;}
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Area) {			//anti area
			double d1 = o1.getShape().area();
			double d2 = o2.getShape().area();
			if(d1<d2) {ans  = 1;}
			if(d2<d1) {ans = -1;}
		}
		if(_flag == Ex4_Const.Sort_By_Perimeter) {			//Parameter
			double d1 = o1.getShape().perimeter();
			double d2 = o2.getShape().perimeter();
			if(d1<d2) {ans  = -1;}
			if(d2<d1) {ans = 1;}
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {		//anti parameter
			double d1 = o1.getShape().perimeter();
			double d2 = o2.getShape().perimeter();
			if(d1<d2) {ans  = 1;}
			if(d2<d1) {ans = -1;}
		}
		if(_flag == Ex4_Const.Sort_By_Tag) {				//tag
			double d1 = o1.getTag();
			double d2 = o2.getTag();
			if(d1<d2) {ans  = -1;}
			if(d2<d1) {ans = 1;}
		}
		if(_flag == Ex4_Const.Sort_By_Anti_Tag) {			//anti tag
			double d1 = o1.getTag();
			double d2 = o2.getTag();
			if(d1<d2) {ans  = 1;}
			if(d2<d1) {ans = -1;}
		}
		return ans;
	}

	
}
