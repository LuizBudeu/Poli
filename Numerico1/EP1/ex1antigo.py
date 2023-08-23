import numpy as np

EPSLON = 10**(-15)
ITMAX = 50

def erroRelativo(xnew, xold):
    return abs((xnew-xold)/xnew)*100


def calcula(A):
    E, V = np.linalg.eig(A)
    #print(np.linalg.eig(A))
    print(E[0], V[:,0])
    #print(A)

    TAMANHO_MATRIZ = len(A)
    x = np.random.rand(TAMANHO_MATRIZ, 1)

    autovalor_anterior = 0
    for i in range(ITMAX):
        x = np.dot(A, x)
        autovalor = np.linalg.norm(x)
        erro = erroRelativo(autovalor, autovalor_anterior)
        x = x/autovalor
        print(autovalor, erro, x)
        if erro <= EPSLON:
            break
        autovalor_anterior = autovalor

def parte1():
    B = np.random.rand(10, 1)
    Bt = B.transpose()
    A = np.add(B, Bt)

    calcula(A)


def parte2():
    B1 = np.random.rand(5, 5)
    B1inv = np.linalg.inv(B1)
    D1 = np.diag(np.diag(np.random.rand(5, 5)))
    A1 = np.matmul(B1, D1)
    A1 = np.matmul(A1, B1inv)

    calcula(A1)

    B2 = np.random.rand(10, 10)
    B2inv = np.linalg.inv(B2)
    D2 = np.diag(np.diag(np.random.rand(10, 10)))
    A2 = np.matmul(B2, D2)
    A2 = np.matmul(A2, B2inv)

    calcula(A2)

def main():
    parte1()
    #parte2()


if __name__ == "__main__": #Inicia  o Programa
    main()