package jakcan;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

import jakcan.blueprint.Blueprint;
import jakcan.blueprint.Named3dPoint;
import jakcan.geodesic.RegularIcosahedron;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting this test.");

        boolean createGeo = true ;
        boolean createCube = false ;
        Blueprint bp;

        if (createGeo)
            bp = new Blueprint(RegularIcosahedron.getPoints().values(), RegularIcosahedron.getEdges(),"Regular Icosahedron");
        else if (createCube) {
            // create cube
            bp = new Blueprint();
            bp.addPoint("A", 2.0, 2.0, 2.0);
            bp.addPoint("B", 2.0, -2.0, 2.0);
            bp.addPoint("C", -2.0, -2.0, 2.0);
            bp.addPoint("D", -2.0, 2.0, 2.0);
            bp.addPoint("E", 2.0, 2.0, -2.0);
            bp.addPoint("F", 2.0, -2.0, -2.0);
            bp.addPoint("G", -2.0, -2.0, -2.0);
            bp.addPoint("H", -2.0, 2.0, -2.0);
            bp.addEdge("C","D") ;
            bp.addEdge("A","D") ;
            bp.addEdge("D","H") ;
            bp.addEdge("C","B") ;

            bp.addEdge("C","G") ;
            bp.addEdge("C","D") ;
            bp.addEdge("A","B") ;
            bp.addEdge("B","F") ;

            bp.addEdge("A","E") ;
            bp.addEdge("F","E") ;
            bp.addEdge("H","E") ;
            bp.addEdge("H","G") ;
            bp.addEdge("F","G") ;
        }
        else
        {
            bp = new Blueprint();
            bp.addPoint("A", 3.0, 3.0, 2.0);
            bp.addPoint("B", 3.0, -3.0, 2.0);
            bp.addPoint("C", -3.0, -3.0, 2.0);
            bp.addPoint("D", -3.0, 3.0, 2.0);
            bp.addPoint("E", 3.0, 3.0, -2.0);
            bp.addPoint("F", 3.0, -3.0, -2.0);
            bp.addPoint("G", -3.0, -3.0, -2.0);
            bp.addPoint("H", -3.0, 3.0, -2.0);
            bp.addEdge("C","D") ;
            bp.addEdge("A","D") ;
            bp.addEdge("D","H") ;
            bp.addEdge("C","B") ;

            bp.addEdge("C","G") ;
            bp.addEdge("C","D") ;
            bp.addEdge("A","B") ;
            bp.addEdge("B","F") ;

            bp.addEdge("A","E") ;
            bp.addEdge("F","E") ;
            bp.addEdge("H","E") ;
            bp.addEdge("H","G") ;
            bp.addEdge("F","G") ;
        }

        // Move point A to the top
        bp.rotateToTop("A") ;

        // TODO Implement V2 and V3
        // TODO Remove lower points 
        // TODO move figure to xy plane
        // TODO generate parts list

        // Create a JSON object
        JSONObject jsonObject = bp.writeToJSON();

        try (FileWriter file = new FileWriter("data.json")) {
            // Write the JSON object to file
            file.write(jsonObject.toString(4)); // 4 is for pretty printing with indent level 4
            System.out.println("Successfully wrote JSON object to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}