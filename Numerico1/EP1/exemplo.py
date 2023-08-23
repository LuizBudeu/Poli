import numpy as np

A = np.array([[-2, -4, 2],
              [-2,  1, 2],
              [ 4,  2, 5]])

e = np.linalg.eig(A)[1][1] #qualquer um dos altovetores
print(e)
print(A@e)
print( (A@e) / e )