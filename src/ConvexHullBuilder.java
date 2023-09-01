import java.util.ArrayList;
import java.util.Arrays;


import java.util.Stack;

import edu.princeton.cs.algs4.Point2D;

public class ConvexHullBuilder {

    private Stack<Point2D> hullPts;

    public ConvexHullBuilder(ArrayList<Point2D> pointList) {

        hullPts = new Stack<>();
        if (pointList == null) {
            return;
        }

        if (pointList.size() < 3) {
            hullPts.addAll(pointList);
            return;
        }

        Point2D[] sorted = new Point2D[pointList.size()];
        for (int i = 0; i < pointList.size(); i++) {
            sorted[i] = pointList.get(i);
        }
        // sort based on y value
        Arrays.sort(sorted);

        // sort after the first point based on polar coordinate
        Arrays.sort(sorted, 1, sorted.length, sorted[0].polarOrder());

        // put first point on stack
        hullPts.push(sorted[0]);
        int nonEqual = 1;
        while (sorted[0].equals(sorted[nonEqual]) && nonEqual < sorted.length) {
            nonEqual++;
        }
        // if all points equal stop
        if (nonEqual == sorted.length) {
            return;
        }
        //put the next non equal point ont he stack
        hullPts.push(sorted[nonEqual]);
        for (int i = nonEqual + 1; i < sorted.length; i++) {
            //get the 3 points to test
            Point2D curPoint = sorted[i];
            Point2D firstPt = hullPts.pop();
            Point2D secondPt = hullPts.peek();
            while (Point2D.ccw(secondPt, firstPt, curPoint) <= 0 && hullPts.size() > 1) {
                //update points
                firstPt = hullPts.pop();
                secondPt = hullPts.peek();
            }
            //add onto the stack so you can continue
            hullPts.push(firstPt);
            hullPts.push(curPoint);
        }

    }

    public Iterable<Point2D> hull() {

        return hullPts;
    }



}

