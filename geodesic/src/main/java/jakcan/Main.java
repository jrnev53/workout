package jakcan;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

import jakcan.geodesic.RegularIcosahedron;
import jakcan.model3d.Box;
import jakcan.model3d.Cube;
import jakcan.model3d.Model3D;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting this test.");

        boolean createGeo = true ;
        boolean createCube = false ;
        Model3D model;

        if (createGeo)
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

        // TODO define faces and print
        // RegularIcosahedron - add face data
        // here - Create a Blueprint from the Model3D, print only faces, 
        // Things in models have labels, things in blueprints can be assigned a color.
        // Blueprints have printing info, Models don't? Models have blueprints?

        // DONE Scale the size
        // define the model as four meters high
        // model.scale(4.0 / model.getGreatestZ()) ;
        // TODO Implement V2 and V3
        // TODO Remove lower points 
        // TODO move figure to xy plane
        // TODO generate parts list
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