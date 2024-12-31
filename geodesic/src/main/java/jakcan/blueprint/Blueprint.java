package jakcan.blueprint;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Blueprint {
    private Map<String, Named3dPoint> points;
    private Edges edges;
    private String title ;
    public static Named3dPoint ORIGIN = new Named3dPoint("ORIGIN", 0.0, 0.0, 0.0);

    public Blueprint() {
        points = new HashMap<>();
        edges = new Edges();
        title = "Blueprint" ;
    }

    public Blueprint(Collection<Named3dPoint> points, Edges edges) {
        this() ;

        for (Named3dPoint pt : points) {
            this.points.put(pt.name, pt);
        }

        this.edges.putAll(edges);
    }

    public Blueprint(Collection<Named3dPoint> points, Edges edges, String title) {
        this(points, edges) ;
        this.title = title ;
    }

    public Map<String, Named3dPoint> getPoints() {
        return points;
    }

    public Edges getEdges() {
        return edges;
    }

    public JSONObject writeToJSON() {
        // Create a JSON object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title) ;

        // write out points
        JSONArray jsonPts = new JSONArray();
        for (Named3dPoint pt : this.points.values()) {
            JSONObject json_pt = new JSONObject();
            json_pt.put("name", pt.name);
            json_pt.put("x", pt.x);
            json_pt.put("y", pt.y);
            json_pt.put("z", pt.z);
            jsonPts.put(json_pt);
        }
        jsonObject.put("points", jsonPts);

        // edges
        JSONArray edgesJSON = edges.writeToJSON();
        jsonObject.put("edges", edgesJSON);

        return jsonObject;
    }

    public void addPoint(String name, Double x, Double y, Double z) {
        addPoint(new Named3dPoint(name, x, y, z));
    }

    private void addPoint(Named3dPoint pt) {
        points.put(pt.name, pt);
    }

    public void rotateYZ(Double moveAngle) {
        // Apply this angle change to all points
        Double cosAngle = Math.cos(moveAngle) ;
        Double sinAngle = Math.sin(moveAngle) ;
        for (Named3dPoint pt : points.values()) {
            Double oldx = pt.y ;
            Double oldy = pt.z ;
            pt.y = (oldx * cosAngle) - (oldy * sinAngle);
            pt.z = (oldx * sinAngle) + (oldy * cosAngle);
        }
    }

    public void rotateY(Double moveAngle) {
        // Apply this angle change to all points
        Double cosAngle = Math.cos(moveAngle) ;
        Double sinAngle = Math.sin(moveAngle) ;
        for (Named3dPoint pt : points.values()) {
            Double oldx = pt.x ;
            Double oldz = pt.z ;
            pt.x = (oldx * cosAngle) + (oldz * sinAngle);
            pt.z = (-oldx * sinAngle) + (oldz * cosAngle);
        }
    }

    /**
     * Rotate the points so that the named point is at x=0,y=0 and above all other
     * points.
     * 
     * @param name The name of the point to be top.
     */
    public void rotateToTop(String name) {
        Named3dPoint topPt = getPoints().get("A");
        // System.out.println("Top Point: " + topPt) ;

        Double offAngle = getAngleYZ(topPt, Blueprint.ORIGIN);
        // System.out.println("YZ Off Angle is " + Math.toDegrees(offAngle));

        // To move the point to the top, rotate so that the angle between it an the
        // origin is 90 degrees, aka straight up.
        // Double moveAngle = Math.toRadians(90.0) - offAngle;
        Double moveAngle = offAngle;
        // System.out.println("Move angle is " + Math.toDegrees(moveAngle));

        // Apply this angle change to all points
        rotateYZ(moveAngle) ;
        // System.out.println("Top Point: " + topPt) ;

        // now do the XZ plane
        offAngle = getAngleXZ(topPt, Blueprint.ORIGIN) ;
        // moveAngle = Math.toRadians(90.0) - offAngle;
        moveAngle = - offAngle;
        // moveAngle = offAngle;
        // System.out.println("XZ Off Angle is " + offAngle + ", in degrees that's " + Math.toDegrees(offAngle));
        rotateY(moveAngle) ;
        // System.out.println("Top Point: " + topPt) ;
    }

    /**
     * Return the angle between two points in the XY plane
     * 
     * @param p1
     * @param p2
     * @return
     */
    Double getAngleXY(Named3dPoint p1, Named3dPoint p2) {
        Double dx = p1.x - p2.x;
        Double dy = p1.y - p2.y;
        return Math.atan2(dy, dx);
    }

    /**
     * Return the angle between two points in the XY plane
     */
    public Double getAngleYZ(Named3dPoint p1, Named3dPoint p2) {
        Double dz = p1.z - p2.z;
        Double dy = p1.y - p2.y;
        return Math.atan2(dy, dz);
    }

    public Double getAngleXZ(Named3dPoint p1, Named3dPoint p2) {
        Double dz = p1.z - p2.z;
        Double dx = p1.x - p2.x;
        return Math.atan2(dx, dz);
    }

    public void addEdge(String p1name, String p2name) {
        Named3dPoint p1 = points.get(p1name);
        Named3dPoint p2 = points.get(p2name);
        edges.addEdge(p1, p2);
    }

    public void moveX(Double dx) {
        for (Named3dPoint pt : points.values()) {
            pt.x = pt.x + dx ;
        }
    }

    public void moveY(Double dy) {
        for (Named3dPoint pt : points.values()) {
            pt.y = pt.y + dy ;
        }
    }
}