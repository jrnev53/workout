package jakcan.geodesic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;

import jakcan.model3d.Model3D;
import jakcan.model3d.Named3dPoint;

public class Geodesic {
    /** This determines */
    private int scale = 3;

    public String generatePartsList(Model3D model) {
        StringBuilder sb = new StringBuilder();

        Map<BigDecimal, Integer> strutLengths = new TreeMap<>();

        // count the number of each unique strut length
        for (Entry<Named3dPoint, Set<Named3dPoint>> entry : model.getEdges().getEdges().entrySet()) {
            Named3dPoint p1 = entry.getKey();
            for (Named3dPoint p2 : entry.getValue()) {
                // one each edge once
                if (p1.getName().compareTo(p2.getName()) < 0) {
                    double xdiff = Math.pow(p2.getX() - p1.getX(), 2.0);
                    double ydiff = Math.pow(p2.getY() - p1.getY(), 2.0);
                    double zdiff = Math.pow(p2.getZ() - p1.getZ(), 2.0);
                    double strutLength = Math.sqrt(xdiff + ydiff + zdiff);
                    BigDecimal dbsl = new BigDecimal(strutLength);
                    dbsl = dbsl.setScale(scale, RoundingMode.HALF_EVEN);
                    Integer count = strutLengths.get(dbsl);
                    if (count == null) {
                        count = 0;
                    }
                    strutLengths.put(dbsl, ++count);
                }
            }
        }

        for (Entry<BigDecimal, Integer> entry : strutLengths.entrySet()) {
            sb.append("struct length: ").append(entry.getKey()).append(" count: ").append(entry.getValue()).append("\n") ;
        }

        // count the number of vertices
        int numVerts = model.getPoints().size();
        sb.append(String.format("There are %d vertices", numVerts)) ;

        return sb.toString();
    }
}
