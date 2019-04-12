public class Point {
    private int x;
    private int y;

    public Point() { x = 0; y = 0; }

    public Point(int _x, int _y) { x = _x; y = _y; }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int _x) {
        x = _x;
    }
    public void setY(int _y) {
        y = _y;
    }

    public String print() { return "(" + x + "," + y + ")"; }
    public boolean equal(Point p) { return x == p.x && y == p.y; }

    public static void main(String args[]) {
        Point test = new Point();
        Point test2 = new Point(10,2);
        System.out.println(test.print());
        System.out.println(test2.print());
        if (test.equals(test2)) System.out.println(test.print());
        else System.out.println(test2.print());
    }
}

