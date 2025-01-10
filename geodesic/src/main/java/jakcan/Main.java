package jakcan;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

import jakcan.geodesic.Box;
import jakcan.geodesic.Cube;
import jakcan.geodesic.Geodesic;
import jakcan.geodesic.RegularIcosahedron;
import jakcan.model3d.Model3D;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting this test.");

        boolean createGeo = false ;
        boolean createCube = false ;
        boolean createRI_2V = true ;
        Model3D model;

        if (createRI_2V)
            model = Geodesic.getModel(2) ;
        else if (createGeo)
            model = RegularIcosahedron.getModel() ;
        else if (createCube) {
            // create cube
            model = Cube.createModel(2.0) ;
        }
        else
        {
            model = Box.createModel(2.0, 3.0) ;
        }

        // Move point A to the top
        model.rotateToTop("A") ;

        // define the model as four meters high
        // model.scale(4.0 / model.getGreatestZ()) ;

        // DONE define faces and print
        // DONE Scale the size
        model.scale(4.0 / model.getGreatestZ()) ;
        
        // TODO Implement V2 and V3

        // TODO Remove lower points 
        // TODO move figure to xy plane - move z by an offset.
        // TODO generate parts list
        Geodesic g = new Geodesic() ;
        System.out.println(g.generatePartsList(model)) ;
        // TODO Define the doorway

        // Create a JSON object
        JSONObject jsonObject = model.writeToJSON();

        try (FileWriter file = new FileWriter("data.json")) {
            // Write the JSON object to file
            file.write(jsonObject.toString(4)); // 4 is for pretty printing with indent level 4
            System.out.println("Successfully wrote JSON object to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}