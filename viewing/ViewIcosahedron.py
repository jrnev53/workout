import plotly.graph_objects as go
import math

# This function returns the points and edges that define a regular icosahedron,
# a figure made up of 20 equilateral triangles. All edges are the same length.
# Each point is mulitpled by the scale parameter.
# When scale = 1, the length of the edges will be 2.
def regular_icosahedron():
    # ret = [] ;
    golden_ratio = (1 + math.sqrt(5))/2 ;

    ret = [
        { 'x': 0, 'y': 1, 'z': golden_ratio, 'name': 'A' },
        { 'x': 0, 'y': -1, 'z': golden_ratio, 'name': 'B' },
        { 'x': 0, 'y': 1, 'z': -golden_ratio, 'name': 'C' },
        { 'x': 0, 'y': -1, 'z': -golden_ratio, 'name': 'D' },

        { 'x': 1, 'y': golden_ratio, 'z': 1, 'name': 'E' },
        { 'x': -1, 'y': golden_ratio, 'z': -1, 'name': 'F' },
        { 'x': 1, 'y': -golden_ratio, 'z': 1, 'name': 'G' },
        { 'x': -1, 'y': -golden_ratio, 'z': -1, 'name': 'H' },

        { 'x': golden_ratio, 'y': 0, 'z': 1, 'name': 'I' },
        { 'x': golden_ratio, 'y': 0, 'z': -1, 'name': 'J' },
        { 'x': -golden_ratio, 'y': 0, 'z': 1, 'name': 'K' },
        { 'x': -golden_ratio, 'y': 0, 'z': -1, 'name': 'L' },
    ]
    return ret 

# Get the points of the icosahedron
gd = regular_icosahedron() ;

# each point should have 5 neighbor points that are closest and all the same distance away (within tolerance)
# for each point, find it's five neighbors 
# draw lines between the point and it's neighbors.

# plot the points
fig = go.Figure(data=[go.Scatter3d(
    x=[], 
    y=[],
    z=[],
    mode='markers'
)])

for pt in gd:

    fig.add_trace(go.Scatter3d(
    x=[pt['x']],  
    y=[pt['y']],
    z=[pt['z']],
    mode='markers',
    marker=dict(
        size=12,
        color='red',  # Optional: You can change color for distinction
        symbol='circle'
    ),
    hovertext=pt['name'],
))

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
    title='3D Line Plot'
)

# Show the figure
fig.show()