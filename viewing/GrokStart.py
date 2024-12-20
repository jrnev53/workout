import plotly.graph_objects as go

fig = go.Figure(data=[go.Scatter3d(
    x=[1, 2, 3, 4, 5],
    y=[5, 4, 3, 2, 1],
    z=[2, 3, 4, 5, 6],
    mode='markers'
)])

fig.update_layout(scene=dict(
    xaxis_title='X AXIS TITLE',
    yaxis_title='Y AXIS TITLE',
    zaxis_title='Z AXIS TITLE'),
    width=900,
    margin=dict(r=20, b=10, l=10, t=10))

fig.show()