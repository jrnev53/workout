package jakcan.model3d;

// The name of the point will be immutable, but it's position is changeable.
public class Named3dPoint {
    Double x, y, z ;
    String name ;

    public Named3dPoint(String name, Double x, Double y, Double z)
    {
        this.name = name ;
        this.x = x ;
        this.y = y ;
        this.z = z ;
    }

    @Override
    public String toString() {
        return String.format("(%s % 4.2f,% 4.2f,% 4.2f)", name, x, y, z)  ;
    }

    public Double getX() {
        return x ;
    }

    public Double getY() {
        return y ;
    }

    public String getName() {
        return name ;
    }

    public Double getZ() {
        return z ;
    }

    /**
     * Returns the distance of this point to the origin.
     */
    public Double getRadius() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) ;
    }

    /**
     * Extend this point from the origin so that it's length matches the radius
     * @param radius
     */
    public void setVectorLength(Double radius) {
        Double originalLen = getRadius() ;
        Double ratio = radius /originalLen ;
        x = x * ratio ;
        y = y * ratio ;
        z = z * ratio ;
    }

}
