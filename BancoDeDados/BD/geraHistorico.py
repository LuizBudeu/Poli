import random

numeros = "123456789"
vezes = 60

def geraMarcaTemporal():
    ano = random.randint(2010, 2020)
    mes = random.randint(1, 12)
    dia = random.randint(1, 29)
    
    return f"{ano}-{mes}-{dia}"


def geraPreco():
    return random.randint(80, 120)


def geraHistorico():
    historico = []
    for j in range(10):
        for i in range(vezes):
            emp = []
            emp.append(i+1)
            emp.append(geraMarcaTemporal())
            emp.append(geraPreco())
            historico.append(emp)

    return historico


def geraArquivo():
    f = open("historicos.txt", "w")
    emps = geraHistorico()
    for e in emps:
        f.write(f"INSERT INTO historico_acao (id_acao, marca_temporal, preco) values ({e[0]}, \"{e[1]}\", {e[2]});\n")
    f.close()


def main():
    geraArquivo()


if __name__ == '__main__':
    main()