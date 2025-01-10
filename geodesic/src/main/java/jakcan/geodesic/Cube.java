package jakcan.geodesic;

import jakcan.model3d.Model3D;

public class Cube {

    public static Model3D createModel(Double sideLength) {
        Model3D model = new Model3D() ;
        model.addPoint("A", sideLength, sideLength, sideLength);
        model.addPoint("B", sideLength, -sideLength, sideLength);
        model.addPoint("C", -sideLength, -sideLength, sideLength);
        model.addPoint("D", -sideLength, sideLength, sideLength);
        model.addPoint("E", sideLength, sideLength, -sideLength);
        model.addPoint("F", sideLength, -sideLength, -sideLength);
        model.addPoint("G", -sideLength, -sideLength, -sideLength);
        model.addPoint("H", -sideLength, sideLength, -sideLength);
        model.addEdge("C","D") ;
        model.addEdge("A","D") ;
        model.addEdge("D","H") ;
        model.addEdge("C","B") ;

        model.addEdge("C","G") ;
        model.addEdge("C","D") ;
        model.addEdge("A","B") ;
        model.addEdge("B","F") ;

        model.addEdge("A","E") ;
        model.addEdge("F","E") ;
        model.addEdge("H","E") ;
        model.addEdge("H","G") ;
        model.addEdge("F","G") ;

        model.setTitle("Cube") ;

        return model ;
    }

}
