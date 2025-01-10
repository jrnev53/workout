package jakcan.geodesic;

import jakcan.model3d.Model3D;

public class Box {

    public static Model3D createModel(Double height, Double sideLength) {
        Model3D model = new Model3D();
        model.addPoint("A", sideLength, sideLength, height);
        model.addPoint("B", sideLength, -sideLength, height);
        model.addPoint("C", -sideLength, -sideLength, height);
        model.addPoint("D", -sideLength, sideLength, height);
        model.addPoint("E", sideLength, sideLength, -height);
        model.addPoint("F", sideLength, -sideLength, -height);
        model.addPoint("G", -sideLength, -sideLength, -height);
        model.addPoint("H", -sideLength, sideLength, -height);
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

        model.setTitle("Box");

        return model ;
    }

}
