package de.bfw.geosearch;

import java.util.Objects;

public class Point {
	static final int areaSize = 16;
	static final int cellSize = 6;
	private static long areaMask;
	
	static {
		areaMask = 0;
		long mask = 1;
		for(int i = 0; i <= areaSize - cellSize; i++) {
			areaMask |= mask;
			mask = mask << 1;
		}
	}
	
	private long x;
	private long y;
	private String code= null;
	
	public Point(long x, long y) {
		super();
		this.x = x;
		this.y = y;
	}

	public long getX() {
		return x;
	}
	
	public void setX(long x) {
		if(this.x != x) {
			code = null;
		}
		
		this.x = x;
	}
	
	public long getY() {
		return y;
	}
	
	public void setY(long y) {
		if(this.y != y) {
			code = null;
		}

		this.y = y;
	}
	
	public boolean isInside(long x1, long y1, long x2, long y2) {
		return
				x >= x1 && x <=x2 &&
				y >= y1 && y <=y2
				;
	}
	
	private static String getCode(long val) {
		val = val >> cellSize;
		val = val & areaMask;
		
		return Long.toHexString(val);
	}
	
	public String getCode() {
		if(code == null) {
			code = getCode(x) + "*" + getCode(y);
		}		
		
		return code;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
}
