package com.monitrack.shared;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.io.Serializable;
import java.util.List;

public class Polygone extends Polygon{
	
		private int[] x;
	    private int[] y;
	    private int tab;
	    
	public Polygone(List<Point> listPoint){
	    // Remplissage de ta liste de sommets
	    for (Point point : listPoint){
	        this.addPoint(point.x, point.y);
	    }
	}

}
