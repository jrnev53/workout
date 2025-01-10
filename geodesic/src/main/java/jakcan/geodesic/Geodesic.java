package jakcan.geodesic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakcan.model3d.Face;
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

    private static void frequency3(Model3D model) {
        throw new UnsupportedOperationException("Unimplemented method 'frequency3'");
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

        // add new points & faces
        for (Face face : oldFaces.values()) {
            List<Named3dPoint> outline = face.getPoints();
            Named3dPoint p1 = outline.get(0);
            Named3dPoint p2 = outline.get(1);
            Named3dPoint p3 = outline.get(2);

            // FIXME Move midpointBetween to Named3dPoint?
            // FIXME The function midpointBetween can't be right, want point on the sphere!!
            // FIXME Label these new points as "frequency points", and color differently in the output.
            Named3dPoint q1 = midpointBetween(p1, p2);
            Named3dPoint q2 = midpointBetween(p2, p3);
            Named3dPoint q3 = midpointBetween(p3, p1);
            model.addPoint(q1);
            model.addPoint(q2);
            model.addPoint(q3);

            model.addFace(p1, q1, q3);
            model.addFace(q1, p2, q3);
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

}
