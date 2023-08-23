import numpy as np
from math import sqrt

EPSLON = 10**(-15)
ITMAX = 50

def householder(A):
    n = A.shape[0]
    v = np.zeros(n)
    u = np.zeros(n)
    z = np.zeros(n)

    for k in range(0, n - 2):
        if np.isclose(A[k + 1, k], 0.0):
            alpha = -np.sqrt(np.sum(A[(k + 1) :, k] ** 2))
        else:
            alpha = -np.sign(A[k + 1, k]) * np.sqrt(np.sum(A[(k + 1) :, k] ** 2))

        two_r_squared = alpha ** 2 - alpha * A[k + 1, k]
        v[k] = 0.0
        v[k + 1] = A[k + 1, k] - alpha
        v[(k + 2) :] = A[(k + 2) :, k]
        u[k:] = 1.0 / two_r_squared * np.dot(A[k:, (k + 1) :], v[(k + 1) :])
        z[k:] = u[k:] - np.dot(u, v) / (2.0 * two_r_squared) * v[k:]

        for l in range(k + 1, n - 1):

            A[(l + 1) :, l] = (
                A[(l + 1) :, l] - v[l] * z[(l + 1) :] - v[(l + 1) :] * z[l]
            )
            A[l, (l + 1) :] = A[(l + 1) :, l]
            A[l, l] = A[l, l] - 2 * v[l] * z[l]

        A[-1, -1] = A[-1, -1] - 2 * v[-1] * z[-1]
        A[k, (k + 2) :] = 0.0
        A[(k + 2) :, k] = 0.0

        A[k + 1, k] = A[k + 1, k] - v[k + 1] * z[k]
        A[k, k + 1] = A[k + 1, k]

def gram_schmidt(A):
    m = A.shape[1]
    Q = np.zeros(A.shape)
    temp_vector = np.zeros(m)

    Q[:, 0] = A[:, 0] / np.linalg.norm(A[:, 0], ord=2)

    for i in range(1, m):
        q = Q[:, :i]
        temp_vector = np.sum(np.sum(q * A[:, i, None], axis=0) * q, axis=1)
        Q[:, i] = A[:, i] - temp_vector
        Q[:, i] /= np.linalg.norm(Q[:, i], ord=2)

    return Q

def QR(A):

    Q = gram_schmidt(A)
    R = Q.T @ A

    return (Q, R)


def eigs_2x2(A):

    b = -(A[-1, -1] + A[-2, -2])
    c = A[-1, -1] * A[-2, -2] - A[-2, -1] * A[-1, -2]
    d = np.sqrt(b ** 2 - 4 * c)

    if b > 0:
        return (-2 * c / (b + d), -(b + d) / 2)
    else:
        return ((d - b) / 2, 2 * c / (d - b))


def QR_eigenvalues(A):

    n = A.shape[0]
    λ = np.zeros(n)

    for _ in range(ITMAX):

        # Check to see if we have converged, then deflate by 1
        if np.abs(A[-1, -2]) <= EPSLON:
            n -= 1
            λ[n] = A[-1, -1]
            A = A[:-1, :-1]

        # Here we are finding the eigenvalues of the lower 2 x 2 submatrix to
        # find the best value to shift by to improve convergence rates.
        μ1, μ2 = eigs_2x2(A)

        # Since we have a explicit formula for 2x2 case, let's use it
        if n == 2:
            λ[0] = μ1
            λ[1] = μ2
            break

        p = np.array([μ1 - A[-1, -1], μ2 - A[-1, -1]]).argmin()

        alpha = μ1 if p == 0 else μ2

        I = np.eye(n)
        # Shifted QR decomp
        Q, R = QR(A - alpha * I)
        # Reverse QR
        A = R @ Q + alpha * I

    return λ


A = np.array([[6, -2, -1], 
              [-2, 6, -1],
              [-1, -1, 5]])

print(QR_eigenvalues(A))
