/*
 * This function returns a data structure with 3D named points and connections between
 * them that define the structure.
 */
function geodesicGreenhouseFenceDef() {
    var ret ;

    return ret ;
}

/*
 * This function returns the points and edges that define a regular icosahedron,
 * a figure made up of 20 equilateral triangles. All edges are the same length.
 * Each point is mulitpled by the scale parameter.
 * When scale = 1, the length of the edges will be 2.
 */
function geodesicDef(scale) {
    let ret = new Object() ;
    var golden_ratio = (1 + Math.sqrt(5))/2 ;
    ret.points = [] ;
    a = Object.create(point3d)
    a.x = 0; a.y = 1; a.z = golden_ratio, a.name = 'A' ;
    b = Object.create(point3d)
    b.x = 0; b.y = -1; b.z = golden_ratio, b.name = 'B' ;
    c = Object.create(point3d)
    c.x = 0; c.y = 1; c.z = golden_ratio, c.name = 'C' ;
    d = Object.create(point3d)
    d.x = 0; d.y = 1; d.z = golden_ratio, d.name = 'D' ;
    ret.points.push(a, b, c, d) ;
    // ret.points = [ [0, 1, golden_ratio], [0, -1, golden_ratio], [0, 1, -golden_ratio], [0, -1, -golden_ratio] ]
    return ret ;
}

let point3d = Object.create({x: 0, y: 0, z: 0, name: ''}) ;
def = geodesicDef(1) ;
console.log(def) ;