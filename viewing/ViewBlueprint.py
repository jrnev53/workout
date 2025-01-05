import plotly.graph_objects as go
import math
import json

# read the blueprint
with open('data.json', 'r') as file:
    bp = json.load(file) 

# draw vertices
# need array of x values, y values, z values, and point names.
xList = []
yList = []
zList = []
hoverList = []
ptDict = {}

for point in bp['points']:
    xList = xList + [ point['x']]
    yList = yList + [ point['y']]
    zList = zList + [ point['z']]
    hoverList = hoverList + [ point['name']]
    ptDict[point['name']] = point

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
)]

# draw edges
# need array of p1 & p2 x's, y's, and z's.
for edge in bp['edges']:
    # get names of points
    p1name = edge['pt1']
    p2name = edge['pt2']
    pt1 = ptDict.get(p1name) 
    pt2 = ptDict.get(p2name)
    newline = go.Scatter3d(
        x = [ pt1['x'], pt2['x'] ],
        y = [ pt1['y'], pt2['y'] ],
        z = [ pt1['z'], pt2['z'] ],
        mode = 'lines',
        name = pt1['name'] + pt2['name']
    )
    data = data + [newline]

# shade the AEF triangle
# Create the triangle by specifying the indices of the points
i, j, k = [0, 1, 2], [1, 2, 0], [2, 0, 1]  # These define how points connect

for face in bp['faces']:
    points = []
    for ptname in face['outline'] :
        points = points + [ ptDict.get(ptname) ] ;
    data= data + [
        go.Mesh3d(
            x=[p['x'] for p in points],
            y=[p['y'] for p in points],
            z=[p['z'] for p in points],
            i=i, j=j, k=k,
            color='lightblue',
            opacity=0.5
        )
    ]

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

# add the data to the figure
fig = go.Figure(data)

fig.update_layout(scene=dict(
    xaxis_title='X Axis',
    yaxis_title='Y Axis',
    zaxis_title='Z Axis'),
    title=bp['title']
)

# Show the figure
fig.show()