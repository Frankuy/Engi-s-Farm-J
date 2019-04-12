public class Point<T extends Number> implements Comparable<Point<T>> {
    private T x;
    private T y;

    public Point(T _x, T _y) { x = _x; y = _y; }

    public T getX(){
        return x;
    }
    public T getY(){
        return y;
    }
    public void setX(T _x) {
        x = _x;
    }
    public void setY(T _y) {
        y = _y;
    }

    @Override
    public int compareTo(Point<T> o) {
        if (o.x.equals(x) && o.y.equals(y)) return 0;
        else return -1;
    }

    public String print() { return "(" + x + "," + y + ")"; }

    public static void main(String args[]) {
        Point test = new Point<>(2,2);
        Point test2 = new Point<>(10,2);
        System.out.println(test.print());
        System.out.println(test2.print());
        if (test.equals(test2)) System.out.println(test.print());
        else System.out.println(test2.print());
    }
}

