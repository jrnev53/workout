package jakcan.blueprint;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map.Entry;

public class Edges {

    private Map<Named3dPoint, Set<Named3dPoint>> edges;

    public Edges() {
        edges = new HashMap<>();
    }

    /**
     * Add all edges from another set.
     */
    public void putAll(Edges ri_edges) {
        for (Entry<Named3dPoint, Set<Named3dPoint>> node : ri_edges.getAll()) {
            Named3dPoint p1 = node.getKey() ;
            // get or create node for this point
            Set<Named3dPoint> this_p1_set = edges.get(p1) ;
            if (this_p1_set == null)
            {
                this_p1_set = new HashSet<>() ;
                edges.put(p1, this_p1_set) ;
            }
            for (Named3dPoint p2 : node.getValue()) {
                this_p1_set.add(p2) ;
            }
        }
    }

    Set<Entry<Named3dPoint, Set<Named3dPoint>>> getAll() {
        return edges.entrySet() ;
    }

    /**
     * Record AB and BA.
     * 
     * @param p1
     * @param p2
     */
    public void addEdge(Named3dPoint p1, Named3dPoint p2) {
        // System.out.println("Adding edge " + p1.name + p2.name) ;
        // get or create node for p1
        {
            Set<Named3dPoint> targets1 = edges.get(p1) ;
            if (targets1 == null)
            {
                targets1 = new HashSet<>() ;
                edges.put(p1, targets1) ;
            }
            targets1.add(p2) ;
        }

        {
            Set<Named3dPoint> targets2 = edges.get(p2) ;
            if (targets2 == null)
            {
                targets2 = new HashSet<>() ;
                edges.put(p2, targets2) ;
            }
            targets2.add(p1) ;        
        }
        // System.out.println(this) ;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        sb.append( String.format("Edges (%d) ", edges.size() / 2))  ; // size is wrong. Need to count everything in sets.
        for (Entry<Named3dPoint, Set<Named3dPoint>> node : edges.entrySet()) {
            for (Named3dPoint pt : node.getValue()) {
                sb.append("(").append(node.getKey().name).append(" ").append(pt.name).append(")") ;
            }
        }
        return sb.toString() ;
    }

    public JSONArray writeToJSON() {
        JSONArray ret = new JSONArray() ;

        // to avoid duplication, only write edges where the first point is less than the 2nd point
        for (Entry<Named3dPoint,Set<Named3dPoint>> entry : edges.entrySet()) {
            Named3dPoint pt1 = entry.getKey() ;
            for (Named3dPoint pt2 : entry.getValue()) {
                if (pt1.name.compareTo(pt2.name) <= 0) {
                    JSONObject edgeData = new JSONObject() ;
                    edgeData.put("pt1", pt1.name) ;
                    edgeData.put("pt2", pt2.name) ;
                    ret.put(edgeData) ;
                }
            }
        }

        return ret ;
    }
}
