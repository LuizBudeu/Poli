
# Um problema desse algoritmo é que toda vez que ele acha um caminho 
# possível, ele incrementa o 'numPedidos', mesmo que ele já tenha achado
# esse mesmo caminho só que em outra ordem. Ou seja, esse algoritmo 
# conta como dois pedidos diferentes fazer a compra "A, C" e "C, A".
# Como não consegui achar uma solução viável para salvar cada caminho separado
# para depois comparar e só depois incrementar 'numPedidos' se forem
# diferentes, optei por deixar esse algoritmo mesmo que errado pois roda
# nos limites de tempo estabelecidos.

# ALGORITMO----------------------------------------
def solve(voucher, produtos, memo = {}):
    global numPedidos

    # Já pegamos valores salvos para economizar tempo de computação
    if voucher in memo:
        return memo[voucher]

    # Se achou um caminho que dá certo
    if voucher == 0:
        return True

    # Se achou um caminho que não dá certo
    if voucher < 0:
        return False
    
    # Maneira recursiva de checar todos os caminhos possíveis para todos preços de produtos
    for p in produtos:
        restante = voucher - p 
            
        # Se acha um caminho possível, salva em 'memo' que existe esse caminho
        if solve(restante, produtos, memo):
            numPedidos += 1
            memo[voucher] = True
            return True
        
    # Guarda os valores para economizar tempo de computação
    memo[voucher] = False
    return False



# INPUT------------------------------------------
voucher = int(input())
produtos = [] 


produtos.append(input().split())
# Os produtos são salvos no primeiro elemento de uma lista por causa da linha acima
for i in range(len(produtos[0])):
    produtos[0][i] = int(produtos[0][i])

# Guardamos o número de pedidos na variável global 'numPedidos'
numPedidos = 0

# Como queremos saber só o número de pedidos, não precisamos salvar o resultado de 'solve' em algo útil
_ = solve(voucher, produtos[0])

# Imprime o valor errado de 'numPedidos' pois existem caminhos repetidos
print(numPedidos)
