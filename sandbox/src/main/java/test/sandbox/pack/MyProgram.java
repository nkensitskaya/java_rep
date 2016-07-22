package test.sandbox.pack;

public class MyProgram {

	public static void main(String[] args){
		System.out.println("Hello, world!");

		Point A = new Point(0,0);
		Point B = new Point(3,3);
		System.out.println("Distance between A and B = " + distance(A,B));
		System.out.println("Distance between A and B = " + A.distance(B));

	}


	public static double distance(Point p1,Point p2){

		return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
	}
}