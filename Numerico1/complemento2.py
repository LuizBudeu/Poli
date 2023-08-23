def calculaComplemento2(num):
    resultado = ''
    for letter in num:
        resultado += str(inverso(letter))

    sum = bin(int(resultado, 2) + int('1', 2))

    return str(sum)

def inverso(letra):
    if letra == '0':
        return 1
    elif letra == '1':
        return 0
    else:
        return None

numBinario = input("Digite o número em binário: ")
print(f"Complemento de 2 do número binário: {calculaComplemento2(numBinario)}")
