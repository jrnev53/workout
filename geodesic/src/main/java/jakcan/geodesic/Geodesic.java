package jakcan.geodesic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakcan.model3d.Face;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;

import jakcan.model3d.Model3D;
import jakcan.model3d.Named3dPoint;

public class Geodesic {

    public static Model3D getModel(int frequency) {
        Model3D model = RegularIcosahedron.getModel();

        if (frequency == 2) {
            frequency2(model);
        } else if (frequency == 3) {
            frequency3(model);
        } else {
            throw new UnsupportedOperationException("That frequency not supported, yet");
        }

        return model;
    }

    /**
     * Change this model to a frequency 3 geodesic.
     * 
     * @param model A model of the RegularIcosahedron.
     */
    private static void frequency3(Model3D model) {
        // make a copy of faces in the model before
        Map<String, Face> oldFaces = new HashMap<>(model.getFaces());

        Double radius = null ;
        // add new points & faces
        for (Face face : oldFaces.values()) {
            List<Named3dPoint> outline = face.getPoints();
            Named3dPoint p1 = outline.get(0);
            Named3dPoint p2 = outline.get(1);
            Named3dPoint p3 = outline.get(2);

            if (radius == null) {
                radius = p1.getRadius() ;
            }

            // split each side of the original triangle into three pieces
            // qqqq Move midpointBetween to Named3dPoint?
            // qqqq Label these new points as "frequency points", and color differently in the output.
            Named3dPoint q1 = midpointBetween(p1, p2);
            q1.setVectorLength(radius) ;
            Named3dPoint q2 = midpointBetween(p2, p3);
            q2.setVectorLength(radius) ;
            Named3dPoint q3 = midpointBetween(p3, p1);
            q3.setVectorLength(radius) ;
            model.addPoint(q1);
            model.addPoint(q2);
            model.addPoint(q3);

            model.addFace(p1, q1, q3);
            model.addFace(q1, p2, q2);
            model.addFace(q2, q3, q1);
            model.addFace(q3, q2, p3);
        }

        // delete the original faces (and edges), but not the points.
        for (String oldFaceName : oldFaces.keySet()){
            model.removeFace(oldFaceName) ;
        }
    }

    private static int vertexNum = 0;

    /**
     * Change this model to a frequency 2 geodesic.
     * 
     * @param model A model of the RegularIcosahedron.
     */
    private static void frequency2(Model3D model) {
        // make a copy of faces in the model before
        Map<String, Face> oldFaces = new HashMap<>(model.getFaces());

        Double radius = null ;
        // add new points & faces
        for (Face face : oldFaces.values()) {
            List<Named3dPoint> outline = face.getPoints();
            Named3dPoint p1 = outline.get(0);
            Named3dPoint p2 = outline.get(1);
            Named3dPoint p3 = outline.get(2);

            if (radius == null) {
                radius = p1.getRadius() ;
            }

            // qqqq Move midpointBetween to Named3dPoint?
            // qqqq Label these new points as "frequency points", and color differently in the output.
            Named3dPoint q1 = midpointBetween(p1, p2);
            q1.setVectorLength(radius) ;
            Named3dPoint q2 = midpointBetween(p2, p3);
            q2.setVectorLength(radius) ;
            Named3dPoint q3 = midpointBetween(p3, p1);
            q3.setVectorLength(radius) ;
            model.addPoint(q1);
            model.addPoint(q2);
            model.addPoint(q3);

            model.addFace(p1, q1, q3);
            model.addFace(q1, p2, q2);
            model.addFace(q2, q3, q1);
            model.addFace(q3, q2, p3);
        }

        // delete the original faces (and edges), but not the points.
        for (String oldFaceName : oldFaces.keySet()){
            model.removeFace(oldFaceName) ;
        }
    }

    /**
     * This returns the point at the midpoint in a straight line between two points.
     * 
     * @return
     */
    private static Named3dPoint midpointBetween(Named3dPoint a, Named3dPoint b) {
        String vertexName = String.format("F2-%3d", vertexNum++) ;
        Named3dPoint q1 = new Named3dPoint(vertexName, (a.getX() + b.getX())/2, (a.getY() + b.getY())/2, (a.getZ() + b.getZ())/2) ;
        
        return q1 ;
    }

    /** This determines */
    private int scale = 3;

    public String generatePartsList(Model3D model) {
        StringBuilder sb = new StringBuilder();

        Map<BigDecimal, Integer> strutLengths = new TreeMap<>();

        // count the number of each unique strut length
        for (Entry<Named3dPoint, Set<Named3dPoint>> entry : model.getEdges().getEdges().entrySet()) {
            Named3dPoint p1 = entry.getKey();
            for (Named3dPoint p2 : entry.getValue()) {
                // one each edge once
                if (p1.getName().compareTo(p2.getName()) < 0) {
                    double xdiff = Math.pow(p2.getX() - p1.getX(), 2.0);
                    double ydiff = Math.pow(p2.getY() - p1.getY(), 2.0);
                    double zdiff = Math.pow(p2.getZ() - p1.getZ(), 2.0);
                    double strutLength = Math.sqrt(xdiff + ydiff + zdiff);
                    BigDecimal dbsl = new BigDecimal(strutLength);
                    dbsl = dbsl.setScale(scale, RoundingMode.HALF_EVEN);
                    Integer count = strutLengths.get(dbsl);
                    if (count == null) {
                        count = 0;
                    }
                    strutLengths.put(dbsl, ++count);
                }
            }
        }

        for (Entry<BigDecimal, Integer> entry : strutLengths.entrySet()) {
            sb.append("struct length: ").append(entry.getKey()).append(" count: ").append(entry.getValue()).append("\n") ;
        }

        // count the number of vertices
        int numVerts = model.getPoints().size();
        sb.append(String.format("There are %d vertices", numVerts)) ;

        return sb.toString();
    }
}
