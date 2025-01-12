package jakcan.geodesic;

import jakcan.model3d.Model3D;

public class RegularIcosahedron {
    // qqqq move this to a better place
    public static final Double golden_ratio = (1 + Math.sqrt(5.0)) / 2.0;

    // private static Map<String, Named3dPoint> ri_points = null ;
    // private static Edges ri_edges = null ;
    private static Model3D model = null;

    public static Model3D getModel() {
        if (model == null) {
            model = new Model3D();
            model.addPoint("A", 0.0, 1.0, golden_ratio);
            model.addPoint("B", 0.0, -1.0, golden_ratio);
            model.addPoint("C", 0.0, 1.0, -golden_ratio);
            model.addPoint("D", 0.0, -1.0, -golden_ratio);

            model.addPoint("E", 1.0, golden_ratio, 0.0);
            model.addPoint("F", -1.0, golden_ratio, 0.0);
            model.addPoint("G", 1.0, -golden_ratio, 0.0);
            model.addPoint("H", -1.0, -golden_ratio, 0.0);

            model.addPoint("I", golden_ratio, 0.0, 1.0);
            model.addPoint("J", golden_ratio, 0.0, -1.0);
            model.addPoint("K", -golden_ratio, 0.0, 1.0);
            model.addPoint("L", -golden_ratio, 0.0, -1.0);

            model.setTitle("Regular Icosahedron");

            model.addFace("A", "E", "F") ;
            model.addFace("A", "F", "K") ;
            model.addFace("A", "K", "B") ;
            model.addFace("A", "B", "I") ;
            model.addFace("A", "I", "E") ;

            model.addFace("E", "C", "F") ;
            model.addFace("F", "C", "L") ;
            model.addFace("F", "C", "L") ;
            model.addFace("F", "L", "K") ;
            model.addFace("K", "L", "H") ;
            model.addFace("B", "H", "G") ;
            model.addFace("B", "G", "I") ;
            model.addFace("I", "G", "J") ;
            model.addFace("I", "J", "E") ;
            model.addFace("E", "J", "C") ;
            
            model.addFace("C", "D", "L") ;
            model.addFace("L", "D", "H") ;
            model.addFace("H", "D", "G") ;
            model.addFace("G", "D", "J") ;
            model.addFace("J", "D", "C") ;

            model.addFace("K", "H", "B") ;
        }

        return model;
    }

}
