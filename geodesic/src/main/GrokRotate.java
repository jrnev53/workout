import java.util.ArrayList;
import java.awt.Point;

public class GrokRotate {
    public static ArrayList<Point> rotatePointsToTop(ArrayList<Point> points, Point topPoint) {
        // Calculate centroid
        double sumX = 0, sumY = 0;
        for (Point point : points) {
            sumX += point.x;
            sumY += point.y;
        }
        double centroidX = sumX / points.size();
        double centroidY = sumY / points.size();

        // Calculate angle
        double dx = topPoint.x - centroidX;
        double dy = topPoint.y - centroidY;
        double theta = Math.atan2(dy, dx) - Math.PI / 2;

        // Rotate points
        ArrayList<Point> rotatedPoints = new ArrayList<>();
        for (Point point : points) {
            double x = point.x - centroidX;
            double y = point.y - centroidY;
            double newX = x * Math.cos(theta) - y * Math.sin(theta) + centroidX;
            double newY = x * Math.sin(theta) + y * Math.cos(theta) + centroidY;
            rotatedPoints.add(new Point((int) Math.round(newX), (int) Math.round(newY)));
        }
        return rotatedPoints;
    }

    // Example usage:
    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(100, 100));
        points.add(new Point(50, 150));

        // Assume the first point should be at the top
        ArrayList<Point> result = rotatePointsToTop(points, points.get(0));
        for (Point p : result) {
            System.out.println(p);
        }
    }
}