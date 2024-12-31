package jakcan.blueprint;

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

}
