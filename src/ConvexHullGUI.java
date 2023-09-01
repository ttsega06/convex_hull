

import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class ConvexHullGUI {

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 10000);
        StdDraw.setYscale(0, 10000);
        StdDraw.enableDoubleBuffering();

        ArrayList<Point2D> pointList = new ArrayList<Point2D>();

        //listens mouse clicks, add points to the list
        //and draw convex hull

        //if I hold the mouse down it keeps plotting points, use boolean
        //to only draw one point at a time
        boolean isMousePressed = false;

        while (true) {
            if (StdDraw.mousePressed() && !isMousePressed) {
                //set mouse pressed so it doesn't fire this off again
                isMousePressed = true;
                //user clicks, add point to the list
                int x = (int) (Math.round(StdDraw.mouseX()));
                int y = (int) (Math.round(StdDraw.mouseY()));
                pointList.add(new Point2D(x, y));

                // compute convex hull
                ConvexHullBuilder convexBuilder = new ConvexHullBuilder(pointList);

                StdDraw.clear();

                drawConvexHull(pointList, convexBuilder.hull());

            }

            //account for multiple points when mouse held down
            //only allow another click to happen after the mouse has been released
            if (isMousePressed && !StdDraw.mousePressed()) {
                isMousePressed = false;
            }

            StdDraw.show();
            StdDraw.pause(20);
        }

    }

    //plot convex hull
    private static void drawConvexHull(ArrayList<Point2D> pointList,
                                       Iterable<Point2D> hull) {
        drawPoints(pointList);  //black points

        drawHullPoints(hull);	//red points

        drawHullLines(hull);	//blue lines
    }

    // plot black points
    private static void drawPoints(ArrayList<Point2D> pointList) {
        int n = pointList.size(); //number of points

        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(StdDraw.BLACK);

        for (int i = 0; i < n; i++)
            pointList.get(i).draw();
    }

    // plot red hull points
    private static void drawHullPoints(Iterable<Point2D> hull) {
        StdDraw.setPenColor(StdDraw.RED);
        for (Point2D p : hull)
            p.draw();
    }

    // plot blue hull line segments
    private static void drawHullLines(Iterable<Point2D> hull) {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        Point2D prev = null;
        for (Point2D p : hull) {
            if (prev != null)
                prev.drawTo(p);
            prev = p;
        }
        // connect first and last points
        for (Point2D p : hull) {
            prev.drawTo(p);
            break;
        }
    }

}
