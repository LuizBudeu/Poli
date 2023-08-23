from queue import Queue


# FUNÇÃO ÚTIL: adiciona valores a um dicionário
def append_value(dict_obj, key, value):
    if key in dict_obj:
        if not isinstance(dict_obj[key], list):
            dict_obj[key] = [dict_obj[key]]

        dict_obj[key].append(value)
    else:

        dict_obj[key] = value

# ALGORITMO----------------------------------------
def bfs(vizinhos, k):
    visitados = {}
    distancias = {}
    queue = Queue()

    # Todos nodes começam não visitados e com distâncias infinitas
    for node in vizinhos.keys():
        visitados[node] = False
        distancias[node] = -1

    # Inicia os nodes 'k's como nodes iniciais
    for i in k:
        visitados[i] = True
        distancias[i] = 0
        queue.put(i)

    # Calcula as distâncias mínimas de cada node aos nodes iniciais
    while not queue.empty():
        u = queue.get()

        for v in vizinhos[u]:
            if not visitados[v]:
                visitados[v] = True
                distancias[v] = distancias[u] + 1
                queue.put(v)

    # Seleciona o maior número de traduções
    x = 0
    for v in distancias.values():
        if v > x:
            x = v

    # Verifica se o grafo é conexo
    conexo = True
    for v in visitados.values():
        if v == False:
            conexo = False

    return x, conexo
    


# INPUT------------------------------------------
m, n, a = input().split()
m, n, a = [int(m), int(n), int(a)]

grafo = {}
nodes = []

# Salva o grafo como todos os vizinhos de cada node
for _ in range(n):
    i, j, t = input().split()
    i, j, t = [int(i), int(j), int(t)]
    if i not in nodes:
        nodes.append(i)
    if j not in nodes:
        nodes.append(j)
    append_value(grafo, i, j)
    append_value(grafo, j, i)

nodes.sort()

# Converte todos os values para 'list' para iterar depois
for k, v in grafo.items():
    if not isinstance(grafo[k], list):
        grafo[k] = [grafo[k]]

# Salva os 'k's
k = []
for _ in range(a):
    k.append(int(input()))

traducoes, conexo = bfs(grafo, k)
# Se o grafo for conexo, printa a maior tradução
if conexo:
    print(traducoes)
# Se não for conexo, printa -1
else:
    print(-1)