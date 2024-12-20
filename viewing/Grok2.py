import plotly.graph_objects as go

# Example data for a 3D line
x = [1, 2, 3, 4, 5]
y = [5, 4, 3, 2, 1]
z = [2, 3, 4, 5, 6]

fig = go.Figure(data=go.Scatter3d(
    x=x, 
    y=y, 
    z=z, 
    mode='lines',  # 'lines' for just lines, 'lines+markers' for both
    name='3D Line'
))

fig.update_layout(scene=dict(
    xaxis_title='X Axis',
    yaxis_title='Y Axis',
    zaxis_title='Z Axis'),
    title='3D Line Plot'
)

fig.show()