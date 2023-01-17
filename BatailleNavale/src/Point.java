
public class Point {
	
	int x;
	int y;

	public Point(int nx, int ny){
			x = nx;
			y = ny;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public String toString() {
		return "x:"+this.x+" y:"+this.y;
	}
}
