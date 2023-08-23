import numpy as np
import metodos

EPSLON = 10**(-15)
ITMAX = 50

def erro_vetor(xk, A): #Calcula o erro do vetor
    e,v = np.linalg.eig(A)
    x_estrela = v[:, np.argmax(e)]
    if (np.dot(x_estrela, xk) > 0):
        return np.linalg.norm(xk - x_estrela)
    else:
        return np.linalg.norm(xk + x_estrela)


def calcula(A): #Calcula os autovalores e autovetores seguindo o método das potências
    TAMANHO_MATRIZ = len(A)
    rng = np.random.default_rng()
    x = rng.random(TAMANHO_MATRIZ) #Cria o vetor 'x' com inicialização aleatória

    iteracoes = []  #Para plotagem
    for i in range(ITMAX): #Itera até o limite setado por ITMAX
        iteracoes.append(x)
        x = np.dot(A, x) #Transforma o vetor 'x' usando a matriz A
        autovalor = np.linalg.norm(x) #Normaliza o valor
        x = x/autovalor  #Normaliza o valor
        erro = erro_vetor(x, A)
        if erro <= EPSLON: #Se o erro for menor que EPSLON, sai do loop
            break
    return iteracoes

def parte1(): #Cria a matriz A da parte 1
    rng = np.random.default_rng()
    B = rng.random((10,10))
    Bt = B.transpose()
    A = np.add(B, Bt)

    iteracoes = calcula(A) #Calcula os valores
    metodos.plota_erros_ex1(iteracoes, A, "Ex1 - Parte 1") #Plotagem as imagens da parte 1


def parte2(): #Cria as matrizes A1 e A2 da parte 2
    B1 = np.random.rand(5, 5)
    B1inv = np.linalg.inv(B1)
    D1 = np.diag(np.diag(np.random.rand(5, 5)))
    A1 = np.matmul(B1, D1)
    A1 = np.matmul(A1, B1inv)

    iteracoes1 = calcula(A1) #Calcula os valores

    B2 = np.random.rand(10, 10)
    B2inv = np.linalg.inv(B2)
    D2 = np.diag(np.diag(np.random.rand(10, 10)))
    A2 = np.matmul(B2, D2)
    A2 = np.matmul(A2, B2inv)

    iteracoes2 = calcula(A2) #Calcula os valores

    temp = metodos.A1_proximos(A1, A2) #Plotagem das imagens da parte 2
    if temp:
        metodos.plota_erros_ex1(iteracoes1, A1, "Ex1 - Parte 2 - $\lambda_{1}$ e $\lambda_{2}$ próximos")
        metodos.plota_erros_ex1(iteracoes2, A2, "Ex1 - Parte 2 - $\lambda_{1}$ e $\lambda_{2}$ distantes")
    else:
        metodos.plota_erros_ex1(iteracoes2, A2, "Ex1 - Parte 2 - $\lambda_{1}$ e $\lambda_{2}$ próximos")
        metodos.plota_erros_ex1(iteracoes1, A1, "Ex1 - Parte 2 - $\lambda_{1}$ e $\lambda_{2}$ distantes")       


def main(): #Chama as duas partes
    parte1()
    parte2()


if __name__ == "__main__": #Inicia  o programa
    main()