import plotly.graph_objects as go
import numpy as np

# Define the plane parameters
z = 0  # Z-coordinate of the plane (flat at z=0)
x = y = np.linspace(-10, 10, 100)  # Creating a grid
X, Y = np.meshgrid(x, y)

# Create the surface plot for the plane
fig = go.Figure(data=[go.Surface(z=z*np.ones_like(X), x=X, y=Y, 
                                 colorscale=[[0, 'rgb(128,128,128)'], [1, 'rgb(128,128,128)']],
                                 showscale=False)])

# mySurface = go.Surface()

# Customize the layout
fig.update_layout(
    title='Flat Plane at Z=0',
    scene=dict(
        xaxis_title='X Axis',
        yaxis_title='Y Axis',
        zaxis_title='Z Axis',
        aspectmode='cube'
    )
)

# Show the plot
fig.show()