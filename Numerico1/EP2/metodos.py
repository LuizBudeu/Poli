# Importações
import numpy as np 
import time 
from scipy.stats import norm
import matplotlib.pyplot as plt

# Constantes
N = 10000 
L = 10


# Obter 'u' pelo uso de 2 loops, 'i' e 'j'
def uPor2Loops(K, sigma, T, r, N, M, L):
    u = np.zeros((M+1)*(N+1))
    u = u.reshape((N+1, M+1))

    # Preencher i == 0 com 0
    u[0] = 0

    # Preencher j == 0 com K*max(np.e**(i*2*L/N - L) - 1, 0)
    i = 0
    while i <= N:
        u[i][0] = K*max(np.e**(i*2*L/N - L) - 1, 0)
        i += 1

    # Preencher i == N com K*np.e**(L+sigma**(2) * j*T/M/2)
    j = 0
    while j <= M:
        u[N][j] = K*np.e**(L+sigma**(2) * j*T/M/2)
        j += 1

    # Preencher o resto
    i = 1
    j = 0
    cte = (T/M)/(4*(L**2) /(N**2)) * (sigma**2/2)
    while j < M:
        i = 1
        while i < N:
            u[i][j+1] = u[i][j] + (cte * (u[i-1][j] - 2*u[i][j] + u[i+1][j]))
            i += 1
        j += 1

    return u

# Obter 'u' por vetorização
def uPorVetorizacao(K, sigma, T, r, N, M, L):
    u = np.zeros((M+1)*(N+1))
    u = u.reshape((N+1, M+1))

    # Preencher i == 0 com 0
    u[0] = 0

    # Preencher j == 0 com K*max(np.e**(i*2*L/N - L) - 1, 0)
    i = 0
    while i <= N:
        u[i][0] = K*max(np.e**(i*2*L/N - L) - 1, 0)
        i += 1

    # Preencher i == N com K*np.e**(L+sigma**(2) * j*T/M/2)
    j = 0
    while j <= M:
        u[N][j] = K*np.e**(L+sigma**(2) * j*T/M/2)
        j += 1

    cte = (T/M)/(4*(L**2) /(N**2)) * (sigma**2/2)
    j = 0
    while j <= M:
        u[1:N-1][j+1] = u[1:N-1][j] + (cte * (u[0:N-2][j] - 2*u[1:N-1][j] + u[2:N][j]))
        j += 1

    return u

# Obtém a matriz Vij
def Vij(K, sigma, T, r, N, M, L, utype):
    v = np.zeros((M+1)*(N+1))
    v = v.reshape((N+1, M+1))
    if utype == "vet":
        u = uPorVetorizacao(K, sigma, T, r, N, M, L)
    else:
        u = uPor2Loops(K, sigma, T, r, N, M, L)
    
    i = 0
    j = 0
    while i <= N:
        j = 0
        while j <= M:
            v[i][j] = u[i][j] * np.e**(-r * j*T/M)
            j += 1 
        i += 1

    return v

# Obtém a matriz Sij
def Sij(K, sigma, T, r, N, M, L):
    s = np.zeros((M+1)*(N+1))
    s = s.reshape((N+1, M+1))

    i = 0
    j = 0
    while i <= N:
        while j <= M:
            s[i][j] = K*np.e**(i*2*L/N - L - (r - sigma**2 /2 )*j*T/M)
            j += 1
        i += 1
    return s

# Obtém X(S, t)
def Xst(S, t, K, sigma, T, r, L):
    return np.log(S/K) + (r - sigma**2 /2) * (T-t)

# Obtém V1(S, t) utilizando a aproximação (6)
def Vst1(S, t, K, sigma, T, r, N, M, L, utype):
    vij = Vij(K, sigma, T, r, N, M, L, utype)
    xst = Xst(S, t, K, sigma, T, r, L)
    taut = T-t

    i = 0
    salvai = 0
    maisproximo = 10000
    while i <= N:
        xi = i*2*L/N - L
        if abs(xi - xst) < maisproximo:
            maisproximo = abs(xi - xst)
            salvai = i
        i += 1

    j = 0
    salvaj = 0
    maisproximo = 10000
    while j <= N:
        tauj = j*T/M
        if abs(tauj - taut) < maisproximo:
            maisproximo = abs(tauj - taut)
            salvaj = j
        j += 1

    return vij[salvai][salvaj]

# Obtém V2(S, t) utilizando a aproximação (7)
def Vst2(S, t, K, sigma, T , r, N, M, L, utype): 
    xst = Xst(S, t, K, sigma, T, r, L)
    vij = Vij(K, sigma, T, r, N, M, L, utype)
    taut = T-t

    i = 0
    xi = 0
    xii = 0
    while i <= N:
        xi = i*2*L/N - L
        xii = (i+1)*2*L/N - L
        if xi <= xst <= xii:
            break
        i += 1

    j = 0
    salvaj = 0
    maisproximo = 10000
    while j <= M:
        tauj = j*T/M
        if abs(taut - tauj) < maisproximo:
            maisproximo = abs(taut - tauj)
            salvaj = j
        j += 1

    return ((xii - xst)*vij[i][salvaj] - (xi - xst)*vij[i+1][salvaj])/(xii - xi)

def lucroPorAtivo(S, t, K, sigma, T, r, N, M, L, utype, premioPorAtivo):
    return Vst1(S, t, K, sigma, T, r, N, M, L, utype) - premioPorAtivo

# Roda o cenário fictício
def cenarioFicticio():
    S = 1
    t = 0
    K = 1.00
    sigma = 0.01 
    T = 1 
    r = 0.01
    M = 30    # M calculado para ser estável
    utype = 'loops'    # Maneiras de calcular 'u'; 'vet' para vetorização e 'loops' para 2 loops
                       # Foi escolhido o modo 'loops' para rodar pois, mesmo que o modo 'vet' implementado
                       # pelos alunos é mais rápido, ele não está gerando resultados satisfatórios

    # Questão 1 do cenário fictício
    print("Questão 1: Precificação da opção de compra de R$ 1.000,00 do ativo para o tempo presente (t = 0) considerando que o ativo tem preço de R$ 1,00 hoje.")
    print(f"K = {K}    sigma = {sigma}    T = {T}    r = {r*100}%    t = {t}    S = {S}    M = {M}")
    vst1 = Vst1(S, t, K, sigma, T, r, N, M, L, utype)
    print(f'Utilizando a aproximação (6): {vst1 * 1000}')
    vst2 = Vst2(S, t, K, sigma, T, r, N, M, L, utype)
    print(f'Utilizando a aproximação (7): {vst2 * 1000}')
    print("")
    
    # Questão 2 do cenário fictício
    
    print("Questão 2: Lucro/prejuízo da opção em diversos cenários de S para alguns instantes de tempo")
    print("Ver gráficos")

    # O código comentado abaixo está funcionando corretamente, mas como optamos
    # pelo 'loops', ele é demorado (alguns minutos). Para ver os resultados desse
    # código, ver 'Questão 2' no relatório.
    """ premioPorAtivo = vst1
    t = 0.5
    S = 1
    print(f"Lucro para t = 0.5 e S = {S} é igual a {lucroPorAtivo(S, t, K, sigma, T, r, N, M, L, utype, premioPorAtivo)*1000}")
    S = 1.5
    print(f"Lucro para t = 0.5 e S = {S} é igual a {lucroPorAtivo(S, t, K, sigma, T, r, N, M, L, utype, premioPorAtivo)*1000}")
    S = 0.6
    print(f"Lucro para t = 0.5 e S = {S} é igual a {lucroPorAtivo(S, t, K, sigma, T, r, N, M, L, utype, premioPorAtivo)*1000}")


    S_valores = [.95 + i*.01 for i in range(10)]
    t = 0.0
    y = [Vst1(Sv, t, K, sigma, T, r, N, M, L, utype) for Sv in S_valores]
    plt.plot(S_valores, y, label=f't = {t}')  
    t = 0.5
    y = [Vst1(Sv, t, K, sigma, T, r, N, M, L, utype) for Sv in S_valores]
    plt.plot(S_valores, y, label=f't = {t}')  
    t = 0.99  # se for um, dá divisão por zero
    y = [Vst1(Sv, t, K, sigma, T, r, N, M, L, utype) for Sv in S_valores]
    plt.plot(S_valores, y, label=f't = {t}')  
    plt.xlabel('Preço da ação')
    plt.ylabel('Preço da opção')
    plt.title(f"Preço da ação versus preço da opção para alguns valores de t e sigma = {sigma*100}%")
    plt.legend()

    plt.show() """

    print("")

    # Questão 3 do cenário fictício
    
    print("Questão 3: Análise para sigma = 2%")
    print("Ver gráficos")

    # O código comentado abaixo está funcionando corretamente, mas como optamos
    # pelo 'loops', ele é demorado (alguns minutos). Para ver os resultados desse
    # código, ver 'Questão 3' no relatório.
    """ sigma = 0.02
    M = 4 * M  # Ajuste para manter estabilidade

    t = 0.0
    premioPorAtivo = Vst1(S, t, K, sigma, T, r, N, M, L, utype)
    t = 0.5
    print("Questão 3: Lucro/prejuízo da opção em diversos cenários de S para alguns instantes de tempo")
    S = 1
    print(f"Lucro para t = 0.5 e S = {S} é igual a {lucroPorAtivo(S, t, K, sigma, T, r, N, M, L, utype, premioPorAtivo)*1000}")
    S = 1.5
    print(f"Lucro para t = 0.5 e S = {S} é igual a {lucroPorAtivo(S, t, K, sigma, T, r, N, M, L, utype, premioPorAtivo)*1000}")
    S = 0.6
    print(f"Lucro para t = 0.5 e S = {S} é igual a {lucroPorAtivo(S, t, K, sigma, T, r, N, M, L, utype, premioPorAtivo)*1000}")
    
    print("Ver gráficos")

    S_valores = [.95 + i*.01 for i in range(10)]
    t = 0.0
    y = [Vst1(Sv, t, K, sigma, T, r, N, M, L, utype) for Sv in S_valores]
    plt.plot(S_valores, y, label=f't = {t}')  
    t = 0.5
    y = [Vst1(Sv, t, K, sigma, T, r, N, M, L, utype) for Sv in S_valores]
    plt.plot(S_valores, y, label=f't = {t}')  
    t = 0.99  # se for um, dá divisão por zero
    y = [Vst1(Sv, t, K, sigma, T, r, N, M, L, utype) for Sv in S_valores]
    plt.plot(S_valores, y, label=f't = {t}')  
    plt.xlabel('Preço da ação')
    plt.ylabel('Preço da opção')
    plt.title(f"Preço da ação versus preço da opção para alguns valores de t e sigma = {sigma*100}%")
    plt.legend()

    plt.show() """

    print("")

    # Questão 4 do cenário fictício
    print("Questão 4: Análise para sigma = 10% e r = 10% e analise o cenário de precificação e lucros em função do preço do ativo.")
    print("Ver gráficos")

    # O código comentado abaixo está funcionando corretamente, mas como optamos
    # pelo 'loops', ele é demorado (alguns minutos). Para ver os resultados desse
    # código, ver 'Questão 4' no relatório.
    """ sigma = 0.10
    r = 0.10
    M = int(T * (sigma*N / (2*L))**2 ) + 1  # Ajuste para manter estabilidade

    t = 0.0
    premioPorAtivo = Vst1(S, t, K, sigma, T, r, N, M, L, utype)
    t = 0.5

    S = 1
    print(f"Lucro para t = 0.5 e S = {S} é igual a {lucroPorAtivo(S, t, K, sigma, T, r, N, M, L, utype, premioPorAtivo)*1000}")
    S = 1.5
    print(f"Lucro para t = 0.5 e S = {S} é igual a {lucroPorAtivo(S, t, K, sigma, T, r, N, M, L, utype, premioPorAtivo)*1000}")
    S = 0.6
    print(f"Lucro para t = 0.5 e S = {S} é igual a {lucroPorAtivo(S, t, K, sigma, T, r, N, M, L, utype, premioPorAtivo)*1000}")
    
    print("Ver gráficos")

    S_valores = [.95 + i*.02 for i in range(5)]
    t = 0.0
    y = []
    for i in range(len(S_valores)):
        print(f"{i} de {len(S_valores)} computados")
        y.append(Vst1(S_valores[i], t, K, sigma, T, r, N, M, L, utype))
    plt.plot(S_valores, y, label=f't = {t}')  
    t = 0.5
    y = []
    for i in range(len(S_valores)):
        print(f"{i} de {len(S_valores)} computados")
        y.append(Vst1(S_valores[i], t, K, sigma, T, r, N, M, L, utype))
    plt.plot(S_valores, y, label=f't = {t}')  
    t = 0.99  # se for um, dá divisão por zero
    y = []
    for i in range(len(S_valores)):
        print(f"{i} de {len(S_valores)} computados")
        y.append(Vst1(S_valores[i], t, K, sigma, T, r, N, M, L, utype))
    plt.plot(S_valores, y, label=f't = {t}')  
    plt.xlabel('Preço da ação')
    plt.ylabel('Preço da opção')
    plt.title(f"Preço da ação versus preço da opção para alguns valores de t e sigma = {sigma*100}%")
    plt.legend()

    plt.show() """

    print("")

    # Questão 5 será respondida no relatório
    print("Questão 5 respondida no relatório")
    print("")

    # Arquivo txt do cenário 1
    f = open("cenario1.txt", "w")
    f.write(f"Questão 1: Precificação da opção de compra de R$ 1.000,00 do ativo para o tempo presente (t = 0) considerando que o ativo tem preço de R$ 1,00 hoje.\nK = {K}    sigma = {sigma}    T = {T}    r = {r*100}%    t = {t}    S = {S}    M = {M}\nUtilizando a aproximação (6): {vst1 * 1000}\nUtilizando a aproximação (7): {vst2 * 1000}\nQuestão 2: Lucro/prejuízo da opção em diversos cenários de S para alguns instantes de tempo\nVer gráficos\nQuestão 3: Análise para sigma = 2%\nVer gráficos\nQuestão 4: Análise para sigma = 10% e r = 10% e analise o cenário de precificação e lucros em função do preço do ativo.\nVer gráficos\nQuestão 5 respondida no relatório\n")
    f.close()

# Roda o cenário de câmbio
def cenarioCambio():
    S = 5.6376
    t = 0
    K = 5.7
    sigma = 0.1692
    T = 0.25 
    r = 0.1075
    M = 1800    # M calculado para ser estável
    utype = 'loops'    # Maneiras de calcular 'u'; 'vet' para vetorização e 'loops' para 2 loops
                       # Foi escolhido o modo 'loops' para rodar pois, mesmo que o modo 'vet' implementado
                       # pelos alunos é mais rápido, ele não está gerando resultados satisfatórios

    # Cálculo do prêmio da opção
    # Esse código demora uns 2 minutos para rodar
    print(f"K = {K}    sigma = {sigma}    T = {T}    r = {r*100}%    t = {t}    S = {S}    M = {M}")
    print("Prêmio da opção:")
    vst1 = Vst1(S, t, K, sigma, T, r, N, M, L, utype)
    print(f'Utilizando a aproximação (6): {vst1 * 17543.8596491}')
    vst2 = Vst2(S, t, K, sigma, T, r, N, M, L, utype)
    print(f'Utilizando a aproximação (7): {vst2 * 17543.8596491}')
    print("")

    # Comparação com o valor real
    print("Comparação com o valor real respondida no relatório.")

    # Análise o cenário de lucro/prejuízo estimado para 1 de Janeiro de 2022
    t = 1/3
    print("Análise o cenário de lucro/prejuízo estimado para 1 de Janeiro de 2022:")
    print("Ver gráficos")

    # O código comentado abaixo está funcionando corretamente, mas como optamos
    # pelo 'loops', ele é demorado (alguns minutos). Para ver os resultados desse
    # código, ver 'Cenário de câmbio' no relatório.
    """ premioPorAtivo = vst1
    S_valores = [5.35 + i*.2 for i in range(5)]
    y = []
    for i in range(len(S_valores)):
        print(f"{i} de {len(S_valores)} computados")
        y.append(lucroPorAtivo(S_valores[i], t, K, sigma, T, r, N, M, L, utype, premioPorAtivo) * 1e5 / 5.7)

    plt.plot(S_valores, y, label=f't = {t}')  
    plt.xlabel('Preço da ação')
    plt.ylabel('Preço da opção')
    plt.title(f"Preço da ação versus preço da opção com sigma = {sigma*100}%")
    plt.legend()

    plt.show() """

    # Arquivo txt do cenário 2
    f = open("cenario2.txt", "w")
    f.write(f"K = {K}    sigma = {sigma}    T = {T}    r = {r*100}%    t = {t}    S = {S}    M = {M}\nPrêmio da opção:\nUtilizando a aproximação (6): {vst1 * 17543.8596491}\nUtilizando a aproximação (7): {vst2 * 17543.8596491}\nComparação com o valor real respondida no relatório.\nAnálise o cenário de lucro/prejuízo estimado para 1 de Janeiro de 2022:\nVer gráficos\n")
    f.close()

# Roda o cenárioReal
def cenarioReal():
    S = 5.54
    t = 0
    K = 5.6
    sigma = 0.7374
    T = 0.5
    r = 0.1075
    M = 3150     # M calculado para ser estável
    utype = 'loops'    # Maneiras de calcular 'u'; 'vet' para vetorização e 'loops' para 2 loops
                       # Foi escolhido o modo 'loops' para rodar pois, mesmo que o modo 'vet' implementado
                       # pelos alunos é mais rápido, ele não está gerando resultados satisfatórios

    #Esse código demora uns 3 minutos para rodar
    print(f"K = {K}    sigma = {sigma}    T = {T}    r = {r*100}%    t = {t}    S = {S}    M = {M}")
    print("Precificação para daqui 6 meses:")
    vst1 = Vst1(S, t, K, sigma, T, r, N, M, L, utype)
    print(f'Precificação de uma unidade, utilizando a aproximação (6): {vst1}')
    vst2 = Vst2(S, t, K, sigma, T, r, N, M, L, utype)
    print(f'Precificação de uma unidade, utilizando a aproximação (7): {vst2}')

    # Arquivo txt do cenário 3
    f = open("cenario3.txt", "w")
    f.write(f"K = {K}    sigma = {sigma}    T = {T}    r = {r*100}%    t = {t}    S = {S}    M = {M}\nPrêmio da opção:\nUtilizando a aproximação (6): {vst1 * 17543.8596491}\nUtilizando a aproximação (7): {vst2 * 17543.8596491}\nVer gráficos\n")
    f.close()

# Realiza o teste do tempo
def testeTempo():
    S = 1
    t = 0
    K = 1.00
    sigma = 0.01 
    T = 1 
    r = 0.01
    M = 30

    tic = time.time()
    u = uPor2Loops(K, sigma, T, r, N, M, L)
    toc = time.time()
    print(f"Temporização uPor2Loops: {toc - tic} s")

    tic = time.time()
    u = uPorVetorizacao(K, sigma, T, r, N, M, L)
    toc = time.time()
    print(f"Temporização uPorVetorizacao: {toc - tic} s")

# Imprime cabeçalho
def imprimeCabecalho():
    print("============================================================")
    print("MAP3122 - Métodos Numéricos e Aplicações - 2022")
    print("EP2")
    print("Alunos: Luiz Guilherme Budeu     NUSP: 11821639")
    print("        Rafael Katsuo Nakata     NUSP: 11803819")
    print("============================================================")

# Imprime opções
def opcoes():
    print("")
    print("------------------------------------------------------------")
    print("Escolha qual cenário deseja rodar:")
    print("1. Cenário fictício")
    print("2. Cenário de câmbio")
    print("3. Cenário real - Bônus")
    print("4. Teste de tempo entre 'u' por 2 loops e 'u' por vetorização (Cenário fictício)")
    print("q. Quit\n")
    escolha = input("Sua escolha: ")
    print("------------------------------------------------------------")
    print("")
    return escolha

# Obtém o V analítico
def VAnalitico(S, t, K, sigma, T, r):
    tau = T - t
    x = np.log(S/K) + (r - sigma**2/2) * tau

    d1 = ( x + sigma**2 * tau ) / ( sigma * np.sqrt(tau) )
    d2 = x / ( sigma * np.sqrt(tau) )
    V = S * norm.cdf(d1) - K * np.exp(-r*tau) * norm.cdf(d2)

    return V

# Obtém o 'u' de maneira analítica
def uAnalitico(S, t, K, sigma, T, r):
    tau = T - t
    x = np.log(S/K) + (r - sigma**2/2) * tau

    d1 = ( x + sigma**2 * tau ) / ( sigma * np.sqrt(tau) )
    d2 = x / ( sigma * np.sqrt(tau) )
    V = K * np.exp(x + sigma**2 * tau/2) * norm.cdf(d1) - K * norm.cdf(d2)

    return V


