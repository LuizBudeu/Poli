import matplotlib.pyplot as plt
import numpy as np
import ex2

def mu_k_ex1(xk, A):
    return (xk@(A@xk)) / (xk@xk)


def mu_k_ex2(xk, A):
    A_inv = np.linalg.inv(A)
    if np.dot(xk, A_inv@xk) < 0:
        xk = -xk
    return (xk@(A_inv@xk)) / (xk@xk)


def erro(x_estrela, iteracao):
    if np.dot(x_estrela, iteracao) > 0:
        return np.linalg.norm(x_estrela - iteracao)
    else:
        return np.linalg.norm(x_estrela + iteracao)


def A1_proximos(A1, A2):
    autovalores1 = sorted(np.linalg.eigvals(A1))
    autovalores2 = sorted(np.linalg.eigvals(A2))
    return (abs(autovalores1[-2] - autovalores1[-1]) < abs(autovalores2[-2] - autovalores2[-1]))


def plota_erros_ex1(iteracoes, A, titulo):
    n_it = len(iteracoes) - 1
    autovalores = sorted(np.linalg.eigvals(A))
    lambda1 = autovalores[-1]
    lambda2 = autovalores[-2]

    e, v = np.linalg.eig(A)
    x_estrela = v[:,np.argmax(e)]

    erros_vetor = np.array([np.linalg.norm(erro(x_estrela, iteracao)) for iteracao in iteracoes])
    erros_valor = np.array([abs(lambda1 - mu_k_ex1(iteracao, A)) for iteracao in iteracoes])
    linha_ordem_1 = np.array([(lambda2/lambda1)**k for k in range(n_it + 1)])
    linha_ordem_2 = np.array([(lambda2/lambda1)**(2*k) for k in range(n_it + 1)])

    xpoints = list(range(n_it + 1))

    plt.plot(xpoints, erros_vetor, linewidth = 3.0, label=r"Erros autovetor $||xk-x*||$")
    plt.plot(xpoints, erros_valor, linewidth = 3.0, label=r"Erros autovalor $|\mu_{k}-\lambda_{1}|$")
    plt.plot(xpoints, linha_ordem_1, linewidth = 3.0, label=r"$|\lambda_{2}/\lambda_{1}|^{k}$")
    plt.plot(xpoints, linha_ordem_2, linewidth = 3.0, label=r"$|\lambda_{2}/\lambda_{1}|^{2k}$")
    
    plt.legend(loc="lower left")

    plt.yscale("log")
    plt.grid()

    plt.title(titulo)
    plt.xlabel("Erro")
    plt.ylabel("Iterações")

    plt.show()


def plota_erros_ex2(iteracoes, A, titulo):
    n_it = len(iteracoes) - 1
    A_inv = np.linalg.inv(A)
    autovalores = sorted(np.linalg.eigvals(A_inv))
    lambda_n_inv = autovalores[-1]
    lambda_n_menos_1_inv = autovalores[-2]

    x_estrela = ex2.calcula_x_estrela(A)

    if np.dot(iteracoes[-1], x_estrela) > 0:
        erros_vetor = np.array([np.linalg.norm(x_estrela - iteracao) for iteracao in iteracoes])
    else:
        erros_vetor = np.array([np.linalg.norm(x_estrela + iteracao) for iteracao in iteracoes])
    erros_valor = np.array([abs(lambda_n_inv - mu_k_ex2(iteracao, A)) for iteracao in iteracoes])
    linha_ordem_1 = np.array([(lambda_n_menos_1_inv/lambda_n_inv)**k for k in range(n_it + 1)])
    linha_ordem_2 = np.array([(lambda_n_menos_1_inv/lambda_n_inv)**(2*k) for k in range(n_it + 1)])

    xpoints = list(range(n_it + 1))

    plt.plot(xpoints, erros_vetor, linewidth = 3.0, label=r"Erros autovetor $||xk-x*||$")
    plt.plot(xpoints, erros_valor, linewidth = 3.0, label=r"Erros autovalor $|\mu_{k}-\lambda_{n}^{-1}|$")
    plt.plot(xpoints, linha_ordem_1, linewidth = 3.0, label=r"$|\lambda_{n}/\lambda_{n-1}|^{k}$")
    plt.plot(xpoints, linha_ordem_2, linewidth = 3.0, label=r"$|\lambda_{n}/\lambda_{n-1}|^{2k}$")
    
    plt.legend(loc="lower left")

    plt.yscale("log")
    plt.grid()

    plt.title(titulo)
    plt.xlabel("Erro")
    plt.ylabel("Iterações")

    plt.show()