
# ALGORITMO----------------------------------------
def solve(voucher, produtos, memo = {}):
    # Já pegamos valores salvos para economizar tempo de computação
    if voucher in memo:
        return memo[voucher]

    # Se achou um caminho que dá certo
    if voucher == 0:
        return []

    # Se achou um caminho que não dá certo
    if voucher < 0:
        return None


    # Maneira recursiva de checar todos os caminhos possíveis para todos preços de produtos
    menorCombinacao = None
    for p in produtos:
        restante = voucher - p 
        combinacaoRestante = solve(restante, produtos, memo)

        # Se ainda existe um caminho abaixo, isto é, não acabou o voucher
        if combinacaoRestante != None:
            combinacao = combinacaoRestante.copy()
            combinacao.append(p)

            # Se a combinação for menor que a atual, esse é a nova menor combinação
            if menorCombinacao == None or len(combinacao) < len(menorCombinacao):
                menorCombinacao = combinacao

    # Guarda os valores para economizar tempo de computação
    memo[voucher] = menorCombinacao
    return menorCombinacao



# INPUT------------------------------------------
voucher = int(input())
produtos = [] 


produtos.append(input().split())
# Os produtos são salvos no primeiro elemento de uma lista por causa da linha acima
for i in range(len(produtos[0])):
    produtos[0][i] = int(produtos[0][i])


# 'solve' retorna o caminho mais curto, então fazemos 'len' para saber quantos elementos são
print(len(solve(voucher, produtos[0])))









