import edu.princeton.cs.algs4.Point2D;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class ConvexHullTest {

    @Test
    void testNull() {
        // compute convex hull
        ConvexHullBuilder convexBuilder = new ConvexHullBuilder(null);
        Stack<Point2D> hullPts = (Stack<Point2D>) convexBuilder.hull();
        assert(hullPts.size() == 0);
    }

    String stackToString(Stack<Point2D> s) {
        String output = "";
        while (s.size() > 0) {
            output += s.pop().toString() + " ";
        }
        return output;
    }

    @Test
    void test1Point() {

        ArrayList<Point2D> pts = new ArrayList<>();
        pts.add(new Point2D(0,0));
        // compute convex hull
        ConvexHullBuilder convexBuilder = new ConvexHullBuilder(pts);
        Stack<Point2D> hullPts = (Stack<Point2D>) convexBuilder.hull();
        String actual = stackToString(hullPts);
        String expected = "(0.0, 0.0) ";
        assert(actual.equals(expected));
    }

    @Test
    void test2Points() {

        ArrayList<Point2D> pts = new ArrayList<>();
        pts.add(new Point2D(0,0));
        pts.add(new Point2D(0,5));
        // compute convex hull
        ConvexHullBuilder convexBuilder = new ConvexHullBuilder(pts);
        Stack<Point2D> hullPts = (Stack<Point2D>) convexBuilder.hull();
        String actual = stackToString(hullPts);
        String expected = "(0.0, 5.0) (0.0, 0.0) ";
        assert(actual.equals(expected));
    }


    @Test
    void test3Points() {

        ArrayList<Point2D> pts = new ArrayList<>();
        pts.add(new Point2D(0,0));
        pts.add(new Point2D(0,5));
        pts.add(new Point2D(2.5,2.5));
        // compute convex hull
        ConvexHullBuilder convexBuilder = new ConvexHullBuilder(pts);
        Stack<Point2D> hullPts = (Stack<Point2D>) convexBuilder.hull();
        String actual = stackToString(hullPts);
        String expected = "(0.0, 5.0) (2.5, 2.5) (0.0, 0.0) ";
        assert(actual.equals(expected));
    }

    @Test
    void testBox() {

        ArrayList<Point2D> pts = new ArrayList<>();
        pts.add(new Point2D(0,0));
        pts.add(new Point2D(0,5));
        pts.add(new Point2D(5,0));
        pts.add(new Point2D(2.5,2.5));
        pts.add(new Point2D(5,5));
        // compute convex hull
        ConvexHullBuilder convexBuilder = new ConvexHullBuilder(pts);
        Stack<Point2D> hullPts = (Stack<Point2D>) convexBuilder.hull();
        String actual = stackToString(hullPts);
        String expected = "(0.0, 5.0) (5.0, 5.0) (5.0, 0.0) (0.0, 0.0) ";
        assert(actual.equals(expected));
    }

    @Test
    void testBoxMultipleInside() {

        ArrayList<Point2D> pts = new ArrayList<>();
        pts.add(new Point2D(0,0));
        pts.add(new Point2D(0,5));
        pts.add(new Point2D(5,0));
        pts.add(new Point2D(5,5));
        //These should not be included in hull
        pts.add(new Point2D(2.5,2.5));
        pts.add(new Point2D(1.5,1.5));
        pts.add(new Point2D(3.5,3.5));
        pts.add(new Point2D(4.5,4.5));

        // compute convex hull
        ConvexHullBuilder convexBuilder = new ConvexHullBuilder(pts);
        Stack<Point2D> hullPts = (Stack<Point2D>) convexBuilder.hull();
        String actual = stackToString(hullPts);
        String expected = "(0.0, 5.0) (5.0, 5.0) (5.0, 0.0) (0.0, 0.0) ";
        assert(actual.equals(expected));
    }

}

