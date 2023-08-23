import random

vezes = 100

def geraDescricao():
    return random.choice(["Preferencial", "Ordinárias", "Units"])

def geraTipoAcao():
    t_acao = []
    for i in range(vezes):
        emp = []
        emp.append(geraDescricao())
        t_acao.append(emp)

    return t_acao

def geraArquivo():
    f = open("tipo_acao.txt", "w")
    emps = geraTipoAcao()
    for e in emps:
        f.write(f"INSERT INTO tipo_acao (descricao) values (\"{e[0]}\");\n")
    f.close()


def main():
    geraArquivo()


if __name__ == '__main__':
    main()