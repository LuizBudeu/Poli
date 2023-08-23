# Exercício 2: método das potências inverso

import numpy as np
import metodos

EPSILON = 1e-15
EPSILON_SOR = 1e-8
ITMAX = 30  #entre 30 e 70, dependendo do problema
npt1 = 7  #entre 7 e 12
npt2 = 5  #entre 5 e 10
autovalores_pt2_proximos = np.array([5,4,3,2,1]) #npt2 valores estritamente positivos e distintos
autovalores_pt2_distantes = np.array([50,43,38,20,1]) #npt2 valores estritamente positivos e distintos
rng = np.random.default_rng()


def escolhe_omega(A,b):  #omega pertence ao intervalo (1,2(
    omega = 1.0
    menor_n_it = float("inf")
    while omega < 2.0:
        n_it = resolve_sor_k(A, b, omega)[1]
        if n_it < menor_n_it:
            menor_n_it = n_it
            melhor_omega = omega
        omega += .1  #testa de 1 até 2, em passos de 0.1
    return melhor_omega


def resolve_sor(A, b, omega):  #resolve sistema Ax = b
    return resolve_sor_k(A, b, omega)[0]


def resolve_sor_k(A, b, omega):
    global EPSILON_SOR

    n = len(b)
    xk = np.zeros(n)
    xk_anterior = float("inf") * np.ones(n)
    k = 0
    while np.linalg.norm(xk - xk_anterior) > EPSILON_SOR:
        xk_anterior = np.copy(xk)
        for i in range(n):
            x_temp = xk
            x_temp[i] = 0.0
            residuo = b[i] - np.dot(A[i], x_temp)
            xk[i] = (1 - omega)*xk[i] + (omega/A[i][i]) * residuo
        k += 1
    
    return xk, k


def passa_crit_linhas(A):
    n = len(A)
    for i in range(n):
        if abs(A[i][i]) < sum(map(abs, A[i])) - abs(A[i][i]): 
            return False
    return True


def proximo(A, xk, omega):  #calcula próxima iteração xk+1 do método das potências inverso
    xk_desnormalizado = resolve_sor(A, xk, omega)
    return xk_desnormalizado / np.linalg.norm(xk_desnormalizado)


def escolhe_A_pt1(n):
    global rng
    while True:
        B = rng.random((n,n))
        A = B + B.T + n*np.identity(n)
        if passa_crit_linhas(A): return A


# x* é o autovetor associado ao maior autovalor em módulo de A^(-1)
def calcula_x_estrela(A):
    A_inv = np.linalg.inv(A)
    e, v = np.linalg.eig(A_inv)
    x_estrela = v[:,np.argmax(e)]
    return x_estrela


# Calcula o autovalor associado ao autovetor x_eig de A^(-1)
def mu_k(x_eig, A):
    return np.linalg.norm(x_eig) / np.linalg.norm(A@x_eig)


def potencias(A, x0, omega, x_estrela):
    global EPSILON
    global ITMAX

    iteracoes = [x0]
    xk = x0
    k = 0
    while True:
        xkprox = proximo(A, xk, omega)
        iteracoes.append(xkprox)
        k += 1
        erro1 = np.linalg.norm(xkprox - x_estrela)
        #para o caso dele convergir para o vetor oposto
        erro2 = np.linalg.norm(xkprox + x_estrela)
        if erro1 < EPSILON or erro2 < EPSILON:
            print("Convergiu em", k, "iterações")
            break
        if k == ITMAX: 
            print("Atingiu o limite de iterações")
            break
        xk = xkprox

    lambda_n = 1/mu_k(iteracoes[k], A)

    return lambda_n, iteracoes


def calcula_lambda_n(A):
    return min(np.linalg.eig(A)[0])


def potencias_wrapper(A):
    global rng
    n = len(A)
    x0 = rng.random(n)
    omega = escolhe_omega(A, x0)
    x_estrela = calcula_x_estrela(A)  # x* para o critério de parada
    return potencias(A, x0, omega, x_estrela)


def erro_assintotico(A):
    autovalores = sorted(np.linalg.eigvals(A))
    return autovalores[-2]/autovalores[-1]


def parte1():
    global npt1
    A = escolhe_A_pt1(npt1)  # Seleciona A baseado no critério de linhas
    lambda_n, iteracoes = potencias_wrapper(A)

    ## Apresentação dos resultados
    print("Resultado:     lambda n =", lambda_n)
    print("Valor correto: lambda n =", calcula_lambda_n(A))
    metodos.plota_erros_ex2(iteracoes, A, "Exercício 2 - Parte 1")


def escolhe_A_pt2(n, autovalores):
    global rng
    B0 = rng.random((n, n))
    D = np.diag(autovalores)
    p = 1
    while True:
        B = B0 + p*np.identity(n)
        A = B @ (D @ np.linalg.inv(B))
        if passa_crit_linhas(A):
            break
        p += 1
    return A


def parte2():
    global npt2, autovalores_pt2_distantes, autovalores_pt2_proximos

    A = escolhe_A_pt2(npt2, autovalores_pt2_proximos)
    iteracoes = potencias_wrapper(A)[1]
    metodos.plota_erros_ex2(iteracoes, A, r"Exercício 2 - Parte 2 - $\lambda_{n}$ e $\lambda_{n-1}$ próximos")

    A = escolhe_A_pt2(npt2, autovalores_pt2_distantes)
    iteracoes = potencias_wrapper(A)[1]
    metodos.plota_erros_ex2(iteracoes, A, r"Exercício 2 - Parte 2 - $\lambda_{n}$ e $\lambda_{n-1}$ distantes")


def main():
    parte1()
    parte2()

if __name__ == "__main__":
    main()
