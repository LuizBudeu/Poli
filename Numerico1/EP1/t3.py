import numpy as np

EPSLON = 10**(-15)
ITMAX = 50



def fatoracaoQR(A): #Fatora a matriz A como Q ortogonal e R trinagular
    n = len(A)

    Q = np.empty((n, n)) #Inicializa matriz Q
    u = np.empty((n, n)) #Inicializa matriz u 

    u[:, 0] = A[:, 0]
    Q[:, 0] = u[:, 0] / np.linalg.norm(u[:, 0]) #Ortogonalizar

    for i in range(1, n):
        u[:, i] = A[:, i]
        for j in range(i):
            u[:, i] -= (A[:, i] @ Q[:, j]) * Q[:, j] 

        Q[:, i] = u[:, i] / np.linalg.norm(u[:, i]) 

    R = np.zeros((n, n))
    for i in range(n):
        for j in range(i, n):
            R[i, j] = A[:, j] @ Q[:, i]

    return Q, R

def calcula(A):
    "Find the eigenvalues of A using QR decomposition."

    A_antigo = np.copy(A)
    A_novo = np.copy(A)
    V = np.ones((len(A), len(A))) 

    diff = 1000000 #Um valor bem grande
    for i in range(ITMAX):
        A_antigo[:, :] = A_novo
        Q, R = fatoracaoQR(A_antigo)
        A_novo[:, :] = R @ Q

        V = V @ Q

        diff = np.abs(A_novo - A_antigo).max()
        if diff < EPSLON:
            break

    autovalores = np.diag(A_novo)

    return autovalores, V


def parte1(): #Cria a matriz A da parte 1 e mostra os autovalores
    A = np.array([[6., -2., -1.], 
                [-2., 6., -1.],
                [-1., -1., 5.]])
    autovalores, autovetores = calcula(A)
    print(autovalores)

def parte2(): #Cria a matriz A da parte 2 e mostra os autovalores
    B = ([[1., 1.],
        [-3., 1.]])

    autovalores, autovetores = calcula(B)
    print(autovalores)

def parte3(): #Cria a matriz A da parte 3 e mostra os autovalores
    C = ([[3., -3.],
        [0.33333, 5.]])

    autovalores, autovetores = calcula(C)
    print(autovalores)



def main(): #Chama as tres partes
    parte1()
    parte2()
    parte3()



if __name__ == "__main__": #Inicia  o programa
    main()