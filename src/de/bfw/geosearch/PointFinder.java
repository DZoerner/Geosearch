package de.bfw.geosearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PointFinder {
	private static long cellFactor;
	
	static {
		cellFactor = 1 << Point.cellSize;
	}
	
	private Map<String, Set<Point>> pointGroupMap = new HashMap<>();
	
	public boolean add(Point point) {
		Set<Point> pointGroup = pointGroupMap.get(point.getCode());
		if(pointGroup == null) {
			pointGroup = new HashSet<>();
			pointGroupMap.put(point.getCode(), pointGroup);
		}
		return pointGroup.add(point);
	}
	
	public boolean remove(Point point) {
		Set<Point> pointGroup = pointGroupMap.get(point.getCode());
		if(pointGroup == null) {
			return false;
		}
		boolean ret = pointGroup.remove(point);
		
		if(pointGroup.isEmpty()) {
			pointGroupMap.remove(point.getCode());
		}
		
		return ret;
	}
	
	public void move(Point point, long x, long y) {
		remove(point);
		
		point.setX(x);
		point.setY(y);
		
		add(point); 
	}
	
	public Set<Point> findPoints(long x1, long y1, long x2, long y2){
		Set<Point> ret = new HashSet<>();
		
 		long dx = (x2 + cellFactor - 1 - x1) / cellFactor;
		long dy = (y2 + cellFactor - 1 - y1) / cellFactor;
		
		for(long ix = 0; ix <= dx; ix++) {
			for(long iy = 0; iy <= dy; iy++) {
				Point criteria = new Point(x1 + ix*cellFactor, y1 + iy*cellFactor);
				Set<Point> found = pointGroupMap.get(criteria.getCode());
				if(found != null) {
					for(Point p : found) {
						if(p.isInside(x1, y1, x2, y2)) {
							ret.add(p);
						}
					}
				}
			}
		}
		
		return ret;
	}
}
