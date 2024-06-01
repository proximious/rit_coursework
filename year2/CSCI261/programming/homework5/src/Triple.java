// Use this to package up your results, that is two points and the distance
// between them. A triple should be the return value of your 
public class Triple {

    public Point p1;
    public Point p2;
    public double dist;
    
    public Triple (Point p1, Point p2, double dist) {
        this.p1 = p1;
	this.p2 = p2;
	this.dist = dist;
    }

    public String toString() {
	return "p1 = " + p1 + " p2 = " + p2 + " dist = " + dist;
    }
}
