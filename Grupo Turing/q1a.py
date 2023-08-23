
# ALGORITMO----------------------------------------
def dijkstra(nodes, grafo):
    # Começa as distâncias com 1000000 = inf
    distMin = {v: 1000000 for v in nodes}
    # A distância do node inicial ao node inicial = 0
    distMin[0] = 0

    # Salva os vizinhos de cada node
    vizinhos = {v: {} for v in nodes}
    for (u, v), tempo in grafo.items():
        vizinhos[u][v] = tempo 
        vizinhos[v][u] = tempo

    # Calcula as distâncias mínimas para cada node a partir do node inicial
    nodesTemp = [v for v in nodes]
    while nodesTemp:
        outrosCaminhos = {v: distMin[v] for v in nodesTemp}
        u = min(outrosCaminhos, key=outrosCaminhos.get)

        nodesTemp.remove(u)

        for v, tempo in vizinhos[u].items():
            distMin[v] = min(distMin[v], distMin[u] + tempo)

    # Retorna as distâncias mínimas para cada node a partir do node inicial
    return distMin



# INPUT------------------------------------------
m, n, a = input().split()
m, n, a = [int(m), int(n), int(a)]

grafo = {}
nodes = []

# Salva o grafo como um dicionário com key=tuple(i, j) e value=t
for _ in range(n):
    i, j, t = input().split()
    i, j, t = [int(i), int(j), int(t)]
    if i not in nodes:
        nodes.append(i)
    if j not in nodes:
        nodes.append(j)
    grafo[(i, j)] = t

nodes.sort()

# Só pra contar as 'a' linhas
for _ in range(a):
    _ = input()

# Printa a distância mínima do último node
print(dijkstra(nodes, grafo)[nodes[-1]])
