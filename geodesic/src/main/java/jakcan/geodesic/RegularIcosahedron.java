package jakcan.geodesic;
import java.util.HashMap;
import java.util.Map;

import jakcan.blueprint.Edges;
import jakcan.blueprint.Named3dPoint;

public class RegularIcosahedron {
    // qqqq move this to a better place
    public static final Double golden_ratio = (1 + Math.sqrt(5.0))/2.0 ;

    private static Map<String, Named3dPoint> ri_points = null ;
    private static Edges ri_edges = null ;

    // qqqq Declare a single data structure for points, edges, and faces?

    /**
     * Generate a set of points that define a regular icosahedron.
     * @return
     */
    public static Map<String, Named3dPoint> getPoints() {
        if (ri_points == null) 
        {
            ri_points = new HashMap<>() ;
            ri_points.put("A", new Named3dPoint("A", 0.0, 1.0, golden_ratio)) ;
            ri_points.put("B", new Named3dPoint("B", 0.0, -1.0, golden_ratio)) ;
            ri_points.put("C", new Named3dPoint("C",0.0, 1.0, -golden_ratio)) ;
            ri_points.put("D", new Named3dPoint("D",0.0, -1.0, -golden_ratio)) ;
    
            ri_points.put("E", new Named3dPoint("E",1.0, golden_ratio, 0.0)) ;
            ri_points.put("F", new Named3dPoint("F",-1.0, golden_ratio, 0.0)) ;
            ri_points.put("G", new Named3dPoint("G",1.0, -golden_ratio, 0.0)) ;
            ri_points.put("H", new Named3dPoint("H",-1.0, -golden_ratio, 0.0)) ;
    
            ri_points.put("I", new Named3dPoint("I",golden_ratio, 0.0, 1.0)) ;
            ri_points.put("J", new Named3dPoint("J",golden_ratio, 0.0, -1.0)) ;
            ri_points.put("K", new Named3dPoint("K",-golden_ratio, 0.0, 1.0)) ;
            ri_points.put("L", new Named3dPoint("L",-golden_ratio, 0.0, -1.0)) ;
            }
        Map<String, Named3dPoint> points = new HashMap<>() ;
        points.putAll(ri_points);

        return points ;
    }

    public static Edges getEdges() {
        getPoints() ;
        if (ri_edges == null)
        {
            ri_edges = new Edges() ;
            ri_edges.addEdge(ri_points.get("A"), ri_points.get("B")) ;
            ri_edges.addEdge(ri_points.get("A"), ri_points.get("E")) ;
            ri_edges.addEdge(ri_points.get("A"), ri_points.get("F")) ;
            ri_edges.addEdge(ri_points.get("A"), ri_points.get("I")) ;
            ri_edges.addEdge(ri_points.get("A"), ri_points.get("K")) ;
        
            ri_edges.addEdge(ri_points.get("B"), ri_points.get("G")) ;
            ri_edges.addEdge(ri_points.get("B"), ri_points.get("H")) ;
            ri_edges.addEdge(ri_points.get("B"), ri_points.get("I")) ;
            ri_edges.addEdge(ri_points.get("B"), ri_points.get("K")) ;
        
            ri_edges.addEdge(ri_points.get("C"), ri_points.get("D")) ;
            ri_edges.addEdge(ri_points.get("C"), ri_points.get("E")) ;
            ri_edges.addEdge(ri_points.get("C"), ri_points.get("F")) ;
            ri_edges.addEdge(ri_points.get("C"), ri_points.get("J")) ;
            ri_edges.addEdge(ri_points.get("C"), ri_points.get("L")) ;

            ri_edges.addEdge(ri_points.get("D"), ri_points.get("G")) ;
            ri_edges.addEdge(ri_points.get("D"), ri_points.get("H")) ;
            ri_edges.addEdge(ri_points.get("D"), ri_points.get("J")) ;
            ri_edges.addEdge(ri_points.get("D"), ri_points.get("L")) ;

            ri_edges.addEdge(ri_points.get("E"), ri_points.get("F")) ;
            ri_edges.addEdge(ri_points.get("E"), ri_points.get("I")) ;
            ri_edges.addEdge(ri_points.get("E"), ri_points.get("J")) ;

            ri_edges.addEdge(ri_points.get("F"), ri_points.get("K")) ;
            ri_edges.addEdge(ri_points.get("F"), ri_points.get("L")) ;

            ri_edges.addEdge(ri_points.get("G"), ri_points.get("H")) ;
            ri_edges.addEdge(ri_points.get("G"), ri_points.get("I")) ;
            ri_edges.addEdge(ri_points.get("G"), ri_points.get("J")) ;

            ri_edges.addEdge(ri_points.get("H"), ri_points.get("K")) ;
            ri_edges.addEdge(ri_points.get("H"), ri_points.get("L")) ;

            ri_edges.addEdge(ri_points.get("I"), ri_points.get("J")) ;

            ri_edges.addEdge(ri_points.get("K"), ri_points.get("L")) ;
            // System.out.println("initial set") ;
            // System.out.println(ri_edges) ;
        }

        Edges edges = new Edges() ;
        edges.putAll(ri_edges) ;
        return edges ;
    }

}
