import numpy as np

def rotate_around_axis(points, axis, angle):
    """
    Rotate points around a given axis by angle (in radians).
    
    :param points: np.array of shape (n, 3) where n is the number of points
    :param axis: 'x', 'y', or 'z' for the rotation axis
    :param angle: Angle in radians
    :return: Rotated points
    """
    # Rotation matrices
    if axis == 'x':
        rotation_matrix = np.array([
            [1, 0, 0],
            [0, np.cos(angle), -np.sin(angle)],
            [0, np.sin(angle), np.cos(angle)]
        ])
    elif axis == 'y':
        rotation_matrix = np.array([
            [np.cos(angle), 0, np.sin(angle)],
            [0, 1, 0],
            [-np.sin(angle), 0, np.cos(angle)]
        ])
    elif axis == 'z':
        rotation_matrix = np.array([
            [np.cos(angle), -np.sin(angle), 0],
            [np.sin(angle), np.cos(angle), 0],
            [0, 0, 1]
        ])
    else:
        raise ValueError("Axis must be 'x', 'y', or 'z'")

    # Apply rotation
    return np.dot(points, rotation_matrix.T)

# Example usage
# Define some points
points = np.array([
    [1, 0, 0],
    [0, 1, 0],
    [0, 0, 1]
])

# Rotate around the y-axis by 45 degrees (π/4 radians)
rotated_points = rotate_around_axis(points, 'y', np.pi/4)

print("Original points:\n", points)
print("Rotated points:\n", rotated_points)