class Rectangle {
	Point A = new Point();
	Point B = new Point();

	Rectangle(int x1, int y1, int x2, int y2) { 
		A.x = x1;
		A.y = y1;
		B.x = x2;
		B.y = y2;
	}
	Rectangle(Point p1, Point p2){
		A.x = p1.x;
		A.y = p1.y;
		B.x = p2.x;
		B.y = p2.y;		
	}

	int area() {
		return ((B.x-A.x)*(B.y-A.y));	
	}
	
	int perimeter() {
		return (2*(B.x-A.x) + 2*(B.y-A.y));
	}

	boolean pointInside(Point p) {
		return (A.x <= p.x && p.x <= B.x && A.y <= p.y && p.y <= B.y);	
	}

	boolean rectangleInside(Rectangle r) {
		return (pointInside(r.A) && pointInside(r.B));	
	}
}
