import plotly.graph_objects as go

# Define the three points in 3D space
points = [
    [0, 0, 0],  # Point A
    [1, 0, 0],  # Point B
    [0, 1, 0]   # Point C
]

# Create the triangle by specifying the indices of the points
i, j, k = [0, 1, 2], [1, 2, 0], [2, 0, 1]  # These define how points connect

# Create the 3D scatter plot
fig = go.Figure(data=[
    go.Mesh3d(
        x=[p[0] for p in points],
        y=[p[1] for p in points],
        z=[p[2] for p in points],
        i=i, j=j, k=k,
        color='lightblue',
        opacity=0.5
    ),
    go.Scatter3d(
        x=[p[0] for p in points], 
        y=[p[1] for p in points], 
        z=[p[2] for p in points],
        mode='markers',
        marker=dict(size=5, color='red')
    )
])

# Layout settings
fig.update_layout(
    scene=dict(
        xaxis_title='X AXIS',
        yaxis_title='Y AXIS',
        zaxis_title='Z AXIS',
    ),
    title='3D Face Defined by Three Points'
)

# Show the plot
fig.show()