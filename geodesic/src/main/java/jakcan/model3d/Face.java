package jakcan.model3d;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Faces define planes in the model. The order of the points defining the face matters.
 * Faces can have 3 or more of points defining it.
 */
public class Face {

    private String faceName ;
    private List<Named3dPoint> outline ;

    public Face(String faceName, Named3dPoint p1, Named3dPoint p2, Named3dPoint p3, Named3dPoint... pts) {
        this.faceName = faceName ;
        outline = new LinkedList<>() ;
        outline.add(p1) ;
        outline.add(p2) ;
        outline.add(p3) ;
        for (Named3dPoint pt : pts)
        {
            outline.add(pt) ;
        }
    }

    public String getName() {
        return faceName ;
    }

    public List<Named3dPoint> getPoints() {
        return outline ;
    }

}
