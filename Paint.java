public class Paint {

    private final double x;
    private final double y;	
    private final double width;
    private final double height;

    public Paint(double x0, double y0, double w, double h) {
        x = x0;
        y = y0;
        width = w;
        height = h;
    }
    public void show() {
        StdDraw.filledRectangle(x, y, width / 2, height / 2);
    }
}
