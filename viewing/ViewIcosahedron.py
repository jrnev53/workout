import plotly.graph_objects as go
import math

# This function returns the points and edges that define a regular icosahedron,
# a figure made up of 20 equilateral triangles. All edges are the same length.
# Each point is mulitpled by the scale parameter.
# When scale = 1, the length of the edges will be 2.
def regular_icosahedron_points():
    # ret = [] ;
    golden_ratio = (1 + math.sqrt(5))/2 ;

    ret = [
        { 'x': 0, 'y': 1, 'z': golden_ratio, 'name': 'A' },
        { 'x': 0, 'y': -1, 'z': golden_ratio, 'name': 'B' }, # AB
        { 'x': 0, 'y': 1, 'z': -golden_ratio, 'name': 'C' },
        { 'x': 0, 'y': -1, 'z': -golden_ratio, 'name': 'D' }, # CD

        { 'x': 1, 'y': golden_ratio, 'z': 0, 'name': 'E' }, # AE CE
        { 'x': -1, 'y': golden_ratio, 'z': 0, 'name': 'F' }, # AF CF
        { 'x': 1, 'y': -golden_ratio, 'z': 0, 'name': 'G' }, # BG
        { 'x': -1, 'y': -golden_ratio, 'z': 0, 'name': 'H' }, # BH 

        { 'x': golden_ratio, 'y': 0, 'z': 1, 'name': 'I' }, #AI BI
        { 'x': golden_ratio, 'y': 0, 'z': -1, 'name': 'J' }, # CJ
        { 'x': -golden_ratio, 'y': 0, 'z': 1, 'name': 'K' }, # AK, BK 
        { 'x': -golden_ratio, 'y': 0, 'z': -1, 'name': 'L' }, # CL 
    ]
    return ret 

def move_A_over_D(gd):
    
    return gd ;

def add_line(ret, p1, p2):
    return ret + [ {'p1': p1, 'p2': p2, 'name': p1['name']+p2['name'] } ]

def draw_from_A(gd):
    ret = []
    pointA = gd[0]
    pointB = gd[1] ;    
    pointC = gd[2] ;
    pointD = gd[3] ;
    pointE = gd[4] ;    
    pointF = gd[5] ;    
    pointG = gd[6] ;
    pointH = gd[7] ;
    pointI = gd[8] ;
    pointJ = gd[9] ;
    pointK = gd[10] ;   
    pointL = gd[11] ;

    ret = add_line(ret, pointA, pointB)
    ret = add_line(ret, pointA, pointE)
    ret = add_line(ret, pointA, pointF)
    ret = add_line(ret, pointA, pointI)
    ret = add_line(ret, pointA, pointK)

    ret = add_line(ret, pointB, pointG)
    ret = add_line(ret, pointB, pointH)
    ret = add_line(ret, pointB, pointI)
    ret = add_line(ret, pointB, pointK)

    ret = add_line(ret, pointC, pointD) ;
    ret = add_line(ret, pointC, pointE) ;
    ret = add_line(ret, pointC, pointF) ;
    ret = add_line(ret, pointC, pointJ) ;
    ret = add_line(ret, pointC, pointL) ;
    # DH DG DL DJ
    ret = add_line(ret, pointD, pointG) ;
    ret = add_line(ret, pointD, pointH) ;
    ret = add_line(ret, pointD, pointJ) ;
    ret = add_line(ret, pointD, pointL) ;
    # print(ret)
    # EF EI EJ 
    ret = add_line(ret, pointE, pointF) ;
    ret = add_line(ret, pointE, pointI) ;
    ret = add_line(ret, pointE, pointJ) ;
    # FK FL 
    ret = add_line(ret, pointF, pointK) ;
    ret = add_line(ret, pointF, pointL) ;
    # GH GI GJ
    ret = add_line(ret, pointG, pointH) ;
    ret = add_line(ret, pointG, pointI) ;
    ret = add_line(ret, pointG, pointJ) ;
    # HI HK HL 
    # ret = add_line(ret, pointH, pointI) ;
    ret = add_line(ret, pointH, pointK) ;
    ret = add_line(ret, pointH, pointL) ;
    # IJ 
    ret = add_line(ret, pointI, pointJ) ;
    # KL
    ret = add_line(ret, pointK, pointL) ;

    pivot = pointL
    for pt in gd:
        if pt['name'] > pivot['name']:
            ret = ret + [ {'p1': pivot, 'p2': pt, 'name': '*' + pivot['name'] + pt['name'] } ]

    return ret

# Get the points of the regular icosahedron
gd = regular_icosahedron_points() ;

# stand the figure with the top and bottom being vertices
gd = move_A_over_D(gd) ;

# define the edges of the regular icosahedron
draw_these_lines = draw_from_A(gd) ;
# print(draw_these_lines)

# fig = go.Figure(data=go.Scatter3d(
#     x=x, 
#     y=y, 
#     z=z, 
#     mode='lines',  # 'lines' for just lines, 'lines+markers' for both
#     name='3D Line'
# ))

xList = []
yList = []
zList = []
hoverList = []
    
for pt in gd:
    xList = xList + [pt['x']] ;
    yList = yList + [pt['y']] ;
    zList = zList + [pt['z']] ;
    hoverList = hoverList + [pt['name']] ;

# draw vertices
data = [go.Scatter3d(
    x=xList,  
    y=yList,
    z=zList,
    mode='markers',
    marker=dict(
        size=5,
        color='blue',  # Optional: You can change color for distinction
        symbol='circle'
    ),
    hovertext=hoverList,
    # legend='vertices'
)]

# draw edges
for lineinfo in draw_these_lines:
    newline = go.Scatter3d(
        x = [ lineinfo['p1']['x'], lineinfo['p2']['x'] ],
        y = [ lineinfo['p1']['y'], lineinfo['p2']['y'] ],
        z = [ lineinfo['p1']['z'], lineinfo['p2']['z'] ],
        mode = 'lines',
        name = lineinfo['name'],
    )
    data = data + [newline]
    # fig.add_trace( newline )
# fig.add_trace( linedata )
fig = go.Figure(data)

# shade the AEF triangle
points = [ gd[0], gd[4], gd[5] ]
# Create the triangle by specifying the indices of the points
i, j, k = [0, 1, 2], [1, 2, 0], [2, 0, 1]  # These define how points connect

fig = go.Figure(data= data + [
    go.Mesh3d(
        x=[p['x'] for p in points],
        y=[p['y'] for p in points],
        z=[p['z'] for p in points],
        i=i, j=j, k=k,
        color='lightblue',
        opacity=0.5
    )
])

# Update layout for better visualization
# fig.update_layout(
#     scene=dict(
#         xaxis_title='X AXIS',
#         yaxis_title='Y AXIS',
#         zaxis_title='Z AXIS'
#     ),
#     width=700,
#     margin=dict(r=10, l=10, b=10, t=10)
# )

fig.update_layout(scene=dict(
    xaxis_title='X Axis',
    yaxis_title='Y Axis',
    zaxis_title='Z Axis'),
    title='Regular Icosahedron'
)

# Show the figure
fig.show()